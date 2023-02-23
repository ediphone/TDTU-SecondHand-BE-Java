package com.example.TDTUSecondhandShop.DAO;

import com.example.TDTUSecondhandShop.credentials.Credentials;
import com.example.TDTUSecondhandShop.models.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public PostDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }
    // Get a Post
    public Post getPost(String postID) throws  SQLException{
        stm = conn.createStatement();
        String sql = "SELECT * FROM POST WHERE POSTID = '"+postID+"'";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            Post post = new Post(
                    resultSet.getString("POSTID"),
                    resultSet.getString("EMAILSHOP"),
                    resultSet.getString("EMAILBUY"),
                    resultSet.getString("TYPE"),
                    resultSet.getString("TITLE"),
                    resultSet.getString("PRICE"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getInt("RATE"),
                    resultSet.getString("STATUS"),
                    resultSet.getTimestamp("TIMECREATED")
            );
            return post;
        }
        resultSet.close();
        stm.close();
        return null;
    }
    // GET all post sell
    public List<Post> getAllPostSell(String email) throws SQLException {
        List<Post> posts = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM POST WHERE EMAILSHOP = '"+email+"' AND STATUS <> 'DELETED'  ORDER BY TIMECREATED DESC ";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            Post post = new Post(
                    resultSet.getString("POSTID"),
                    resultSet.getString("EMAILSHOP"),
                    resultSet.getString("EMAILBUY"),
                    resultSet.getString("TYPE"),
                    resultSet.getString("TITLE"),
                    resultSet.getString("PRICE"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getInt("RATE"),
                    resultSet.getString("STATUS"),
                    resultSet.getTimestamp("TIMECREATED")
            );
            posts.add(post);
        }

        resultSet.close();
        stm.close();
        return posts;
    }
    // GET all post buy
    public List<Post> getAllPostBuy(String email) throws SQLException {
        List<Post> posts = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM POST WHERE EMAILBUY = '"+email+"'  ORDER BY TIMECREATED DESC ";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            Post post = new Post(
                    resultSet.getString("POSTID"),
                    resultSet.getString("EMAILSHOP"),
                    resultSet.getString("EMAILBUY"),
                    resultSet.getString("TYPE"),
                    resultSet.getString("TITLE"),
                    resultSet.getString("PRICE"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getInt("RATE"),
                    resultSet.getString("STATUS"),
                    resultSet.getTimestamp("TIMECREATED")
            );
            posts.add(post);
        }

        resultSet.close();
        stm.close();
        return posts;
    }
    // GET all post
    public List<Post> getAllPost(String email) throws SQLException {
        List<Post> posts = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM POST WHERE EMAILSHOP <> '"+email+"'  ORDER BY TIMECREATED DESC ";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            Post post = new Post(
                    resultSet.getString("POSTID"),
                    resultSet.getString("EMAILSHOP"),
                    resultSet.getString("EMAILBUY"),
                    resultSet.getString("TYPE"),
                    resultSet.getString("TITLE"),
                    resultSet.getString("PRICE"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getInt("RATE"),
                    resultSet.getString("STATUS"),
                    resultSet.getTimestamp("TIMECREATED")
            );
            posts.add(post);
        }

        resultSet.close();
        stm.close();
        return posts;
    }
    // GET all post selling
    public List<Post> getAllPostSelling(String email) throws SQLException {
        List<Post> posts = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM POST WHERE EMAILSHOP <> '"+email+"' AND STATUS = 'SELLING' ORDER BY TIMECREATED DESC ";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            Post post = new Post(
                    resultSet.getString("POSTID"),
                    resultSet.getString("EMAILSHOP"),
                    resultSet.getString("EMAILBUY"),
                    resultSet.getString("TYPE"),
                    resultSet.getString("TITLE"),
                    resultSet.getString("PRICE"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getInt("RATE"),
                    resultSet.getString("STATUS"),
                    resultSet.getTimestamp("TIMECREATED")
            );
            posts.add(post);
        }

        resultSet.close();
        stm.close();
        return posts;
    }
    // GET all post selling filter type
    public List<Post> getAllPostSellingType(String email, String typeID) throws SQLException {
        List<Post> posts = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM POST WHERE EMAILSHOP <> '"+email+"' AND STATUS = 'SELLING' AND TYPE = '"+typeID+"' ORDER BY TIMECREATED DESC ";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            Post post = new Post(
                    resultSet.getString("POSTID"),
                    resultSet.getString("EMAILSHOP"),
                    resultSet.getString("EMAILBUY"),
                    resultSet.getString("TYPE"),
                    resultSet.getString("TITLE"),
                    resultSet.getString("PRICE"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getInt("RATE"),
                    resultSet.getString("STATUS"),
                    resultSet.getTimestamp("TIMECREATED")
            );
            posts.add(post);
        }

        resultSet.close();
        stm.close();
        return posts;
    }
    // Add a POST
    public void addPost(String postID, String emailShop, String type, String title, String price, String description) throws SQLException{
        String sql = "INSERT INTO POST VALUES(?,?, NULL, ?, ?, ?, ?, NULL, 'SELLING', ?)";
        java.util.Date utilDate = new java.util.Date();
        Timestamp timeCreated = new java.sql.Timestamp(utilDate.getTime());
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, postID);
        ps.setString(2, emailShop);
        ps.setString(3, type);
        ps.setString(4, title);
        ps.setString(5, price);
        ps.setString(6, description);
        ps.setTimestamp(7, timeCreated);
        ps.execute();
        ps.close();
    }
    //Update a POST
    public void updatePost( String type, String title, String price, String description, String postID) throws  SQLException {
        String sql = "UPDATE POST SET TYPE = ?, TITLE = ?, PRICE = ?, DESCRIPTION = ? WHERE POSTID = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, type);
        ps.setString(2, title);
        ps.setString(3, price);
        ps.setString(4, description);
        ps.setString(5, postID);
        ps.executeUpdate();
        ps.close();
    }
    public void updateStatusPost(String postID, String emailBuy) throws SQLException{
        String sql = "UPDATE POST SET STATUS = 'SOLD', EMAILBUY = ? WHERE POSTID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, emailBuy);
        ps.setString(2, postID);
        ps.execute();
        ps.close();
    }
    public void updateRatingPost(String postID, int rate) throws SQLException{
        String sql = "UPDATE POST SET RATE = ? WHERE POSTID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, rate);
        ps.setString(2, postID);
        ps.execute();
        ps.close();
    }
    public void deletePost(String postID) throws SQLException{
        String sql = "UPDATE POST SET STATUS = 'DELETED' WHERE POSTID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, postID);
        ps.execute();
        ps.close();
    }
    private double calculateAverage(List<Integer> rating) {
        Integer sum = 0;
        if(!rating.isEmpty()) {
            for (Integer rate : rating) {
                sum += rate;
            }
            double avg = sum.doubleValue() / rating.size();
            double roundOff = Math.round(avg * 10.0) / 10.0;
            return roundOff;
        }
        return sum;
    }
    public double getRate(String emailShop) throws  SQLException{
        List<Integer> rating = new ArrayList();
        stm = conn.createStatement();
        String sql = "SELECT RATE FROM POST WHERE EMAILSHOP = '"+emailShop+"'";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            Integer rate = new Integer(resultSet.getInt("RATE"));
            rating.add(rate);
        }
        return calculateAverage(rating);
    }
}
