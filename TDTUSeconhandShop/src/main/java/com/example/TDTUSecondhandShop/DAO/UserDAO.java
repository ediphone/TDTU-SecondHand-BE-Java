package com.example.TDTUSecondhandShop.DAO;
import com.example.TDTUSecondhandShop.credentials.Credentials;
import com.example.TDTUSecondhandShop.models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public UserDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    // Get a User
    public User getUser(String email) throws  SQLException{
        stm = conn.createStatement();
        String sql = "SELECT * FROM USERS WHERE EMAIL = '"+email+"'";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            User user = new User(
                    resultSet.getString("EMAIL"),
                    resultSet.getString("NAME"),
                    resultSet.getString("AVATAR"),
                    resultSet.getString("GENDER"),
                    resultSet.getString("BIRTHDAY"),
                    resultSet.getString("PHONE"),
                    resultSet.getString("PERSONALEMAIL"),
                    resultSet.getString("GENDERHIDDEN"),
                    resultSet.getString("BIRTHDAYHIDDEN"),
                    resultSet.getString("PERSONALEMAILHIDDEN"),
                    resultSet.getString("PHONEHIDDEN")
            );
            return user;
        }
        resultSet.close();
        stm.close();
        return null;
    }
    // GET all user
    public List<User> getAllUser() throws SQLException {
        List<User> users = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM USERS ";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            User user = new User(
                    resultSet.getString("EMAIL"),
                    resultSet.getString("NAME"),
                    resultSet.getString("AVATAR"),
                    resultSet.getString("GENDER"),
                    resultSet.getString("BIRTHDAY"),
                    resultSet.getString("PHONE"),
                    resultSet.getString("PERSONALEMAIL"),
                    resultSet.getString("GENDERHIDDEN"),
                    resultSet.getString("BIRTHDAYHIDDEN"),
                    resultSet.getString("PERSONALEMAILHIDDEN"),
                    resultSet.getString("PHONEHIDDEN")
            );
            users.add(user);
        }

        resultSet.close();
        stm.close();
        return users;
    }
    //GET all active user
    public List<User> getAllActiveUser() throws SQLException {
        List<User> users = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM USERS WHERE EMAIL IN (SELECT EMAIL FROM ACCOUNT WHERE STATUS = 'ACTIVE')";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            User user = new User(
                    resultSet.getString("EMAIL"),
                    resultSet.getString("NAME"),
                    resultSet.getString("AVATAR"),
                    resultSet.getString("GENDER"),
                    resultSet.getString("BIRTHDAY"),
                    resultSet.getString("PHONE"),
                    resultSet.getString("PERSONALEMAIL"),
                    resultSet.getString("GENDERHIDDEN"),
                    resultSet.getString("BIRTHDAYHIDDEN"),
                    resultSet.getString("PERSONALEMAILHIDDEN"),
                    resultSet.getString("PHONEHIDDEN")
            );
            users.add(user);
        }

        resultSet.close();
        stm.close();
        return users;
    }
    // Add a user
    public void addUser(String email, String name) throws SQLException{
        String sql = "INSERT INTO USERS VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        String genderHidden = "ACTIVE";
        String birthdayHidden = "ACTIVE";
        String personalEmailHidden = "ACTIVE";
        String phoneHidden = "ACTIVE";
        String avatar = "";
        String gender= "";
        String birthday= "";
        String phone= "";
        String personalEmail= "";
        ps.setString(1, email);
        ps.setString(2, name);
        ps.setString(3, avatar);
        ps.setString(4, gender);
        ps.setString(5, birthday);
        ps.setString(6, phone);
        ps.setString(7, personalEmail);
        ps.setString(8, genderHidden);
        ps.setString(9, birthdayHidden);
        ps.setString(10, personalEmailHidden);
        ps.setString(11, phoneHidden);
        ps.execute();
        ps.close();
    }
    //Update a user
    public void updateUser(String email, String avatar, String gender, String birthday, String phone, String personalEmail, String genderHidden, String birthdayHidden, String personalEmailHidden, String phoneHidden) throws  SQLException {
        String sql = "";
        if(avatar.trim().equals("")){
            sql = "UPDATE USERS SET GENDER = ?, BIRTHDAY = ?, PHONE = ?, PERSONALEMAIL = ?, GENDERHIDDEN = ?, BIRTHDAYHIDDEN = ?, PERSONALEMAILHIDDEN = ?, PHONEHIDDEN = ? WHERE EMAIL = ?";
        }else{
            sql = "UPDATE USERS SET GENDER = ?, BIRTHDAY = ?, PHONE = ?, PERSONALEMAIL = ?, GENDERHIDDEN = ?, BIRTHDAYHIDDEN = ?, PERSONALEMAILHIDDEN = ?, PHONEHIDDEN = ?, AVATAR = ? WHERE EMAIL = ?";
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, gender);
        ps.setString(2, birthday);
        ps.setString(3, phone);
        ps.setString(4, personalEmail);
        ps.setString(5, genderHidden);
        ps.setString(6, birthdayHidden);
        ps.setString(7, personalEmailHidden);
        ps.setString(8, phoneHidden);
        if(avatar.trim().equals("")){
            ps.setString(9,email);
        }else{
            ps.setString(9, avatar);
            ps.setString(10, email);
        }
        ps.executeUpdate();
        ps.close();
    }

    public List<User> searchUSer(String string) throws SQLException {
        List<User> users = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM USERS WHERE EMAIL IN (SELECT EMAIL FROM ACCOUNT WHERE ROLE <> 'ADMIN' AND STATUS = 'ACTIVE') AND EMAIL LIKE '%"+string+"%'";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            User user = new User(
                    resultSet.getString("EMAIL"),
                    resultSet.getString("NAME"),
                    resultSet.getString("AVATAR"),
                    resultSet.getString("GENDER"),
                    resultSet.getString("BIRTHDAY"),
                    resultSet.getString("PHONE"),
                    resultSet.getString("PERSONALEMAIL"),
                    resultSet.getString("GENDERHIDDEN"),
                    resultSet.getString("BIRTHDAYHIDDEN"),
                    resultSet.getString("PERSONALEMAILHIDDEN"),
                    resultSet.getString("PHONEHIDDEN")
            );
            users.add(user);
        }

        resultSet.close();
        stm.close();
        return users;
    }
}
