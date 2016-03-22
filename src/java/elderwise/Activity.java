/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

/**
 *
 * @author Terence
 */
public class Activity {
    
    private String activityId;
    private String elderlyId;
    private Calendar date;
    private ArrayList<Calendar[]> sleepingTimes;
    private ArrayList<Calendar[]> outTimes;
    private int sleepDisturbances;
    private ArrayList<Calendar[]> halfHourMovementTime;
    private ArrayList<Integer> halfHourMovementCount;
    
    private String testPrint;
            

    //private Calendar[] sleepingTimes;
  
    //each hashtable contains a key, which is like the 'id' of the activity
    //value will be a array of 3 values that describes the activity
    //[0] will be the absolute value
    //[1] will be the percent change of [0] from the base profile
    //[2] will be a boolean of whether [0] is an abnormally

    //ids of activity
    //a1: count of 4 PIR sensors / day (S111-S114)
    //a2: count of magnetic fridge / day (S116)
    //a3: count of temperature stove / day (S117)
    //a4: duration at which elderly is out (S115)
    //a5: weight of person (S118, almost constant pressure for 30 mins and variance low, get average, convert to kg)
    //a6: what time sleep (S118)
    //a7: what time wake up (S118)
    //a8: duration he sleeps (S118)
    //a9: sleep disturbances (S118), calculate mean of the 30 mins that determine he is asleep. 2XSD is upper and lower. 
    //    find outliers. determines disturbances (every consecutive outlier is 1 disturbance).

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public void setElderlyId(String elderlyId) {
        this.elderlyId = elderlyId;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setSleepingTimes(ArrayList<Calendar[]> sleepingTimes) {
        this.sleepingTimes = sleepingTimes;
    }

    public void setOutTimes(ArrayList<Calendar[]> outTimes) {
        this.outTimes = outTimes;
    }

    public void setSleepDisturbances(int sleepDisturbances) {
        this.sleepDisturbances = sleepDisturbances;
    }

    public void setHalfHourMovementTime(ArrayList<Calendar[]> halfHourMovementTime) {
        this.halfHourMovementTime = halfHourMovementTime;
    }

    public void setHalfHourMovementCount(ArrayList<Integer> halfHourMovementCount) {
        this.halfHourMovementCount = halfHourMovementCount;
    }

    public void setTestPrint(String testPrint) {
        this.testPrint = testPrint;
    }

    

    public String getActivityId() {
        return activityId;
    }

    public String getElderlyId() {
        return elderlyId;
    }

    public ArrayList<Calendar[]> getSleepingTimes() {
        return sleepingTimes;
    }

    public ArrayList<Calendar[]> getOutTimes() {
        return outTimes;
    }

    public ArrayList<Calendar[]> getHalfHourMovementTime() {
        return halfHourMovementTime;
    }

    public ArrayList<Integer> getHalfHourMovementCount() {
        return halfHourMovementCount;
    }

    public String getTestPrint() {
        return testPrint;
    }
    
    public Calendar getDate() {
        return date;
    }
    
    public long getTotalOutDuration(){
        long totalOutDuration = 0;
        for (Calendar[] cal: outTimes){
            Calendar start = cal[0];
            Calendar end = cal[1];
            totalOutDuration += (end.getTimeInMillis() - start.getTimeInMillis()) / (1000*60);
        }
        return totalOutDuration;
    }
    

    public long getTotalSleepDuration(){
        long totalSleepDuration = 0;
        for (Calendar[] cal: sleepingTimes){
            Calendar start = cal[0];
            Calendar end = cal[1];
            totalSleepDuration += (end.getTimeInMillis() - start.getTimeInMillis()) / (1000*60);
        }
        return totalSleepDuration;
    }
    
    public int getTotalOutCount(){
        return outTimes.size();
    }
    
    public int getTotalSleepCount(){
        return sleepingTimes.size();
    }
    
    public int getSleepDisturbances(){
        //assumption : wake up during 12am to 6am is sleep disturbance
        int sleepDisturbance = 0;
        for (int i = 0 ; i < sleepingTimes.size() - 1; i++ ){
            Calendar[] times = sleepingTimes.get(i);
            Calendar start = times[0];
            Calendar end = times[1];
            Calendar[] times2 = sleepingTimes.get(i+1);
            Calendar start2 = times2[0];
            Calendar end2 = times2[1];
            if (end.get(Calendar.HOUR_OF_DAY) > 0 && start2.get(Calendar.HOUR_OF_DAY) < 6){
                sleepDisturbance++;
            }
        }
        return sleepDisturbance;
    }
    
    
    
}
