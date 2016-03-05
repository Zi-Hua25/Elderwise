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
    private List<Sensor> sensors;
    
    public SensorDAO(){
        readAllSensorsFromDb();
    }
    
    public List<Sensor> getSensorsBelongingToElderly(Elderly e){
        return null;
    }
    
    public List<Sensor> getAllSensors(){
        return sensors;
    }
    
    public void readAllSensorsFromDb(){
    
    }
    
    public void add(Sensor s){
        sensors.add(s);
    }
}
