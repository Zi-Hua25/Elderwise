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

/**
 *
 * @author Terence
 */
public class SensorDAO {
    private static final String GET_ALL = "SELECT * from Sensor";
    private ArrayList<Sensor> sensors;

    public SensorDAO(){
        readAllSensorsFromDb();
    }
    
    public ArrayList<Sensor> getSensorsBelongingToElderly(Elderly e){
        ArrayList<Sensor> elderlySensors = new ArrayList<Sensor>();
        for (Sensor s: sensors){
            if (s.getElderlyId().equals(e.getId())){
                elderlySensors.add(s);
            }
        }
        if (elderlySensors.size() > 0){
            return elderlySensors;
        }
        return null;
    }
    
    public ArrayList<Sensor> getAllSensors(){
        return sensors;
    }
    
    public void readAllSensorsFromDb(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Sensor> s = new ArrayList<Sensor>();
        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(GET_ALL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Sensor sensor = new Sensor(rs.getString(0), rs.getString(1));
                s.add(sensor);
            }
            sensors = s;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
    }
    
}
