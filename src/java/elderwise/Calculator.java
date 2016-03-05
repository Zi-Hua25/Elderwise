/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author Terence
 */
public final class Calculator {
    
    static DescriptiveStatistics stats = new DescriptiveStatistics();
    
    public static double calculateMean(double[] numbers){
        for (double n:numbers){
            stats.addValue(n);
        }
        return stats.getMean();
    }
    
    public static double calculateMedian(double[] numbers){
        for (double n:numbers){
            stats.addValue(n);
        }
        return stats.getPercentile(0.5);
    }
    
    
    
}
