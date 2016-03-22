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
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Terence
 */
public class SensorReadingDAO {

    private static final String GET_ALL = "SELECT * from SensorReading";
    //private static final String GET_LATEST = "SELECT * from SensorReading where date >= ? and date < ?";
    private static final int START_TIME_OF_DAY = 10;
    private ArrayList<SensorReading> sensorReadings;
    //first key is the elderly, second key is the date, without time
    private Hashtable<String, Hashtable<String, ArrayList<SensorReading>>> sensorReadingTable;
    private SensorDAO sensorDAO;
    private ArrayList<String> filesNamesRead;

    public SensorReadingDAO(SensorDAO sensorDAO) throws IOException, FileNotFoundException, ParseException {
        System.out.println("\n--------------------------");
        
        sensorReadings = new ArrayList<SensorReading>();
        sensorReadingTable = new Hashtable<String, Hashtable<String, ArrayList<SensorReading>>>();
        this.sensorDAO = sensorDAO;
        filesNamesRead = new ArrayList<String>();
        readAllSensorReadingsFromCSV(); 

    }
     
    public Hashtable<String, Hashtable<String, ArrayList<SensorReading>>> getAllReadings(){
        return sensorReadingTable;
    }

    public ArrayList<SensorReading> getSensorReadingsOnDate(String elderlyId, Calendar date) throws ParseException {
        if (sensorReadingTable.containsKey(elderlyId)){
            Hashtable<String, ArrayList<SensorReading>> elderlyTable = sensorReadingTable.get(elderlyId);
            DateFormat format = new SimpleDateFormat("yyMMdd");
            String dateToCheck = format.format(date.getTime());
            //System.out.println(dateToCheck + " to check");
            //for (String key: elderlyTable.keySet()){
              //  System.out.println(key);
            //}
            if (elderlyTable.containsKey(dateToCheck)){
                ArrayList<SensorReading> readings = (ArrayList<SensorReading>) elderlyTable.get(dateToCheck);
               // System.out.println("found");
                return readings;
            } else {
                //System.out.println("never found");
                return null;
            }
        }
        System.out.println(elderlyId);
        return null;
    }
    public Hashtable<Calendar,ArrayList<SensorReading>> getSensorReadingsOnDates(String elderlyId, Calendar start, Calendar end) {
        if (sensorReadingTable.containsKey(elderlyId)){
            
            Hashtable<Calendar, ArrayList<SensorReading>> readingOnDates = new Hashtable<Calendar, ArrayList<SensorReading>>();
            Hashtable<String, ArrayList<SensorReading>> elderlyTable = sensorReadingTable.get(elderlyId);
            
            if (elderlyTable.containsKey(start) && elderlyTable.containsKey(end)){
                while (start.before(end) || start.equals(end)){
                    readingOnDates.put(start, elderlyTable.get(start));
                    start.add(Calendar.DAY_OF_MONTH, 1);
                }
                return readingOnDates;
            } else {
                return null;
            }
        }

        return null;
    }


    /*public SensorReading getOneSensorReadingOnDate(Sensor sensor, Calendar date){
     //sensors are sensors that belong to one elderly. logic in app controller
     return new SensorReading();   //dummy
     }*/
    public void readAllSensorReadingsFromCSV() throws FileNotFoundException, IOException, ParseException {
        String url = Thread.currentThread().getContextClassLoader().getResource("../files/readings").getPath();
        File folder = new File(url); 
        ArrayList<String> fileNames = new ArrayList<String>();
        System.out.println("\nDetecting sensor reading files...");
        for (final File fileEntry : folder.listFiles()) {
            fileNames.add(fileEntry.getName());
            System.out.println(fileEntry.getName());
        }
        System.out.println("Detecting sensor reading files complete!");
        System.out.println("\nRemoving unwanted files..");
        fileNames.remove(".DS_Store");
        System.out.println(".DS_Store removed!");
        System.out.println("Removal complete!");
        //read CSV file, populate sensor reading dao
        //one file is one elderly for one month (assuming)
        for (String filename : fileNames) {
            
            System.out.println("\nreading " + filename + " ....");
            Long start = System.currentTimeMillis();
            String[] splittedValues = filename.split("_");
            String sensorId = splittedValues[2].split("\\.")[0];
            String elderlyId = "";
            System.out.println("walll");
            System.out.println(sensorDAO);
            for (Sensor s : sensorDAO.getAllSensors()) {
                if (s.getSensorId().equals(sensorId)) {
                    elderlyId = s.getElderlyId();
                }
            }
            if (!(elderlyId.equals(""))) {
                Hashtable<String, ArrayList<SensorReading>> elderlyTable
                        = new Hashtable<String, ArrayList<SensorReading>>();
                if (sensorReadingTable.contains(elderlyId)){
                    elderlyTable = sensorReadingTable.get(elderlyId);
                }

                CSVReader reader = new CSVReader(new FileReader(folder + "/" + filename)); 
                String[] nextLine;
                boolean firstRow = true;
                Date currentDateRead = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                while ((nextLine = reader.readNext()) != null) {
                    if (!firstRow) {
                        try {
                            format.applyPattern("yyyy-MM-dd HH:mm:ss");
                            
                            String dateRead = nextLine[1];
                            Date date = format.parse(dateRead);
                            Calendar dateCal = Calendar.getInstance();
                            dateCal.setTime(date);
                            
                            format.applyPattern("yyMMdd");
                            
                            String dateToCheck = format.format(date);
                            
                           
                            //System.out.println(dateToCheck + " < date to check");
                            //Calendar currentDateReadCal = Calendar.getInstance();
                            //currentDateReadCal.setTime(currentDateRead);
                            
                            SensorReading s = new SensorReading(nextLine[0], dateCal, 
                                    nextLine[2], nextLine[3], nextLine[4], nextLine[5], 
                                    nextLine[6], nextLine[7]);
                            if (elderlyTable.containsKey(dateToCheck)){
                                ArrayList<SensorReading> readingList = elderlyTable.get(dateToCheck);
                                readingList.add(s);
                                //elderlyTable.put(currentDateReadCal, readingList);
                            } else {
                                ArrayList<SensorReading> newReadingList = new ArrayList<SensorReading>();
                                newReadingList.add(s);
                                elderlyTable.put(dateToCheck, newReadingList);
                            }
                        } catch (ParseException ex) {
                            System.out.println("error parsing date");
                            Logger.getLogger(SensorReading.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        firstRow = false;
                    }
                }

                //testing
                Long end = System.currentTimeMillis();
                Long diff = end - start;
                System.out.println("finished reading");
                System.out.println("time taken: " + diff / 1000.00 + " seconds");
                
                sensorReadingTable.put(elderlyId, elderlyTable);

            } else {
                System.out.println("unable to identify which elderly does sensor " + sensorId + " belongs to. Reading file ABORTED");
            }
            

        }
        
        System.out.println("\nTotal " + sensorReadingTable.size() + " sensor reading read");

    }


    /*public void readAllSensorReadingsFromDb(){

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
     }*/
}
