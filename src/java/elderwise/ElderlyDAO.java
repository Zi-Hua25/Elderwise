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
    
    public ElderlyDAO(){
        readAllElderlyFromDb();
    }

    public List<Elderly> getElderlies() {
        return elderlies;
    }
    
    public Elderly getElderly(String id){
        for (Elderly e: elderlies){
            if (e.getId().equals(id)){
                return e;
            }
        }
        return null;
    }
    
    public void readAllElderlyFromDb(){
    
    }
    
    public void add(Elderly e){
        elderlies.add(e);
    }
    
    public void update(Elderly e){
        
    }
    
    
}
