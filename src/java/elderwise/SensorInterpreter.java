/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import java.util.Calendar;
import java.util.ArrayList;

/**
 *
 * @author Terence
 */

//calculations are done PER DAY. results are PER DAY
//is to interpret excel values
public final class SensorInterpreter {
    

    public static ArrayList<Calendar[]> calculateSleepTimings(ArrayList<SensorReading> readings){
        Calendar[] sleepingTime = new Calendar[2];
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        sleepingTime[0] = start;
        sleepingTime[1] = end;
        ArrayList<Calendar[]> sleepingTimes = new ArrayList<Calendar[]>();
        sleepingTimes.add(sleepingTime);
        return sleepingTimes;
    }
    
    public static ArrayList<Calendar[]> calculateSleepDisturbances(ArrayList<SensorReading> readings){
        //return when woke up when disturbed, and when sleep back
        Calendar[] sleepingTime = new Calendar[2];
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        sleepingTime[0] = start;
        sleepingTime[1] = end;
        ArrayList<Calendar[]> sleepingTimes = new ArrayList<Calendar[]>();
        sleepingTimes.add(sleepingTime);
        return sleepingTimes;
    }
   
    public static ArrayList<Calendar[]> calculateOutdoorCount(ArrayList<SensorReading> readings){
        //return when go out, when come back
        Calendar[] sleepingTime = new Calendar[2];
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        sleepingTime[0] = start;
        sleepingTime[1] = end;
        ArrayList<Calendar[]> sleepingTimes = new ArrayList<Calendar[]>();
        sleepingTimes.add(sleepingTime);
        return sleepingTimes;
    }

    public static int[] calculateTotalRoomVisits(ArrayList<SensorReading> readings){
        //[0] =
        //[1] =
        //[2] =
        //[3] =
        int[] rooms = new int[4];
        return rooms;
    }
    

    
}
