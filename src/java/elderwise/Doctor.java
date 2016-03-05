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
    private String name;
    private String username;
    private String password;

    public Doctor(){
    }
    
    public Doctor(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
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
