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
public class SensorReadingDAO {
    private static final String GET_ALL = "SELECT * from SensorReading";
    private static final String GET_LATEST = "SELECT * from SensorReading where date < today";
    private List<SensorReading> sensorReadings;
    
    public SensorReading[] getSensorReadingsFromOneElderly(String name){
        return new SensorReading[0]; //dummy
    }
    
    public SensorReading getOneSensorReadingFromOneElderly(String sensorId, String name){
        return new SensorReading();   //dummy
    }
    
    public void readAllSensorReadings(){}
    
    public void readSensorReadingOneDayBefore(){
        //read all row from all sensor
        //creates sensor objects and add to list    
    }
}
