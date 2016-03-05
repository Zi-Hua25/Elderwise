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
public class DoctorDAO {
    private List<Doctor> doctors;
    
    public DoctorDAO(){
        readAllDoctorsFromDb();
    }
    
    public Doctor getOneDoctor(String username){
        return new Doctor();
    }
    
    public List<Doctor> getDoctors(){
        return doctors;
    }
    
    public void readAllDoctorsFromDb(){
    
    }
    
    public void add(Doctor d){
        doctors.add(d);
    }
}
