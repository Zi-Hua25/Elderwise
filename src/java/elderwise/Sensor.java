/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

/**
 *
 * @author Terence
 */
public class Sensor {
    
    private String sensorId;
    private String elderlyId;

    public Sensor(String sensorId, String elderlyId) {
        this.sensorId = sensorId;
        this.elderlyId = elderlyId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public String getElderlyId() {
        return elderlyId;
    }
    
    
    
}
