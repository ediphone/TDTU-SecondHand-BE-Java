package com.example.TDTUSecondhandShop.DAO;

import com.example.TDTUSecondhandShop.credentials.Credentials;
import com.example.TDTUSecondhandShop.models.Image;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public ImageDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }
    // Get Image
    public List<Image> getAllImage(String postID) throws  SQLException{
        List<Image> images = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM IMAGES WHERE POSTID = '"+postID+"'";
        resultSet = stm.executeQuery(sql);

        while(resultSet.next()) {
            Image image = new Image(
                    resultSet.getInt("IMAGEID"),
                    resultSet.getString("POSTID"),
                    resultSet.getString("LINK"));
            images.add(image);
        }
        resultSet.close();
        stm.close();
        return images;
    }
    // Add a Image
    public void addImage(String postID, String link) throws SQLException{
        String sql = "INSERT INTO IMAGES VALUES(?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, postID);
        ps.setString(2, link);
        ps.execute();
        ps.close();
    }
    //Delete a image
    public void deleteImage(String postID) throws SQLException{
        String sql = "DELETE FROM IMAGES WHERE POSTID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, postID);
        ps.execute();
        ps.close();
    }
}
