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
public class SensorReadingDAO {
    private static final String GET_ALL = "SELECT * from SensorReading";
    //private static final String GET_LATEST = "SELECT * from SensorReading where date >= ? and date < ?";
    private static final int START_TIME_OF_DAY = 10;
    
    private ArrayList<SensorReading> sensorReadings;
    
    public SensorReadingDAO(){
        readAllSensorReadingsFromDb();
    }
    
    public ArrayList<SensorReading> getSensorReadingsOnDate(ArrayList<Sensor> sensors, Calendar date){
        //sensors are sensors that belong to one elderly. logic in app controller
        Calendar startDate = Calendar.getInstance();
        startDate.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE), 
                START_TIME_OF_DAY, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE) + 1, 
                START_TIME_OF_DAY, 0);
        
        ArrayList<SensorReading> readings = new ArrayList<SensorReading>();

        for (SensorReading sr: sensorReadings){
            if (sr.getDate().compareTo(startDate) != 0 && sr.getDate().compareTo(endDate) == -1){
                for (Sensor s: sensors){
                    if (sr.getSensorId().equals(s.getSensorId())){
                        readings.add(sr);
                    }
                }
            }
        }
        return readings;
    }
    
    /*public SensorReading getOneSensorReadingOnDate(Sensor sensor, Calendar date){
        //sensors are sensors that belong to one elderly. logic in app controller
        return new SensorReading();   //dummy
    }*/
    
    public void readAllSensorReadingsFromDb(){

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<SensorReading> readings = new ArrayList<SensorReading>();
        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(GET_ALL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Calendar date = Calendar.getInstance();
                date.setTime(rs.getDate(1));
                SensorReading reading = new SensorReading(rs.getString(0), date, rs.getString(2));
                readings.add(reading);
            }
            sensorReadings = readings;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
        System.out.println("sensorr reading dao ok");
    }
    

}
