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
public class Sensor {
    
    private String id;
    private Elderly elderly;

    public Sensor(String id, Elderly elderly) {
        this.id = id;
        this.elderly = elderly;
    }

    public String getId() {
        return id;
    }

    public Elderly getElderly() {
        return elderly;
    }
    
    
    
}
