package com.example.TDTUSecondhandShop.models;

public class PostReport {
    private int reportID;
    private String postID;
    private String emailReport;
    private String description;
    private String status;

    public PostReport(int reportID, String postID, String emailReport, String description, String status) {
        this.reportID = reportID;
        this.postID = postID;
        this.emailReport = emailReport;
        this.description = description;
        this.status = status;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
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
        return "PostReport{" +
                "postID=" + postID +
                ", emailReport='" + emailReport + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
