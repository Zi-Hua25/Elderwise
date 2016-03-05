package elderwise;

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

    

    public AppController() {
        //take note of order
        sensorDAO = new SensorDAO();
        doctorDAO = new DoctorDAO();
        elderlyDAO = new ElderlyDAO();
        sensorReadingDAO = new SensorReadingDAO();
        appointmentDAO = new AppointmentDAO();
        caregiverDAO = new CaregiverDAO();
        activityDAO = new ActivityDAO();

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
    
    public void check(){
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
