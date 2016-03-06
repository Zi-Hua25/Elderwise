package elderwise;

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
    
    public ElderlyDAO(){
        readAllElderlyFromDb();
    }

    public Hashtable<String, Elderly> getElderlies() {
        return elderlies;
    }
    
    public Elderly getElderly(String id){
        return elderlies.get(id);
    }
    
    public void readAllElderlyFromDb(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Hashtable<String, Elderly> elds = new Hashtable<String, Elderly>();
        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(GET_ALL);
            rs = pst.executeQuery();
            while (rs.next()) {
                Elderly elderly = new Elderly(rs.getString(0), rs.getString(1), rs.getInt(2), 
                        rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), 
                        rs.getInt(7), rs.getString(8));
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
    
    
    
    public void update(Elderly e){
        
    }
    
    
}
