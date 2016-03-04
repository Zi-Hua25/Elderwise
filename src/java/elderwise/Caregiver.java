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
public class Caregiver {
    
    private String name;
    private String username;
    private String password;
    private List<Elderly> elderlies;

    public Caregiver(){}
    
    public Caregiver(String name, String username, String password) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Elderly> getElderlies() {
        return elderlies;
    }

    
    
}
