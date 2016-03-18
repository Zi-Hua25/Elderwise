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
import java.util.Calendar;
import java.util.Hashtable;

/**
 *
 * @author Terence
 */
public class CaregiverDAO {
    
    private static final String GET_ALL = "SELECT * from Caregiver";
    private static final String GET_ALL_ASSIGNMENT = "SELECT * from Assignment";
    private Hashtable<String, Caregiver> caregivers;
    private static final File caregiverFolder = new File("files/caregiver");
    private static final File assignmentFolder = new File("files/assignment");
    
    public CaregiverDAO() throws IOException{
        //readAllCaregiversFromDb();
        System.out.println("\n--------------------------");
        caregivers = new Hashtable<String, Caregiver>();
        readAllCaregiverFromCSV();
        readAllCaregiverAssignmentFromCSV();
    }
    
    public void readAllCaregiverFromCSV() throws FileNotFoundException, IOException{
        ArrayList<String> fileNames = new ArrayList<String>();
        System.out.println("\nDetecting caregiver files...");
        for (final File fileEntry : caregiverFolder.listFiles()) {
            fileNames.add(fileEntry.getName());
            System.out.println(fileEntry.getName());
        }
        System.out.println("Detecting caregiver files complete!");
        System.out.println("\nRemoving unwanted files..");
        fileNames.remove(".DS_Store");
        System.out.println(".DS_Store removed!");
        System.out.println("Removal complete!");
        if (fileNames.size() != 1) {
            System.out.println("Invalid number of file. Please try again.");
        } else {
            System.out.println("\nreading " + fileNames.get(0) + " ....");
            Long start = System.currentTimeMillis();
            CSVReader reader = new CSVReader(new FileReader(caregiverFolder + "/" + fileNames.get(0)));
            String[] nextLine;
            boolean firstRow = true;
            while ((nextLine = reader.readNext()) != null) {
                if (!firstRow) {
                    Caregiver c = new Caregiver(nextLine[0], nextLine[1], nextLine[2]);
                    caregivers.put(c.getUsername(), c);
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
        System.out.println("\nTotal " + caregivers.size() + " caregivers read");
    
    }
    
    public void readAllCaregiverAssignmentFromCSV() throws FileNotFoundException, IOException{
        ArrayList<String> fileNames = new ArrayList<String>();
        System.out.println("\nDetecting caregiver asssignment files...");
        for (final File fileEntry : assignmentFolder.listFiles()) {
            fileNames.add(fileEntry.getName());
            System.out.println(fileEntry.getName());
        }
        System.out.println("Detecting caregiver asasignment files complete!");
        System.out.println("\nRemoving unwanted files..");
        fileNames.remove(".DS_Store");
        System.out.println(".DS_Store removed!");
        System.out.println("Removal complete!");
        int count = 0;
        if (fileNames.size() != 1) {
            System.out.println("Invalid number of files. Please try again.");
        } else {
            System.out.println("\nreading " + fileNames.get(0) + " ....");
            Long start = System.currentTimeMillis();
            CSVReader reader = new CSVReader(new FileReader(assignmentFolder + "/" + fileNames.get(0)));
            String[] nextLine;
            boolean firstRow = true;
            
            while ((nextLine = reader.readNext()) != null) {
                if (!firstRow) {
                    String caregiverusername = nextLine[0];
                    String elderlyid = nextLine[1];
                    if (caregivers.containsKey(caregiverusername)){
                        ArrayList<String> elderlyIds = caregivers.get(caregiverusername).getElderlyIds();
                        elderlyIds.add(elderlyid);
                    } else {
                        ArrayList<String> elderlyIds = new ArrayList<String>();
                        elderlyIds.add(elderlyid);
                        Caregiver c = this.getCaregiver(caregiverusername);
                        c.setElderlyIds(elderlyIds);
                        caregivers.put(c.getUsername(), c);
                    }
                    count++;
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
        
        System.out.println("\nTotal " + count + " caregiver assignment read");
        
    
    }
    
    
    public Caregiver getCaregiver(String username){
        return caregivers.get(username);
    }
  
    
    public Hashtable<String, Caregiver> getAllCaregivers(){
        return caregivers;
    }
    
    /*
    public void readAllCaregiversFromDb(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Hashtable<String, Caregiver> cgList = new Hashtable<String, Caregiver>();
        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(GET_ALL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Calendar date = Calendar.getInstance();
                date.setTime(rs.getDate(3));
                Caregiver cg = new Caregiver(rs.getString(1), rs.getString(2), rs.getString(4));
                cgList.put(cg.getUsername(), cg);
            }
            caregivers = cgList;
            System.out.println(caregivers);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
        System.out.println("caregiver dao ok");
        
        
        conn = null;
        pst = null;
        rs = null;
        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(GET_ALL_ASSIGNMENT);
            rs = pst.executeQuery();
            while (rs.next()) {
                String caregiverId = rs.getString(1);
                System.out.println(caregivers);
                Caregiver cg = caregivers.get(caregiverId);
                ArrayList<String> elderlyIds = cg.getElderlyIds();
                elderlyIds.add(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
        
        System.out.println("assignment caregiver elderly ok");

    }
    */
}
