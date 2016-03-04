/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import java.util.Date;

/**
 *
 * @author Terence
 */
public class Appointment {
    private Elderly elderly;
    private Doctor doctor;
    private Date date;

    public Appointment(){}
    
    public Appointment(Elderly elderly, Doctor doctor, Date date) {
        this.elderly = elderly;
        this.doctor = doctor;
        this.date = date;
    }
    
    public Elderly getElderly(){
        return elderly;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Date getDate() {
        return date;
    }
    
    
    
}
