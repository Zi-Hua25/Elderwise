/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import java.util.Calendar;

/**
 *
 * @author Terence
 */
public class SensorReading {
    private String sensorId;
    private Calendar date;
    private String value;
    
    public SensorReading(){}

    public SensorReading(String sensorId, Calendar date, String value) {
        this.sensorId = sensorId;
        this.date = date;
        this.value = value;
    }

    public String getSensorId() {
        return sensorId;
    }

    public Calendar getDate() {
        return date;
    }

    public String getValue() {
        return value;
    }
    
    
}
