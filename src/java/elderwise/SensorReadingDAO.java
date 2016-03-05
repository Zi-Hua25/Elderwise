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
public class SensorReadingDAO {
    private static final String GET_ALL = "SELECT * from SensorReading";
    private static final String GET_LATEST = "SELECT * from SensorReading where date < today";
    private List<SensorReading> sensorReadings;
    
    public SensorReadingDAO(){
        readAllSensorReadingsFromDb();
    }
    
    public SensorReading[] getSensorReadingsOnDate(List<Sensor> sensor, Date date){
        //sensors are sensors that belong to one elderly. logic in app controller
        return new SensorReading[0]; //dummy
    }
    
    public SensorReading getOneSensorReadingOnDate(Sensor sensor, Date date){
        //sensors are sensors that belong to one elderly. logic in app controller
        return new SensorReading();   //dummy
    }
    
    public void readAllSensorReadingsFromDb(){}
    
    public void readSensorReadingFromDbOnDate(Date dates){
        
    }
    
    public void add(SensorReading sr){
        sensorReadings.add(sr);
    }
    
}
