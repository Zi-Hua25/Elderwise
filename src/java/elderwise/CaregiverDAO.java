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
import java.util.Hashtable;

/**
 *
 * @author Terence
 */
public class CaregiverDAO {
    
    private static final String GET_ALL = "SELECT * from Caregiver";
    private Hashtable<String, Caregiver> caregivers;
    
    public CaregiverDAO(){
        readAllCaregiversFromDb();
    }
    
    public Caregiver getCaregiver(String username){
        return caregivers.get(username);
    }
    
    public Hashtable<String, Caregiver> getAllCaregivers(){
        return caregivers;
    }
    
    public void readAllCaregiversFromDb(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Hashtable<String, Caregiver> cgList = new Hashtable<String, Caregiver>();
        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(GET_ALL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Calendar date = Calendar.getInstance();
                date.setTime(rs.getDate(2));
                Caregiver cg = new Caregiver(rs.getString(0), rs.getString(1), rs.getString(2));
                cgList.put(cg.getUsername(), cg);
            }
            caregivers = cgList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
        System.out.println("caregiver dao ok");
        
        
        //read from assigmnet db also

    }
}
