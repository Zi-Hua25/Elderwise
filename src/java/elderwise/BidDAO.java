/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elderwise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author terencelong.2012
 */
public class BidDAO {

    private static final String GET_ALL = "SELECT * from bid";
    private static final String INSERT = "INSERT into bid values (?,?,?,?,?)";
    private static final String DELETE = "DELETE from bid";
    private static final String DELETE_BID = "DELETE from bid WHERE sectionCode = ? AND courseCode = ? AND userId = ?";
    private static final String UPDATE_BID = "Update bid set amount = ? WHERE sectionCode = ? AND courseCode = ? AND userId = ?";
    private static final String GET_ALL_BIDS_BY_STUDENT = "SELECT * from bid where userId = ?";
    private static final String SELECT_BID = "SELECT * from bid where courseCode = ? AND sectionCode = ? AND userId = ?";
    private static final String SELECT_BID_BY_SECTION = "SELECT * from bid where courseCode = ? AND sectionCode=?";

    /*
    public ArrayList<Bid> retrieveAll() {

        ArrayList<Bid> bids = new ArrayList<Bid>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(GET_ALL);
            rs = pst.executeQuery();
            while (rs.next()) {
                bids.add(new Bid(rs.getString("sectionCode"), rs.getString("userid"), rs.getString("courseCode"), rs.getDouble("amount"), rs.getBoolean("isSuccessful")));
            }

            //test code
            System.out.println(bids.get(0).getStudent());
            System.out.println(bids.size());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
        return bids;
    }

    public void add(Bid bid) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(INSERT);
            pst.setString(1, bid.getStudent());;
            pst.setDouble(2, bid.getAmount());
            pst.setString(3, bid.getCourse());
            pst.setString(4, bid.getSection());
            pst.setBoolean(5, bid.getIsSuccessful());

            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
    }

    public void removeAll() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(DELETE);
            // rs = pst.executeQuery();
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
    }

    public void removeBid(String sectionCode, String courseCode, String userId) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(DELETE_BID);
            pst.setString(1, sectionCode);
            pst.setString(2, courseCode);
            pst.setString(3, userId);
            // rs = pst.executeQuery();
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
    }

    public static void modify(String courseCode, String sectionCode, String userId, double amount) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(UPDATE_BID);
            pst.setDouble(1, amount);
            pst.setString(2, sectionCode);
            pst.setString(3, courseCode);
            pst.setString(4, userId);
            //rs = pst.executeQuery();
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
    }

    public ArrayList<Bid> retrieveBidByStudent(String userId) {

        ArrayList<Bid> bids = new ArrayList<Bid>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(GET_ALL_BIDS_BY_STUDENT);
            pst.setString(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) {

                bids.add(new Bid(rs.getString("sectionCode"), rs.getString("userid"), rs.getString("courseCode"), rs.getDouble("amount"), rs.getBoolean("isSuccessful")));
            }

            //test code
            System.out.println(bids.get(0).getCourse() + " 1) YAY");
            System.out.println(bids.size());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
        return bids;
    }

    public int retrieveNumOfBidsOfStudent(String userId) {

        int numBids = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(GET_ALL_BIDS_BY_STUDENT);
            pst.setString(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) {
                numBids++;
            }

            //test code
            System.out.println(numBids);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
        return numBids;
    }

    public Bid retrieveOneBid(String sectionCode, String courseCode, String userId) {


        Bid bid = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(SELECT_BID);
            pst.setString(1, courseCode);
            pst.setString(2, sectionCode);
            pst.setString(3, userId);
            rs = pst.executeQuery();
            if (rs.next()) {
                bid = new Bid(rs.getString("sectionCode"), rs.getString("userid"), rs.getString("courseCode"), rs.getDouble("amount"), rs.getBoolean("isSuccessful"));

            }

            //test code
            System.out.println(bid.getAmount());



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }


        return bid;

    }

    public ArrayList<Bid> retrieveBidsBySection(String sectionCode, String courseCode) {
        ArrayList<Bid> bids = new ArrayList<Bid>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            pst = conn.prepareStatement(SELECT_BID_BY_SECTION);
            pst.setString(1, courseCode);
            pst.setString(2, sectionCode);
            rs = pst.executeQuery();
            while (rs.next()) {
                bids.add(new Bid(rs.getString("sectionCode"), rs.getString("userid"), rs.getString("courseCode"), rs.getDouble("amount"), rs.getBoolean("isSuccessful")));
            }

            //test code
            System.out.println(bids.get(0).getAmount());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, pst, rs);
        }
        return bids;
    }
    */
}
