/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Terence
 */
public class SensorDAO {

    private static final String GET_ALL = "SELECT * from Sensor";
    private ArrayList<Sensor> sensors;
    private static final File folder = new File("files/sensor");

    public SensorDAO() throws IOException {
        System.out.println("\n--------------------------");
        //readAllSensorsFromDb();
        sensors = new ArrayList<Sensor>();
        readAllSensorsFromCSV();


    }

    public void readAllSensorsFromCSV() throws FileNotFoundException, IOException {
        ArrayList<String> fileNames = new ArrayList<String>();
        System.out.println("\nDetecting sensor files...");
        for (final File fileEntry : folder.listFiles()) {
            fileNames.add(fileEntry.getName());
            System.out.println(fileEntry.getName());
        }
        System.out.println("Detecting sensor files complete!");
        System.out.println("\nRemoving unwanted files..");
        fileNames.remove(".DS_Store");
        System.out.println(".DS_Store removed!");
        System.out.println("Removal complete!");
        if (fileNames.size() != 1) {
            System.out.println("Invalid number of file. Please try again.");
        } else {
            System.out.println("\nreading " + fileNames.get(0) + " ....");
            Long start = System.currentTimeMillis();
            CSVReader reader = new CSVReader(new FileReader(folder + "/" + fileNames.get(0)));
            String[] nextLine;
            boolean firstRow = true;
            while ((nextLine = reader.readNext()) != null) {
                if (!firstRow) {
                    Sensor s = new Sensor(nextLine[0], nextLine[1]);
                    sensors.add(s);
                } else {
                    firstRow = false;
                }
            }
            //testing
            Long end = System.currentTimeMillis();
            Long diff = end - start;
            System.out.println("finished reading");
            System.out.println("time taken: " + diff / 1000.00 + " seconds");
        }
        System.out.println("\nTotal " + sensors.size() + " sensors read");
    }

    public ArrayList<Sensor> getSensorsBelongingToElderly(Elderly e) {
        ArrayList<Sensor> elderlySensors = new ArrayList<Sensor>();
        for (Sensor s : sensors) {
            if (s.getElderlyId().equals(e.getId())) {
                elderlySensors.add(s);
            }
        }
        if (elderlySensors.size() > 0) {
            return elderlySensors;
        }
        return null;
    }

    public ArrayList<Sensor> getAllSensors() {
        return sensors;
    }

    /*public void readAllSensorsFromDb(){
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
     System.out.println("sensor dao ok");
     }
     */
}
