/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author Terence
 */
public class DoctorDAO {
    private static final String GET_ALL = "SELECT * from Doctor";
    private Hashtable<String, Doctor> doctors;
    public DoctorDAO() throws IOException{
        //readAllDoctorsFromDb();
        System.out.println("\n--------------------------");
        doctors = new Hashtable<String, Doctor>();
        readAllDocotorsFromCSV();
    }
    
    public void readAllDocotorsFromCSV() throws FileNotFoundException, IOException{
        String url = Thread.currentThread().getContextClassLoader().getResource("../files/doctor").getPath();
        File folder = new File(url); 
        
        ArrayList<String> fileNames = new ArrayList<String>();
        System.out.println("\nDetecting doctor files...");
        for (final File fileEntry : folder.listFiles()) {
            fileNames.add(fileEntry.getName());
            System.out.println(fileEntry.getName());
        }
        System.out.println("Detecting doctor files complete!");
        System.out.println("\nRemoving unwanted files..");
        fileNames.remove(".DS_Store");
        System.out.println(".DS_Store removed!");
        System.out.println("Removal complete!");
        if (fileNames.size() != 1) {
            System.out.println("Invalid number of files. Please try again.");
        } else {
            System.out.println("\nreading " + fileNames.get(0) + " ....");
            Long start = System.currentTimeMillis();
            CSVReader reader = new CSVReader(new FileReader(folder + "/" + fileNames.get(0)));
            String[] nextLine;
            boolean firstRow = true;
            
            while ((nextLine = reader.readNext()) != null) {
                if (!firstRow) {
                    Doctor d = new Doctor(nextLine[0], nextLine[1], nextLine[2]);
                    doctors.put(d.getUsername(), d);
                } else {
                    firstRow = false;
                }
            }
            //testing
            Long end = System.currentTimeMillis();
            Long diff = end - start;
            System.out.println("finished reading");
            System.out.println("time taken: " + diff / 1000.00 + " seconds");
        }
        
        System.out.println("\nTotal " + doctors.size() + " doctos read");
        
    
    }
    
    public Doctor getOneDoctor(String username){
       return doctors.get(username);
    }
    
    public Hashtable<String, Doctor> getDoctors(){
        return doctors;
    }
    /*
    public void readAllDoctorsFromDb(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Hashtable<String, Doctor> docs = new Hashtable<String, Doctor>();
        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(GET_ALL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Doctor doc = new Doctor(rs.getString(1), rs.getString(2), rs.getString(3));
                docs.put(doc.getUsername(), doc);
            }
            doctors = docs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
        System.out.println("doctor dao ok");
    }
    
    */
}
