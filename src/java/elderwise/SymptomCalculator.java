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
public final class SymptomCalculator {
    
    //1 period = 1 day
    private static final int periodToCalculate = 14; //days
    private static final int minPeriodSleepingHours = 10;
    
    
    //for insomnia. only need abnormally for sleep disturbance and sleep duration
    

    public boolean calculateInsomnia(){
        //check for abnormallies that is present in difference activities
        //if all abnormally present in 'minPeriodSleepingHours' days out of 14 days, then symptom present
        //weigh for different activities the same
        return false;
    }
    
}
