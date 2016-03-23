package elderwise;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.stat.descriptive.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Terence
 */
public final class AppController {

    private ElderlyDAO elderlyDAO;
    private DoctorDAO doctorDAO;
    private CaregiverDAO caregiverDAO;
    private SensorReadingDAO sensorReadingDAO;
    private AppointmentDAO appointmentDAO;
    private ActivityDAO activityDAO;
    private SensorDAO sensorDAO;
    

    public AppController() throws ParseException, IOException {
        //take note of order
        Long start = System.currentTimeMillis();
        System.out.println("\nsystem started. bootstrap starting.....");
        
        bootstrap();
        
        Long end = System.currentTimeMillis();
        System.out.println("\n--------------------------");
        System.out.println("bootstrap ended. Time taken: " + ((end-start)/1000.00) + " seconds.");
        

    }
    
    public Activity getOneActivity(String elderly, String date){
        return activityDAO.getActivity(elderly, date);
    }
    
    
    public  void bootstrap() throws IOException, FileNotFoundException, ParseException{
       
            sensorDAO = new SensorDAO();
            elderlyDAO = new ElderlyDAO();
            
            sensorReadingDAO = new SensorReadingDAO(sensorDAO);

            caregiverDAO = new CaregiverDAO();
            doctorDAO = new DoctorDAO();
            
            activityDAO = new ActivityDAO();
            activityDAO.analyzeReadings(sensorReadingDAO.getAllReadings());
            
            
           
         
            
  
        //create profile using maybe first 2-weeks of data
        //createProfiles();
        
        //appointmentDAO = new AppointmentDAO();
        
        //populate caregiver with elderly ids
        
        
        
        //check for abnormally for all dates after say first 2-week, change activity class
        //checkAbnormally();
        
        //check for symptoms across all dates after say first-week, change elderly class
        //checkSymptom();
        
        
        
        
        

    }
    
    public static void interpretReadings(Calendar date, String elderlyId){
        
        System.out.println("\n--------------------------");
        System.out.println("starting to interpret readings...");
        Long start = System.currentTimeMillis();
        
        

        Long end = System.currentTimeMillis();
        System.out.println("\n--------------------------");
        System.out.println("interpretting ended. Time taken: " + ((end-start)/1000.00) + " seconds.");
        
    }
    
    public ArrayList<SensorReading> getOneDayReadingForElderly(String elderlyId, Calendar date) throws ParseException{
        return sensorReadingDAO.getSensorReadingsOnDate(elderlyId, date);
    }
    

    
    public Hashtable<String, Hashtable<String, ArrayList<SensorReading>> >getAllSensorReadings(){
        
        //test staging
        return sensorReadingDAO.getAllReadings();
    }
    
    public static void createProfiles(){
        // take 1st 2-weeks data to create profile using Calculator class
        // sleeping times need to think
        // add profile to elderly object
    }
    
    //performed 'daily'
    public static void readLatestDataFromDB(){
        // read all latest data from db
        // check for abnormally
        // check for symptom
    }
    
    public void checkAbnormally(){
        //get today's activity and compare with profile
        //if abnormally, change status in activity class
        //do necessary updates
    }
    
    public void checkSymptom(){
        //check if abnormally persist for 14 days
        //each abnormally have equal weight to identify 1 symptom
    }
    
    
    
    public static void validateCaretakerLogin(){
        
    }
    
    public static void validateDoctorLogin(){
        
    }
    
    public static void createNewCaretaker(){
        
    }
    
    public static void createNewDoctor(){
        
    }
    
    
    
            
    
    
}
