/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import java.util.ArrayList;

/**
 *
 * @author Terence
 */
public class Caregiver {
    

    private String username;
    private String password;
    private String name;
    private ArrayList<String> elderlyIds;

    public Caregiver(){}
    
    public Caregiver(String username, String password, String name) {
        this.username = username;
        this.password = password;
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

    public ArrayList<String> getElderlyIds() {
        return elderlyIds;
    }

    
    
}
