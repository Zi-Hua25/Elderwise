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
    
    private Elderly elderly;
    private Calendar date;

    private Calendar[] sleepingTimes;
    
    private Hashtable<String, ArrayList<Object>> activityTable;
    
    //each hashtable contains a key, which is like the 'id' of the activity
    //value will be a array of 3 values that describes the activity
    //[0] will be the absolute value
    //[1] will be the percent change of [0] from the base profile
    //[2] will be a boolean of whether [0] is an abnormally

    //ids of activity
    //a1: sleeping hours
    //a2: ...
    
    public Calendar[] getSleepingTimes(){
        return sleepingTimes;
    }
    
    public Activity(){
    }
    
    public Elderly getElderly() {
        return elderly;
    }

    public Calendar getDate() {
        return date;
    }

    public int getSleepingHours(){
        return (Integer)activityTable.get("a1").get(0);
    }
    
    public double getSleepingHoursPercentChange(){
        return (Double)activityTable.get("a1").get(1);
    }
    
    public boolean getSleepingHoursAbnormally(){
        return (Boolean)activityTable.get("a1").get(2);
    }
    
    public void setSleepingHours(int hours){
        ArrayList<Object> values = activityTable.get("a1");
        values.set(0, hours);
        activityTable.put("a1", values);
    }
    
    public void setSleepingHoursPercentChange(double percentChange){
        ArrayList<Object> values = activityTable.get("a1");
        values.set(1, percentChange);
        activityTable.put("a1", values);
    }
    
    public void setSleepingHoursAbnormally(boolean abnormal){
        ArrayList<Object> values = activityTable.get("a1");
        values.set(2, abnormal);
        activityTable.put("a1", values);
    }

    
    
    
    
            
    
}
