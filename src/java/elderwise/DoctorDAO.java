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
import java.util.Hashtable;

/**
 *
 * @author Terence
 */
public class DoctorDAO {
    private static final String GET_ALL = "SELECT * from Doctor";
    private Hashtable<String, Doctor> doctors;
    
    public DoctorDAO(){
        readAllDoctorsFromDb();
    }
    
    public Doctor getOneDoctor(String username){
       return doctors.get(username);
    }
    
    public Hashtable<String, Doctor> getDoctors(){
        return doctors;
    }
    
    public void readAllDoctorsFromDb(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Hashtable<String, Doctor> docs = new Hashtable<String, Doctor>();
        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(GET_ALL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Doctor doc = new Doctor(rs.getString(0), rs.getString(1), rs.getString(2));
                docs.put(doc.getUsername(), doc);
            }
            doctors = docs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
        System.out.println("doctor dao ok");
    }
    
}
