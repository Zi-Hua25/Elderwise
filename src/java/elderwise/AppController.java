package elderwise;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
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
    

    public AppController() throws IOException, FileNotFoundException, ParseException {
        //take note of order
        Long start = System.currentTimeMillis();
        System.out.println("\nsystem started. bootstrap starting.....");
        bootstrap();
        
        Long end = System.currentTimeMillis();
        System.out.println("\nbootstrap ended. Time taken: " + ((end-start)/1000.00) + " seconds.");
        

    }
    
    
    public void bootstrap() throws IOException, FileNotFoundException, ParseException{
        elderlyDAO = new ElderlyDAO();
        sensorDAO = new SensorDAO();
        sensorReadingDAO = new SensorReadingDAO(sensorDAO);
        
        //use sensor intepreter to populate activity class
        //interpretReadings();
        
        
        //doctorDAO = new DoctorDAO();
        
        //read activities and assign to elderly
        //activityDAO = new ActivityDAO();
        
        //create profile using maybe first 2-weeks of data
        //createProfiles();
        
        //appointmentDAO = new AppointmentDAO();
        
        //populate caregiver with elderly ids
        //caregiverDAO = new CaregiverDAO();
        
        
        //check for abnormally for all dates after say first 2-week, change activity class
        //checkAbnormally();
        
        //check for symptoms across all dates after say first-week, change elderly class
        //checkSymptom();
        
        
        
        
        

    }
    
    public static void interpretReadings(){
    
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
