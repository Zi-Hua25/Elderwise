package elderwise;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Terence
 */
public class ElderlyDAO {
    
    private List<Elderly> elderlies;

    public List<Elderly> getElderlies() {
        return elderlies;
    }
    
    public Elderly getElderly(String name){
        return new Elderly(); //dummy
    }
    
    
}
