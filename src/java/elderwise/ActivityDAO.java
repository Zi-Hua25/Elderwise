/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.text.SimpleDateFormat;

/**
 *
 * @author Terence
 */
public class ActivityDAO {

    private Hashtable<String, Hashtable<String, Activity>> activityTable;

    public ActivityDAO() {
        activityTable = new Hashtable<String, Hashtable<String, Activity>>();

    }

    public Activity getActivity(String elderly, String date) {
        //SimpleDateFormat format = new SimpleDateFormat("yyMMdd");

        //String strDate = format.format(date);
        return activityTable.get(elderly).get(date);
    }

    //use rolling average to calculate psychomotor occurence
    //2 SD need to test, no need compare to profile.
    //if more than 2SD, disregard that in calculation, psychomotor++
    //next day 1st value compare with the last day moving average
    //sleep time
    //elderly in house, elderly on bed, other PIR sensor N, 30 mins
    //woken up
    //if sleeping and if other PIR sensor got reading, means he left bed
    //if woken up > 30 mins, means woken up
    //if woken up < 30 mins, means sleep disturbance
    public void analyzeReadings(Hashtable<String, Hashtable<String, ArrayList<SensorReading>>> sensorReadingsTable) {
        //id a1 = sleeping timings
        //id a2 = sleep disturbances timings
        //id a3 = out of home timings
        //id a4 = timings spent in living room
        //id a5 = timings spent in bed room
        //id a6 = timings spent in kitchen
        //id a7 = timings spent in bathroom

        for (String elderlyId : sensorReadingsTable.keySet()) {
            Hashtable<String, ArrayList<SensorReading>> elderlyTable = sensorReadingsTable.get(elderlyId);
            for (String date : elderlyTable.keySet()) {
                SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
                
                // String strDate = format.format(date);
                Activity activity = new Activity();
                ArrayList<SensorReading> readings = elderlyTable.get(date);
                String testLog = "_";
                Hashtable<String, ArrayList<Calendar[]>> allActivityTable
                        = new Hashtable<String, ArrayList<Calendar[]>>();

                ArrayList<Calendar[]> sleepTimesList = new ArrayList<Calendar[]>();
                ArrayList<Calendar[]> outTimesList = new ArrayList<Calendar[]>();

                ArrayList<Integer[]> sleepAndOutRowReference = new ArrayList<Integer[]>();
                for (int i = 0; i < readings.size(); i++) {
                    //current row read
                    SensorReading sr = readings.get(i);

                    if (sr.getBathroomPIR() == false && sr.getBed() == false
                            && sr.getBedRoomPIR() == false && sr.getDoorContact() == false
                            && sr.getKitchenPIR() == false && sr.getLivingRoomPIR() == false) {

                        //check whether the readings is already included in sleep or out times
                        boolean recorded = false;
                        for (Calendar[] cal : sleepTimesList) {
                            if ((sr.getDate().getTime().after(cal[0].getTime())
                                    || sr.getDate().getTime().equals(cal[0].getTime()))
                                    && (sr.getDate().getTime().before(cal[1].getTime())
                                    || sr.getDate().getTime().equals(cal[1].getTime()))) {
                                recorded = true;
                            }
                        }
                        for (Calendar[] cal : outTimesList) {
                            if ((sr.getDate().getTime().after(cal[0].getTime())
                                    || sr.getDate().getTime().equals(cal[0].getTime()))
                                    && (sr.getDate().getTime().before(cal[1].getTime())
                                    || sr.getDate().getTime().equals(cal[1].getTime()))) {
                                recorded = true;
                            }
                        }
                        format.applyPattern("yy/MM/dd HH:mm:ss");
                        if (!recorded) {
                            int awakeOrInOccurence = findAwakeOrInOccurence(readings, i);

                            //cannot find any changedOccurence because reach end of file
                            //need to check whether it is sleep or out
                            if (awakeOrInOccurence == readings.size()) {
                                Integer[] rowRefs = new Integer[2];
                                Calendar[] sleepTime = new Calendar[2];
                                Calendar startime = Calendar.getInstance();
                                startime.setTime(sr.getDate().getTime());
                                rowRefs[0] = i;
                                SensorReading end = readings.get(awakeOrInOccurence - 1);
                                rowRefs[1] = awakeOrInOccurence - 1;
                                sleepAndOutRowReference.add(rowRefs);
                                Calendar endTime = Calendar.getInstance();
                                endTime.setTime(end.getDate().getTime());
                                sleepTime[0] = startime;
                                sleepTime[1] = endTime;
                                sleepTimesList.add(sleepTime);
                                testLog += "<br>Sleep from " + format.format(sleepTime[0].getTime()) + " to " + format.format(sleepTime[1].getTime());

                            } else if (awakeOrInOccurence != -1) {
                                //assumption that sleep and go out only count if there are 
                                //200 consistent rows (around 30 mins time)
                                //need to change from 200 rows to 30 mins in the future
                                if (awakeOrInOccurence - i > 200) {
                                    //if (i != 0) {
                                    
                                    SensorReading changedReading = readings.get(awakeOrInOccurence);
                                    if (changedReading.getDoorContact()) {
                                        Integer[] rowRefs = new Integer[2];
                                        Calendar[] outTiming = new Calendar[2];
                                        Calendar outStart = Calendar.getInstance();
                                        outStart.setTime(sr.getDate().getTime());
                                        rowRefs[0] = i;

                                        SensorReading end = readings.get(awakeOrInOccurence - 1);
                                        rowRefs[1] = awakeOrInOccurence - 1;
                                        sleepAndOutRowReference.add(rowRefs);

                                        Calendar outEnd = Calendar.getInstance();
                                        outEnd.setTime(end.getDate().getTime());
                                        outTiming[0] = outStart;
                                        outTiming[1] = outEnd;
                                        outTimesList.add(outTiming);
                                        testLog += "<br>Go Out from " + format.format(outTiming[0].getTime()) + " to " + format.format(outTiming[1].getTime());
                                    } else {
                                        Integer[] rowRefs = new Integer[2];
                                        Calendar[] sleepTime = new Calendar[2];
                                        Calendar sleepStart = Calendar.getInstance();
                                        sleepStart.setTime(sr.getDate().getTime());
                                        rowRefs[0] = i;
                                        SensorReading end = readings.get(awakeOrInOccurence - 1);
                                        rowRefs[1] = awakeOrInOccurence - 1;
                                        sleepAndOutRowReference.add(rowRefs);
                                        Calendar sleepEnd = Calendar.getInstance();
                                        sleepEnd.setTime(end.getDate().getTime());
                                        sleepTime[0] = sleepStart;
                                        sleepTime[1] = sleepEnd;
                                        sleepTimesList.add(sleepTime);
                                        testLog += "<br>Sleep from " + format.format(sleepTime[0].getTime()) + " to " + format.format(sleepTime[1].getTime());
                                    
                                       
                                    }
                                }
                                //}
                            }
                        }
                    }
                }

                ArrayList<Calendar[]> inactivityList = new ArrayList<Calendar[]>();
                for (Calendar[] sleepTimes : sleepTimesList) {
                    inactivityList.add(sleepTimes);
                }
                for (Calendar[] outTimes : outTimesList) {
                    inactivityList.add(outTimes);
                }
                Collections.sort(sleepTimesList, new Comparator<Calendar[]>() {
                    @Override
                    public int compare(Calendar[] o1, Calendar[] o2) {
                        if (o1[0].getTime().before(o2[0].getTime())) {
                            return -1;
                        } else if (o1[0].getTime().after(o2[0].getTime())) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                });
                Collections.sort(outTimesList, new Comparator<Calendar[]>() {
                    @Override
                    public int compare(Calendar[] o1, Calendar[] o2) {
                        if (o1[0].getTime().before(o2[0].getTime())) {
                            return -1;
                        } else if (o1[0].getTime().after(o2[0].getTime())) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                });

                allActivityTable.put("a1", sleepTimesList);

                allActivityTable.put("a3", outTimesList);
                Collections.sort(inactivityList, new Comparator<Calendar[]>() {
                    @Override
                    public int compare(Calendar[] o1, Calendar[] o2) {
                        if (o1[0].getTime().before(o2[0].getTime())) {
                            return -1;
                        } else if (o1[0].getTime().after(o2[0].getTime())) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                });

                Collections.sort(sleepAndOutRowReference, new Comparator<Integer[]>() {
                    @Override
                    public int compare(Integer[] o1, Integer[] o2) {
                        return o1[0] - o2[0];
                    }
                });

                /*for (Integer[] test: sleepAndOutRowReference){
                 System.out.println("row " + test[0] + " " + test[1]) ;
                 }*/
                ArrayList<Integer[]> activeRowReference = new ArrayList<Integer[]>();
                ArrayList<Calendar[]> activityList = new ArrayList<Calendar[]>();
                for (int i = 0; i < inactivityList.size(); i++) {
                    if (i == 0) {
                        if (sleepAndOutRowReference.get(i)[0] != 0) {
                            //System.out.println("a");
                            Calendar[] activeTimes = new Calendar[2];
                            Calendar start = Calendar.getInstance();
                            Calendar end = Calendar.getInstance();
                            start = readings.get(i).getDate();
                            end = readings.get(sleepAndOutRowReference.get(i)[0] - 1).getDate();
                            activeTimes[0] = start;
                            activeTimes[1] = end;
                            activityList.add(activeTimes);
                            Integer[] rowRef = new Integer[2];
                            rowRef[0] = 0;
                            rowRef[1] = sleepAndOutRowReference.get(i)[0] - 1;
                            activeRowReference.add(rowRef);
                            //System.out.println("a");
                        }
                        /*else {
                    
                         System.out.println("b");
                         Calendar[] activeTimes = new Calendar[2];
                         Calendar start = Calendar.getInstance();
                         Calendar end = Calendar.getInstance();
                         start = readings.get(sleepAndOutRowReference.get(i)[1] + 1).getDate();
                         end = readings.get(sleepAndOutRowReference.get(i + 1)[0] - 1).getDate();
                         activeTimes[0] = start;
                         activeTimes[1] = end;
                         activityList.add(activeTimes);
                         Integer[] rowRef = new Integer[2];
                         rowRef[0] = sleepAndOutRowReference.get(i)[1] + 1;
                         rowRef[1] = sleepAndOutRowReference.get(i)[0] - 1;
                         activeRowReference.add(rowRef);
                         System.out.println("b " + rowRef[0] + " " + rowRef[1]);
                            
                         } */
                    } else if (i == inactivityList.size() - 1) {
                        //System.out.println("d");
                        Calendar[] activeTimes = new Calendar[2];
                        Calendar start = Calendar.getInstance();
                        Calendar end = Calendar.getInstance();
                        start = readings.get(sleepAndOutRowReference.get(i - 1)[1] + 1).getDate();
                        end = readings.get(sleepAndOutRowReference.get(i)[0] - 1).getDate();
                        //System.out.println(start);
                        //System.out.println(end);
                        activeTimes[0] = start;
                        activeTimes[1] = end;
                        activityList.add(activeTimes);
                        Integer[] rowRef = new Integer[2];
                        rowRef[0] = sleepAndOutRowReference.get(i - 1)[1] + 1;
                        rowRef[1] = sleepAndOutRowReference.get(i)[0] - 1;
                        activeRowReference.add(rowRef);
                //System.out.println("d");

                        //if the inactivity does not reach end of file. make activity until end of file
                        if (sleepAndOutRowReference.get(i)[1] != readings.size() - 1) {
                            //System.out.println("c");
                            Calendar[] activeTimes2 = new Calendar[2];
                            Calendar start2 = Calendar.getInstance();
                            Calendar end2 = Calendar.getInstance();
                            start2 = readings.get(sleepAndOutRowReference.get(i)[1] + 1).getDate();
                            end2 = readings.get(readings.size() - 1).getDate();
                            activeTimes2[0] = start2;
                            activeTimes2[1] = end2;
                            activityList.add(activeTimes2);
                            Integer[] rowRef2 = new Integer[2];
                            rowRef2[0] = sleepAndOutRowReference.get(i)[1] + 1;
                            rowRef2[1] = readings.size() - 1;
                            activeRowReference.add(rowRef2);
                            //System.out.println("c");
                        }
                    } else {
                        Calendar[] activeTimes = new Calendar[2];
                        Calendar start = Calendar.getInstance();
                        Calendar end = Calendar.getInstance();
                        start = readings.get(sleepAndOutRowReference.get(i - 1)[1] + 1).getDate();
                        end = readings.get(sleepAndOutRowReference.get(i)[0] - 1).getDate();
                        //System.out.println(start);
                        //System.out.println(end);
                        activeTimes[0] = start;
                        activeTimes[1] = end;
                        activityList.add(activeTimes);
                        Integer[] rowRef = new Integer[2];
                        rowRef[0] = sleepAndOutRowReference.get(i - 1)[1] + 1;
                        rowRef[1] = sleepAndOutRowReference.get(i)[0] - 1;
                        activeRowReference.add(rowRef);
                    }
                }

                Collections.sort(activityList, new Comparator<Calendar[]>() {
                    @Override
                    public int compare(Calendar[] o1, Calendar[] o2) {
                        if (o1[0].getTime().before(o2[0].getTime())) {
                            return -1;
                        } else if (o1[0].getTime().after(o2[0].getTime())) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                });
                format.applyPattern("yy/MM/dd HH:mm:ss");
                testLog += "<br><br>-------------------------------------------<br><br>";
                for (int i = 0; i < inactivityList.size(); i++) {
                    Calendar[] cal = inactivityList.get(i);
                    Integer[] rows = sleepAndOutRowReference.get(i);
                    testLog += "<br>Row " + rows[0] + "-" + rows[1] + ": inactive from "
                            + format.format(cal[0].getTime()) + " to " + format.format(cal[1].getTime());
                }
                testLog += "<br><br>-------------------------------------------<br><br>";
                for (int i = 0; i < activityList.size(); i++) {
                    Calendar[] cal = activityList.get(i);
                    Integer[] rows = activeRowReference.get(i);
                    testLog += "<br>Row " + rows[0] + "-" + rows[1] + ": active from "
                            + format.format(cal[0].getTime()) + " to " + format.format(cal[1].getTime());
                }
                testLog += "<br><br>-------------------------------------------<br><br>";

                //retrieve those sensorreadings that shows elderly is active
                //filter those readings that show elderly is active for at least 1 hour
                //for psychomotor calculation
                ArrayList<SensorReading> readingsToCheck = new ArrayList<SensorReading>();

                for (Integer[] rows : activeRowReference) {
                    //readingsToCheck only holds readings that is more than 0.5 hours
                    readingsToCheck = new ArrayList<SensorReading>();
                    int start = rows[0];
                    int end = rows[1];

                    long startTime = readings.get(start).getDate().getTimeInMillis();
                    long endTime = readings.get(end).getDate().getTimeInMillis();
                    //System.out.println(readings.get(start).getDate().getTime());
                    //System.out.println(readings.get(end).getDate().getTime());
                    long diffInMillis = endTime - startTime;
                    //System.out.println(diffInMillis);
                    // assumption every 30 mins interval check for movement
                    if (diffInMillis >= (0.5 * 60 * 60 * 1000)) {
                        for (int j = start; j <= end; j++) {
                            readingsToCheck.add(readings.get(j));
                        }
                    }

                    //do psychomotor checking only if this period last for > 30 minutes (assumption)
                    if (!readingsToCheck.isEmpty()) {
                        //clean up: remove all those rows with all false
                        ArrayList<SensorReading> cleanedReadings = new ArrayList<SensorReading>();
                        for (int i = 0; i < readingsToCheck.size(); i++) {
                            SensorReading sr = readingsToCheck.get(i);
                            if (sr.getBathroomPIR() == true || sr.getBed() == true || sr.getBedRoomPIR() == true
                                    || sr.getKitchenPIR() == true
                                    || sr.getLivingRoomPIR() == true) {
                                cleanedReadings.add(sr);
                            }
                        }

                        boolean change = false;

                        boolean kitchen = false;
                        boolean living = false;
                        boolean bath = false;
                        boolean room = false;
                        //[0] = kitchen
                        //[1] = living
                        //[2] = bath
                        //[3] = room
                        boolean[] moveCompare = new boolean[4];

                        ArrayList<Integer> movementCounts = new ArrayList<Integer>();
                        int moveCountPerHalfHour = 0;
                        //time marked is the date that is read
                        Date timeMarked = new Date();
                        //timeCheck is 1/2 hours later
                        Date timeCheck = new Date();

                        //bedroom and bed count as bedroom only
                        for (int i = 0; i < cleanedReadings.size(); i++) {
                            SensorReading sr = cleanedReadings.get(i);
                            Calendar calTimeCheck = Calendar.getInstance();
                            change = false;
                            kitchen = false;
                            living = false;
                            bath = false;
                            room = false;

                            int count = 0;
                            if (sr.getBathroomPIR()) {
                                bath = true;
                                count++;
                            }
                            if (sr.getBed() || sr.getBedRoomPIR()) {
                                room = true;
                                count++;
                            }
                            if (sr.getKitchenPIR()) {
                                kitchen = true;
                                count++;
                            }
                            if (sr.getLivingRoomPIR()) {
                                living = true;
                                count++;
                            }

                            //to make only a true
                            //assumption 2 is max count
                            //to do: incorporate first row and last row difference
                            if (count > 1) {
                                //last row already
                                SensorReading nextOrPrev = null;
                                if (i == cleanedReadings.size() - 1) {
                                    nextOrPrev = cleanedReadings.get(i - 1);
                                } else {
                                    nextOrPrev = cleanedReadings.get(i + 1);
                                }//haven last row

                                boolean kitchen2 = false;
                                boolean living2 = false;
                                boolean bath2 = false;
                                boolean room2 = false;
                                if (nextOrPrev.getBathroomPIR()) {
                                    bath2 = true;
                                }
                                if (nextOrPrev.getBed() || nextOrPrev.getBedRoomPIR()) {
                                    room2 = true;
                                }
                                if (nextOrPrev.getKitchenPIR()) {
                                    kitchen2 = true;
                                }
                                if (nextOrPrev.getLivingRoomPIR()) {
                                    living2 = true;
                                }

                                if (living == living2) {
                                    living = false;
                                }
                                if (bath == bath2) {
                                    bath = false;
                                }
                                if (room == room2) {
                                    room = false;
                                }
                                if (kitchen == kitchen2) {
                                    kitchen = false;
                                }
                            }

                            if (i == 0) {
                                timeMarked.setTime(sr.getDate().getTimeInMillis());
                                calTimeCheck.setTime(timeMarked);
                                calTimeCheck.add(Calendar.MINUTE, 30);
                                timeCheck.setTime(calTimeCheck.getTimeInMillis());
                                moveCompare[0] = kitchen;
                                moveCompare[1] = living;
                                moveCompare[2] = bath;
                                moveCompare[3] = room;
                            } else {
                                //check if readings go past half hour mark
                                format.applyPattern("yy/MM/dd HH:mm:ss");
                                if (sr.getDate().getTime().after(timeCheck)) {
                                    testLog += "<br>from " + format.format(timeMarked) + " to "
                                            + format.format(timeCheck) + " --> num of movements this half hour: " + moveCountPerHalfHour;

                                    timeMarked.setTime(sr.getDate().getTimeInMillis());
                                    calTimeCheck.setTime(timeMarked);
                                    calTimeCheck.add(Calendar.MINUTE, 30);
                                    timeCheck.setTime(calTimeCheck.getTimeInMillis());
                                    movementCounts.add(moveCountPerHalfHour);
                                    moveCountPerHalfHour = 0;
                                }

                                change = false;
                                //only kitchen is true
                                if (moveCompare[0]) {
                                    if (kitchen != moveCompare[0]) {
                                        moveCountPerHalfHour++;
                                        change = true;
                                    }
                                }
                                //only living room is true
                                if (moveCompare[1]) {
                                    if (living != moveCompare[1]) {
                                        moveCountPerHalfHour++;
                                        change = true;
                                    }
                                }
                                //only bathroom is true
                                if (moveCompare[2]) {
                                    if (bath != moveCompare[2]) {
                                        moveCountPerHalfHour++;
                                        change = true;
                                    }
                                }
                                //only bedroom is true
                                if (moveCompare[3]) {
                                    if (room != moveCompare[3]) {
                                        moveCountPerHalfHour++;
                                        change = true;
                                    }
                                }

                                if (change) {
                                    moveCompare[0] = kitchen;
                                    moveCompare[1] = living;
                                    moveCompare[2] = bath;
                                    moveCompare[3] = room;
                                }

                            }

                            //there should be only 1 true from here onwards
                            //testLog += "<br>" + sr.getDate().getTime() + " --> " + living + " , " + room + " , " + kitchen + " , " + bath;
                            

                        } // end for loop

                    }// end if
                }
                testLog += "<br><br>-------------------------------------------<br><br>";
                activity.setTestPrint(testLog);
                //allActivityTable.put(testLog, new ArrayList<Calendar[]>());
                if (activityTable.containsKey(elderlyId)) {
                    Hashtable<String, Activity> eTable = activityTable.get(elderlyId);
                    eTable.put(date, activity);
                    activityTable.put(elderlyId, eTable);
                } else {
                    Hashtable<String, Activity> eTable = new Hashtable<String, Activity>();
                    eTable.put(date, activity);
                    activityTable.put(elderlyId, eTable);
                }
            }

            //activities.add(activity);
        }

    }

    public int findAwakeOrInOccurence(ArrayList<SensorReading> readings, int i) {
        for (int j = i + 1; j < readings.size(); j++) {
            SensorReading reading = readings.get(j);
            //ignore bed and bed room
            if (reading.getBathroomPIR() == true || reading.getKitchenPIR() == true
                    || reading.getLivingRoomPIR() == true || reading.getDoorContact() == true) {
                return j;
            } else {
                if (j == readings.size() - 1) {
                    return j + 1;
                }
            }

        }

        return -1;
    }

}
