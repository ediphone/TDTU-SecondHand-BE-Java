package com.example.TDTUSecondhandShop.models;

public class ShopReport {
    private int reportID;
    private String emailShop;
    private String emailReport;
    private String description;
    private String status;

    public ShopReport(int reportID, String emailShop, String emailReport, String description, String status) {
        this.reportID = reportID;
        this.emailShop = emailShop;
        this.emailReport = emailReport;
        this.description = description;
        this.status = status;
    }

    public String getEmailShop() {
        return emailShop;
    }

    public void setEmailShop(String emailShop) {
        this.emailShop = emailShop;
    }

    public String getEmailReport() {
        return emailReport;
    }

    public void setEmailReport(String emailReport) {
        this.emailReport = emailReport;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    @Override
    public String toString() {
        return "ShopReport{" +
                "emailShop='" + emailShop + '\'' +
                ", emailReport='" + emailReport + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
