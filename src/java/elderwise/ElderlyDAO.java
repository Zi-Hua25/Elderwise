package elderwise;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
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
    
    private static final String GET_ALL = "SELECT * from Elderly";
    private Hashtable<String, Elderly> elderlies;
   // private static final File folder = new File("files/elderly");
    public ElderlyDAO() throws IOException{
        System.out.println("\n--------------------------");
        elderlies = new Hashtable<String, Elderly>();
        readAllElderlyFromCSV();
    }

    public Hashtable<String, Elderly> getElderlies() {
        return elderlies;
    }
    
    public Elderly getElderly(String id){
        return elderlies.get(id);
    }
    
    public void readAllElderlyFromCSV() throws FileNotFoundException, IOException{
        String url = Thread.currentThread().getContextClassLoader().getResource("../files/elderly").getPath();
        File folder = new File(url); 

        ArrayList<String> fileNames = new ArrayList<String>();
        System.out.println("\nDetecting elderly files...");
        for (File fileEntry : folder.listFiles()) {
            System.out.println("hihi");
            fileNames.add(fileEntry.getName());
            System.out.println(fileEntry.getName());
        }
        System.out.println("Detecting elderly files complete!");
        System.out.println("\nRemoving unwanted files..");
        fileNames.remove(".DS_Store");
        System.out.println(".DS_Store removed!");
        System.out.println("Removal complete!");
        if (fileNames.size() != 1) {
            System.out.println("Invalid number of file. Please try again.");
        } else {
            System.out.println("\nreading " + fileNames.get(0) + " ....");
            Long start = System.currentTimeMillis();
            CSVReader reader = new CSVReader(new FileReader(folder + "/" + fileNames.get(0)));
            String[] nextLine;
            boolean firstRow = true;
            while ((nextLine = reader.readNext()) != null) {
                if (!firstRow) {
                    Elderly e = new Elderly(nextLine[0], nextLine[1], Integer.parseInt(nextLine[2]), 
                            Integer.parseInt(nextLine[3]),nextLine[4], nextLine[5],nextLine[6], 
                            Integer.parseInt(nextLine[7]), nextLine[8]);
                    elderlies.put(e.getId(),e);
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
        System.out.println("\nTotal " + elderlies.size() + " elderlies read");
    
    }
    
    /*public void readAllElderlyFromDb(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Hashtable<String, Elderly> elds = new Hashtable<String, Elderly>();
        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(GET_ALL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Elderly elderly = new Elderly(rs.getString(1), rs.getString(2), rs.getInt(3), 
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), 
                        rs.getInt(8), rs.getString(9));
                elds.put(elderly.getId(), elderly);
                 
            }
            elderlies = elds;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
        System.out.println("elderly dao ok");
    }
    */
    
    
    
    
    
}
