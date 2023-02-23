package com.example.TDTUSecondhandShop.DAO;

import com.example.TDTUSecondhandShop.credentials.Credentials;
import com.example.TDTUSecondhandShop.models.PostReport;
import com.example.TDTUSecondhandShop.models.ShopReport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopReportDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public ShopReportDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }
    // Get a Shop Report
    public ShopReport getShopReport(int reportID) throws  SQLException{
        stm = conn.createStatement();
        String sql = "SELECT * FROM SHOPREPORT WHERE REPORTID = '"+reportID+"'";
        resultSet = stm.executeQuery(sql);
        ShopReport shopReport = new ShopReport(
                resultSet.getInt("REPORTID"),
                resultSet.getString("EMAILSHOP"),
                resultSet.getString("EMAILREPORT"),
                resultSet.getString("DESCRIPTION"),
                resultSet.getString("STATUS")
        );
        resultSet.close();
        stm.close();
        return shopReport;
    }
    // Get all Post Report
    public List<ShopReport> getAllShopReport() throws  SQLException{
        List<ShopReport> shopReports = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM SHOPREPORT ORDER BY REPORTID DESC";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            ShopReport shopReport = new ShopReport(
                    resultSet.getInt("REPORTID"),
                    resultSet.getString("EMAILSHOP"),
                    resultSet.getString("EMAILREPORT"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getString("STATUS")
            );
            shopReports.add(shopReport);
        }
        resultSet.close();
        stm.close();
        return shopReports;
    }
    // Get all Unread
    public List<ShopReport> getAllShopReportUnread() throws  SQLException{
        List<ShopReport> shopReports = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM SHOPREPORT WHERE STATUS = 'UNREAD' ORDER BY REPORTID DESC";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            ShopReport shopReport = new ShopReport(
                    resultSet.getInt("REPORTID"),
                    resultSet.getString("EMAILSHOP"),
                    resultSet.getString("EMAILREPORT"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getString("STATUS")
            );
            shopReports.add(shopReport);
        }
        resultSet.close();
        stm.close();
        return shopReports;
    }
    // Get all report of user
    public List<ShopReport> getAllShopReportUser(String emailReport) throws  SQLException{
        List<ShopReport> shopReports = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM SHOPREPORT WHERE EMAILREPORT = '"+emailReport+"' ORDER BY REPORTID DESC";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            ShopReport shopReport = new ShopReport(
                    resultSet.getInt("REPORTID"),
                    resultSet.getString("EMAILSHOP"),
                    resultSet.getString("EMAILREPORT"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getString("STATUS")
            );
            shopReports.add(shopReport);
        }
        resultSet.close();
        stm.close();
        return shopReports;
    }
    // Get all report of Shop
    public List<ShopReport> getAllShopReportShop(String emailShop) throws  SQLException{
        List<ShopReport> shopReports = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM SHOPREPORT WHERE EMAILSHOP = '"+emailShop+"' ORDER BY REPORTID DESC";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            ShopReport shopReport = new ShopReport(
                    resultSet.getInt("REPORTID"),
                    resultSet.getString("EMAILSHOP"),
                    resultSet.getString("EMAILREPORT"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getString("STATUS")
            );
            shopReports.add(shopReport);
        }
        resultSet.close();
        stm.close();
        return shopReports;
    }
    // Add a Shop Report
    public void addShopReport(String emailShop, String emailReport, String description) throws SQLException{
        String sql = "INSERT INTO SHOPREPORT VALUES(?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        String status = "UNREAD";
        ps.setString(1, emailShop);
        ps.setString(2, emailReport);
        ps.setString(3, description);
        ps.setString(4, status);
        ps.execute();
        ps.close();
    }
    //Update a Shop report
    public void updateShopReport(int reportID, String status) throws  SQLException{
        String sql = "UPDATE SHOPREPORT SET STATUS = ? WHERE REPORTID = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, status);
        ps.setInt(2, reportID);
        ps.execute();
        ps.close();
    }
}
