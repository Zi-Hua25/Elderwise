/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;
import java.sql.*;
import java.util.Properties;
import java.io.InputStream;
/**
 *
 * @author Terence
 */
public class ConnectionManager {
    /*private static String JDBC_DRIVER = "jdbc.driver";
    private static String JDBC_URL = "jdbc.url";
    private static String JDBC_USER = "jdbc.user";
    private static String JDBC_PASSWORD = "jdbc.password";
    private static Properties props = new Properties();*/
    
    private static final String url = "jdbc:mysql://localhost/elderwisedb";
    private static final String username = "root";
    private static final String password = "password";

    /*
    
    static {
        try {
            // a way to retrieve the data in
            // connection.properties found
            // in WEB-INF/classes
            InputStream is = ConnectionManager.class.getResourceAsStream("/connection.properties");
            props.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class.forName(props.getProperty(JDBC_DRIVER)).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    */
    
    
    /**
     * Gets a connection to the database
     *
     * @return the connection
     * @throws SQLException if an error occurs when connecting
     */
    public static Connection getConnection() throws SQLException {
        
        return DriverManager.getConnection(url,username, password);
    }

    /**
     * close the given connection, statement and resultset
     *
     * @param conn the connection object to be closed
     * @param stmt the statement object to be closed
     * @param rs   the resultset object to be closed
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static void close(Connection conn, Statement stmt) {
       
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
