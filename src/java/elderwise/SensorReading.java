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
public class SensorReading implements Comparable<SensorReading>{

    private String sensorId;
    private Calendar date;
    private boolean doorContact;
    private boolean livingRoomPIR;
    private boolean bedRoomPIR;
    private boolean bed;
    private boolean bathroomPIR;
    private boolean kitchenPIR;

    public SensorReading(String sensorId, Calendar date, String doorContact, String livingRoomPIR, String bedRoomPIR, String bed, String bathroomPIR, String kitchenPIR) {
        this.sensorId = sensorId;
        this.date = date;
        if (doorContact.equals("Yes")) {
            this.doorContact = true;
        } else {
            this.doorContact = false;
        }

        if (livingRoomPIR.equals("Yes")) {
            this.livingRoomPIR = true;
        } else {
            this.livingRoomPIR = false;
        }

        if (bedRoomPIR.equals("Yes")) {
            this.bedRoomPIR = true;
        } else {
            this.bedRoomPIR = false;
        }

        if (bed.equals("Yes")) {
            this.bed = true;
        } else {
            this.bed = false;
        }

        if (bathroomPIR.equals("Yes")) {
            this.bathroomPIR = true;
        } else {
            this.bathroomPIR = false;
        }

        if (kitchenPIR.equals("Yes")) {
            this.kitchenPIR = true;
        } else {
            this.kitchenPIR = false;
        }

    }

    public String getSensorId() {
        return sensorId;
    }

    public Calendar getDate() {

        return date;
    }

    public boolean getDoorContact() {
        return doorContact;
    }

    public boolean getLivingRoomPIR() {
        return livingRoomPIR;
    }

    public boolean getBedRoomPIR() {
        return bedRoomPIR;
    }

    public boolean getBed() {
        return bed;
    }

    public boolean getBathroomPIR() {
        return bathroomPIR;
    }

    public boolean getKitchenPIR() {
        return kitchenPIR;
    }

    @Override
    public int compareTo(SensorReading reading) {

        if (date.getTime().before(reading.getDate().getTime())) {
            return -1;
        }
        if (date.getTime().after(reading.getDate().getTime())) {
            return 1;
        }
        return 0;
    }

}
