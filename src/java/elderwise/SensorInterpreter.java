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
    

    public static Calendar[] calculateSleepTimings(ArrayList<SensorReading> readings){
        return new Calendar[0];
    }
    
    public static int calculateSleepDisturbanceCount(ArrayList<SensorReading> readings){
        return 0;
    }
    public static int calculateStoveCount(ArrayList<SensorReading> readings){
        return 0;
    }
    public static int calculateFridgeDoorCount(ArrayList<SensorReading> readings){
        return 0;
    }
    public static int calculateOutdoorCount(ArrayList<SensorReading> readings){
        return 0;
    }

    public static int calculateTotalRoomVisits(ArrayList<SensorReading> readings){
        return 0;
    }
    public static int calculateWeight(ArrayList<SensorReading> readings){
        return 0;
    }

    
}
