package com.example.TDTUSecondhandShop.DAO;

import com.example.TDTUSecondhandShop.credentials.Credentials;
import com.example.TDTUSecondhandShop.models.PostReport;
import com.example.TDTUSecondhandShop.models.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostReportDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public PostReportDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }
    // Get a Post Report
    public PostReport getPostReport(int reportID) throws  SQLException{
        stm = conn.createStatement();
        String sql = "SELECT * FROM POSTREPORT WHERE REPORTID = '"+reportID+"'";
        resultSet = stm.executeQuery(sql);
        PostReport postReport = new PostReport(
                resultSet.getInt("REPORTID"),
                resultSet.getString("POSTID"),
                resultSet.getString("EMAILREPORT"),
                resultSet.getString("DESCRIPTION"),
                resultSet.getString("STATUS")
        );
        resultSet.close();
        stm.close();
        return postReport;
    }
    // Get all Post Report
    public List<PostReport> getAllPostReport() throws  SQLException{
        List<PostReport> postReports = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM POSTREPORT ORDER BY REPORID DESC";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            PostReport postReport = new PostReport(
                    resultSet.getInt("REPORTID"),
                    resultSet.getString("POSTID"),
                    resultSet.getString("EMAILREPORT"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getString("STATUS")
            );
            postReports.add(postReport);
        }
        resultSet.close();
        stm.close();
        return postReports;
    }
    // Get all Unread
    public List<PostReport> getAllPostReportUnread() throws  SQLException{
        List<PostReport> postReports = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM POSTREPORT WHERE STATUS = 'UNREAD' ORDER BY REPORTID DESC";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            PostReport postReport = new PostReport(
                    resultSet.getInt("REPORTID"),
                    resultSet.getString("POSTID"),
                    resultSet.getString("EMAILREPORT"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getString("STATUS")
            );
            postReports.add(postReport);
        }
        resultSet.close();
        stm.close();
        return postReports;
    }
    // Get all report of user
    public List<PostReport> getAllPostReportUser(String emailReport) throws  SQLException{
        List<PostReport> postReports = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM POSTREPORT WHERE EMAILREPORT = '"+emailReport+"' ORDER BY REPORTID DESC";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            PostReport postReport = new PostReport(
                    resultSet.getInt("REPORTID"),
                    resultSet.getString("POSTID"),
                    resultSet.getString("EMAILREPORT"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getString("STATUS")
            );
            postReports.add(postReport);
        }
        resultSet.close();
        stm.close();
        return postReports;
    }
    // Add a Post Report
    public void addPostReport(String postID, String emailReport, String description) throws SQLException{
        String sql = "INSERT INTO POSTREPORT VALUES(?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        String status = "UNREAD";
        ps.setString(1, postID);
        ps.setString(2, emailReport);
        ps.setString(3, description);
        ps.setString(4, status);
        ps.execute();
        ps.close();
    }
    //Update a Post report
    public void updatePostReport(int reportID, String status) throws  SQLException{
        String sql = "UPDATE POSTREPORT SET STATUS = ? WHERE REPORTID = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, status);
        ps.setInt(2, reportID);
        ps.execute();
        ps.close();
    }

}
