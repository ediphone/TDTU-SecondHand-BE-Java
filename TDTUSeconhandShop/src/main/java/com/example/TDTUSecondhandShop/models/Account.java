package com.example.TDTUSecondhandShop.models;

public class Account {
    private String email;
    private String role;
    private String status;
    public Account() {
        this.email = "";
        this.role = "";
        this.status = "";
    }
    public Account(String email, String role, String status) {
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
