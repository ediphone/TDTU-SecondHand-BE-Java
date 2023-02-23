package com.example.TDTUSecondhandShop.DAO;

import com.example.TDTUSecondhandShop.credentials.Credentials;
import com.example.TDTUSecondhandShop.models.Image;
import com.example.TDTUSecondhandShop.models.Video;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideoDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public VideoDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }
    // Get video
    public List<Video> getAllVideo(String postID) throws  SQLException{
        List<Video> videos = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM VIDEOS WHERE POSTID = '"+postID+"'";
        resultSet = stm.executeQuery(sql);

        while(resultSet.next()) {
            Video video = new Video(
                    resultSet.getInt("VIDEOID"),
                    resultSet.getString("POSTID"),
                    resultSet.getString("LINK"));
            videos.add(video);
        }
        resultSet.close();
        stm.close();
        return videos;
    }
    // Add a video
    public void addVideo(String postID, String link) throws SQLException{
        String sql = "INSERT INTO VIDEOS VALUES(?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, postID);
        ps.setString(2, link);
        ps.execute();
        ps.close();
    }
    //Delete a video
    public void deleteVideo(String postID) throws SQLException{
        String sql = "DELETE FROM VIDEOS WHERE POSTID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, postID);
        ps.execute();
        ps.close();
    }
}
