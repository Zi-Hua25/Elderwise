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
public class Doctor {
    
    private String username;
    private String password;
    private String name;
    
    public Doctor(){
    }
    
    public Doctor(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
      
    public String getName(){
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    
    
}
