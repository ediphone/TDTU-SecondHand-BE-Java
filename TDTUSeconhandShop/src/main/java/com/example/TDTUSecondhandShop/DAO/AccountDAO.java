package com.example.TDTUSecondhandShop.DAO;

import com.example.TDTUSecondhandShop.credentials.Credentials;
import com.example.TDTUSecondhandShop.models.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public AccountDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }
    // Get a Email account
    public Account getAccount(String email) throws  SQLException{
        Account taikhoan = new Account();
        stm = conn.createStatement();
        String sql = "SELECT * FROM ACCOUNT WHERE EMAIL = '"+email+"'";
        resultSet = stm.executeQuery(sql);
        String role = "USER";
        while(resultSet.next()) {
            taikhoan.setEmail(resultSet.getString("EMAIL"));
            taikhoan.setRole(resultSet.getString("ROLE"));
            taikhoan.setStatus(resultSet.getString("STATUS"));
        }
        return taikhoan;
    }
    //Get all Email account
    public List<Account> getAllAccount() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM ACCOUNT WHERE ROLE <> 'ADMIN'";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            Account tk = new Account(
                    resultSet.getString("EMAIL"),
                    resultSet.getString("ROLE"),
                    resultSet.getString("STATUS")
            );
            accounts.add(tk);
        }

        resultSet.close();
        stm.close();
        return accounts;
    }
    // check Email
    public boolean checkEmail(String email) throws  SQLException{
        stm = conn.createStatement();
        String sql = "SELECT * FROM ACCOUNT WHERE EMAIL = '"+email+"'";
        resultSet = stm.executeQuery(sql);
        if(resultSet.next()){
            return false;
        }
        return true;
    }

    // Add an account
    public void addAccount(String email) throws  SQLException{
        String sql = "INSERT INTO ACCOUNT VALUES(?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        String status = "ACTIVE";
        String role = "USER";
        ps.setString(1, email);
        ps.setString(2, role);
        ps.setString(3, status);
        ps.execute();
        ps.close();
    }

    public void updateStatusAccount(String email, String status) throws  SQLException{
        String sql = "UPDATE ACCOUNT SET STATUS = ? WHERE EMAIL = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, status);
        ps.setString(2, email);
        ps.execute();
        ps.close();
    }
    public void updateAccount(String email, String role, String status) throws  SQLException{
        String sql = "UPDATE ACCOUNT SET ACCOUNT.ROLE = ? , ACCOUNT.STATUS = ? WHERE ACCOUNT.EMAIL = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, role);
        ps.setString(2, status);
        ps.setString(3, email);
        ps.execute();
        ps.close();
    }
    public List<Account> searchAccount(String string) throws  SQLException{
        List<Account> accounts = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM ACCOUNT WHERE ROLE <> 'ADMIN' AND EMAIL LIKE '%"+string+"%'";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            Account tk = new Account(
                    resultSet.getString("EMAIL"),
                    resultSet.getString("ROLE"),
                    resultSet.getString("STATUS")
            );
            accounts.add(tk);
        }

        resultSet.close();
        stm.close();
        return accounts;
    }
}
