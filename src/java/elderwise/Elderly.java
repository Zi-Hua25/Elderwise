package elderwise;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Terence
 */
public class Elderly {
    private String name;
    private int age;
    private int mobile;
    private String email;
    private String gender;
    private String nokName;
    private int nokMobile;
    private String risk;
    private Profile profile;
    private Symptom symptom;

   

    public Elderly() {
   
    }

    public Elderly(String name, int age, int mobile, String email, String gender, String nokName, int nokMobile, String risk) {
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.email = email;
        this.gender = gender;
        this.nokName = nokName;
        this.nokMobile = nokMobile;
        this.risk = risk;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getNokName() {
        return nokName;
    }

    public int getNokMobile() {
        return nokMobile;
    }

    public Profile getProfile() {
        return profile;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public String getRisk() {
        return risk;
    }
    
    public void setRisk(String risk){
        this.risk = risk;
    }
    
}
