/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import java.util.List;

/**
 *
 * @author Terence
 */
public class CaregiverDAO {
    
    private List<Caregiver> caregivers;
    
    public CaregiverDAO(){
        readAllCaregiversFromDb();
    }
    
    public Caregiver getCaregiver(String username){
        return new Caregiver();
    }
    
    public List<Caregiver> getAllCaregivers(){
        return caregivers;
    }
    
    public void readAllCaregiversFromDb(){
    
    }
    
    public void add(Caregiver c){
        caregivers.add(c);
    }

}
