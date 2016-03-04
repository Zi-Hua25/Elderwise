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
public class AppointmentDAO {
    private List<Appointment> appointments;

    public AppointmentDAO() {
    }

    
    public AppointmentDAO(List<Appointment> appointments) {
        this.appointments = appointments;
    }
ff
    public Appointment getAppointment(Date date, Elderly elderly){
        return new Appointment();    //dummy
    }
}
