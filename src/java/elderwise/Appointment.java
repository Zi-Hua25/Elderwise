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
public class Appointment {
    private String elderlyId;
    private String doctorId;
    private Calendar date;

    public Appointment(){}

    public Appointment(String elderlyId, String doctorId, Calendar date) {
        this.elderlyId = elderlyId;
        this.doctorId = doctorId;
        this.date = date;
    }

    public String getElderlyId() {
        return elderlyId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public Calendar getDate() {
        return date;
    }
    
    
    
}
