/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

/**
 *
 * @author Terence
 */
public class AbnormallyCalculator {
    
    //what is needed?
    private static final int sleepHoursUpperLimit = 0; 
    private static final int sleepHoursLowerLimit = 0; 
    private static final int sleepHoursConfidence = 0; 
    
    public boolean checkSleepingHoursAbnormally(Activity a){
        double hours = a.getSleepingHours();
        double[] profileSleepingHours = a.getElderly().getProfile().getSleepingHours();
        // method to use to identify abnormally??
        return false;
    }
    
    // more to come..
    
}
