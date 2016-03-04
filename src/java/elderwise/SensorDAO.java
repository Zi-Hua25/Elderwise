/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import java.util.List;

/**
 *
 * @author Terence
 */
public class SensorDAO {
    private List<SensorReading> sensorReadings;
    
    public SensorReading[] getSensorReadingsFromOneElderly(String name){
        return new SensorReading[0]; //dummy
    }
    
    public SensorReading getOneSensorReadingFromOneElderly(String sensorId, String name){
        return new SensorReading();   //dummy
    }
}
