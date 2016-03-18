/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Terence
 */
public class SensorReading {
    private String sensorId;
    private Calendar date;
    private String doorContact;
    private String livingRoomPIR;
    private String bedRoomPIR;
    private String bed;
    private String bathroomPIR;
    private String kitchenPIR;

    public SensorReading(String sensorId, Calendar date, String doorContact, String livingRoomPIR, String bedRoomPIR, String bed, String bathroomPIR, String kitchenPIR) {
        this.sensorId = sensorId;
        this.date = date;
        this.doorContact = doorContact;
        this.livingRoomPIR = livingRoomPIR;
        this.bedRoomPIR = bedRoomPIR;
        this.bed = bed;
        this.bathroomPIR = bathroomPIR;
        this.kitchenPIR = kitchenPIR;
    }

    public String getSensorId() {
        return sensorId;
    }

    public Calendar getDate() {
        return date;
    }

    public String getDoorContact() {
        return doorContact;
    }

    public String getLivingRoomPIR() {
        return livingRoomPIR;
    }

    public String getBedRoomPIR() {
        return bedRoomPIR;
    }

    public String getBed() {
        return bed;
    }

    public String getBathroomPIR() {
        return bathroomPIR;
    }

    public String getKitchenPIR() {
        return kitchenPIR;
    }


    
    
}
