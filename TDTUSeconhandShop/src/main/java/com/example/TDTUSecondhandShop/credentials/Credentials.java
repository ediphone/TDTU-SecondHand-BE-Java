package com.example.TDTUSecondhandShop.credentials;

import java.sql.*;

public class Credentials {
    private String url;
    private String userName;
    private String passWord;
    private String dbName = "SECONDHANDSHOP";
    public Credentials() {
        this.url = "jdbc:sqlserver://localhost;databaseName=" + dbName;
        this.userName = "sa";
        this.passWord = "123456";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
