package com.example.TDTUSecondhandShop.DAO;

import com.example.TDTUSecondhandShop.credentials.Credentials;
import com.example.TDTUSecondhandShop.models.Type;
import com.example.TDTUSecondhandShop.models.Video;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public TypeDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }
    // Get all Type
    public List<Type> getAllType() throws  SQLException{
        List<Type> types = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM TYPES";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            Type type = new Type(
                    resultSet.getString("TYPEID"),
                    resultSet.getString("NAME")
            );
            types.add(type);
        }
        resultSet.close();
        stm.close();
        return types;
    }
    // Add a Type
    public void addType(String typeID, String name) throws SQLException{
        String sql = "INSERT INTO TYPES VALUES(?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, typeID);
        ps.setString(2, name);
        ps.execute();
        ps.close();
    }
    //Update a Type
    public void updateType(String typeID, String name)throws SQLException{
        String sql = "UPDATE TYPES SET NAME = ? WHERE TYPEID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, typeID);
        ps.execute();
        ps.close();
    }
    //Delete a type
    public void deleteType(String typeID) throws SQLException{
        String sql = "DELETE FROM TYPES WHERE TYPEID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, typeID);
        ps.execute();
        ps.close();
    }
}
