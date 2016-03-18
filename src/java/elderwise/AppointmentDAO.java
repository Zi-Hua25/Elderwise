/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ArrayList;

/**
 *
 * @author Terence
 */
public class AppointmentDAO {
    private static final String GET_ALL = "SELECT * from Appointment";
    private ArrayList<Appointment> appointments;

    public AppointmentDAO() {
        readAllAppointmentsFromDb();
    }
    
    public Appointment getAppointment(Calendar date, String elderlyId){
        for (Appointment a: appointments){
            if (a.getElderlyId().equals(elderlyId) && date.compareTo(a.getDate()) == 0){
                return a;
            }
        }
        return null;
    }
    
    public ArrayList<Appointment> getAllAppointments(){
        return appointments;
    }
    
    public void readAllAppointmentsFromDb(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Appointment> aptList = new ArrayList<Appointment>();
        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(GET_ALL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Calendar date = Calendar.getInstance();
                date.setTime(rs.getDate(3));
                Appointment apt = new Appointment(rs.getString(1), rs.getString(2), date);
                aptList.add(apt);
            }
            appointments = aptList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
        System.out.println("appointment dao ok");
    }
    
    public void add(Appointment a){
        appointments.add(a);
    }
}
