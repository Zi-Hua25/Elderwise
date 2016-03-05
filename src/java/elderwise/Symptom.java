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
public class Symptom {
    private final String s1 = "Appetite/Weight Change";
    private boolean s1Detect;
    private final String s2 = "Psychomotor agitation / retardation";
    private boolean s2Detect;
    private final String s3 = "Insomnia / Hypersomnia";
    private boolean s3Detect;
    private final String s4 = "Loss of interest in activities";
    private boolean s4Detect;
    
    public Symptom(){}

    public String getS1() {
        return s1;
    }

    public boolean isS1Detect() {
        return s1Detect;
    }

    public String getS2() {
        return s2;
    }

    public boolean isS2Detect() {
        return s2Detect;
    }

    public String getS3() {
        return s3;
    }

    public boolean isS3Detect() {
        return s3Detect;
    }

    public String getS4() {
        return s4;
    }

    public boolean isS4Detect() {
        return s4Detect;
    }

    public void setS1Detect(boolean s1Detect) {
        this.s1Detect = s1Detect;
    }

    public void setS2Detect(boolean s2Detect) {
        this.s2Detect = s2Detect;
    }

    public void setS3Detect(boolean s3Detect) {
        this.s3Detect = s3Detect;
    }

    public void setS4Detect(boolean s4Detect) {
        this.s4Detect = s4Detect;
    }
    
    
    
            
}
