/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

/**
 *
 * @author Terence
 */
public class Activity {
    
    private String activityId;
    private String elderlyId;
    private Calendar date;
    private String value;

    //private Calendar[] sleepingTimes;
  
    //each hashtable contains a key, which is like the 'id' of the activity
    //value will be a array of 3 values that describes the activity
    //[0] will be the absolute value
    //[1] will be the percent change of [0] from the base profile
    //[2] will be a boolean of whether [0] is an abnormally

    //ids of activity
    //a1: count of 4 PIR sensors / day (S111-S114)
    //a2: count of magnetic fridge / day (S116)
    //a3: count of temperature stove / day (S117)
    //a4: duration at which elderly is out (S115)
    //a5: weight of person (S118, almost constant pressure for 30 mins and variance low, get average, convert to kg)
    //a6: what time sleep (S118)
    //a7: what time wake up (S118)
    //a8: duration he sleeps (S118)
    //a9: sleep disturbances (S118), calculate mean of the 30 mins that determine he is asleep. 2XSD is upper and lower. 
    //    find outliers. determines disturbances (every consecutive outlier is 1 disturbance).

    public Activity(String activityId, String elderlyId, Calendar date, String value) {
        this.activityId = activityId;
        this.elderlyId = elderlyId;
        this.date = date;
        this.value = value;
    }
    
    
    

    public Calendar getDate() {
        return date;
    }

    
    
            
    
}
