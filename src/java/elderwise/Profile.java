/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import java.util.Date;

/**
 *
 * @author Terence
 */
public class Profile {
    
    private Date date;
    private double[] sleepingHours;
    //private Date[] sleepingTimes; //difficult
    private double[] sleepDisturbance;
    private double[] numTimesFridgeDoorOpen;
    private double[] numTimesOutdoor;
    private double[] numTimesStoveUsed;
    private double[] totalRoomVisits;
    private double[] weight;

    public Profile(){}

    public Profile(Date date, double[] sleepingHours, double[] sleepDisturbance, 
            double[] numTimesFridgeDoorOpen, double[] numTimesOutdoor, 
            double[] numTimesStoveUsed, double[] totalRoomVisits, double[] weight) {
        this.date = date;
        this.sleepingHours = sleepingHours;
        this.sleepDisturbance = sleepDisturbance;
        this.numTimesFridgeDoorOpen = numTimesFridgeDoorOpen;
        this.numTimesOutdoor = numTimesOutdoor;
        this.numTimesStoveUsed = numTimesStoveUsed;
        this.totalRoomVisits = totalRoomVisits;
        this.weight = weight;
    }
    

    public Date getDate() {
        return date;
    }

    public double[] getSleepingHours() {
        return sleepingHours;
    }

    public double[] getSleepDisturbance() {
        return sleepDisturbance;
    }

    public double[] getNumTimesFridgeDoorOpen() {
        return numTimesFridgeDoorOpen;
    }

    public double[] getNumTimesOutdoor() {
        return numTimesOutdoor;
    }

    public double[] getNumTimesStoveUsed() {
        return numTimesStoveUsed;
    }

    public double[] getTotalRoomVisits() {
        return totalRoomVisits;
    }

    public double[] getWeight() {
        return weight;
    }
    
    public double getMedSleepingHours() {
        return Calculator.calculateMedian(sleepingHours);
    }

    public int getMedSleepDisturbance() {
        return (int)Calculator.calculateMedian(sleepDisturbance);
    }

    public int getMedNumTimesFridgeDoorOpen() {
        return (int)Calculator.calculateMedian(numTimesFridgeDoorOpen);
    }

    public int getMedNumTimesOutdoor() {
        return (int)Calculator.calculateMedian(numTimesOutdoor);
    }

    public int getMedNumTimesStoveUsed() {
        return (int)Calculator.calculateMean(numTimesStoveUsed);
    }

    public int getMedTotalRoomVisits() {
        return (int)Calculator.calculateMedian(totalRoomVisits);
    }

    public double getMedWeight() {
        return (int)Calculator.calculateMedian(weight);
    }
    
    public double getMeanSleepingHours() {
        return Calculator.calculateMean(sleepingHours);
    }

    public int getMeanSleepDisturbance() {
        return (int)Calculator.calculateMean(sleepDisturbance);
    }

    public int getMeanNumTimesFridgeDoorOpen() {
        return (int)Calculator.calculateMean(numTimesFridgeDoorOpen);
    }

    public int getMeanNumTimesOutdoor() {
        return (int)Calculator.calculateMean(numTimesOutdoor);
    }

    public int getMeanNumTimesStoveUsed() {
        return (int)Calculator.calculateMean(numTimesStoveUsed);
    }

    public int getMeanTotalRoomVisits() {
        return (int)Calculator.calculateMean(totalRoomVisits);
    }

    public double getMeanWeight() {
        return (int)Calculator.calculateMean(weight);
    }
    
    
}
