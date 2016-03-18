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
import java.util.ArrayList;
import java.util.Calendar;
/**
 *
 * @author Terence
 */
public class ActivityDAO {
    private static final String GET_ALL = "SELECT * from Activity";
    private ArrayList<Activity> activities;

    
    public ActivityDAO(){
        readAllElderlyActivity();
    }
    
    public void readAllElderlyActivity(){
        //populate activities with help or elderlydao
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Activity> actList = new ArrayList<Activity>();
        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(GET_ALL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Calendar date = Calendar.getInstance();
                date.setTime(rs.getDate(3));
                Activity act = new Activity(rs.getString(1), rs.getString(2), date, rs.getString(3));
                actList.add(act);
            }
            activities = actList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
        System.out.println("activity dao ok");
    }
    
    
    
    public ArrayList<Activity> getAllActivities(){
        return activities;
    }

    public void add(Activity activity){
        activities.add(activity);
    } 
    
   
}
