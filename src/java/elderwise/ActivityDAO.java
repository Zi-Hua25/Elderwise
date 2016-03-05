/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;


import java.util.ArrayList;
import java.util.Calendar;
/**
 *
 * @author Terence
 */
public class ActivityDAO {

    private ArrayList<Activity> activities;
    
    public Activity getOneElderlyActivityByDate(Elderly elderly, Calendar date){
        return new Activity();
    }
    
    public ArrayList<Activity> getAllActivities(){
        return activities;
    }

    public void add(Activity activity){
        activities.add(activity);
    } 
    
   
}
