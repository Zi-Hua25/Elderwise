/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Terence
 */

//calculations are done PER DAY. results are PER DAY
public final class SensorInterpreter {
    
    public static double calculateSleepingHours(List<SensorReading> readings){
        return 0; //dumy
    }
    public static Date[] calculateSleepTimings(List<SensorReading> readings){
        return new Date[0];
    }
    
    public static int calculateSleepDisturbanceCount(List<SensorReading> readings){
        return 0;
    }
    public static int calculateStoveCount(List<SensorReading> readings){
        return 0;
    }
    public static int calculateFridgeDoorCount(List<SensorReading> readings){
        return 0;
    }
    public static int calculateOutdoorCount(List<SensorReading> readings){
        return 0;
    }

    public static int calculateTotalRoomVisits(List<SensorReading> readings){
        return 0;
    }
    public static int calculateWeight(List<SensorReading> readings){
        return 0;
    }

    
}
