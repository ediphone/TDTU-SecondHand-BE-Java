package com.example.TDTUSecondhandShop.models;
import java.sql.Timestamp;

public class Post {
    private String postID;
    private String emailShop;
    private String emailBuy;
    private String type;
    private String title;
    private String price;
    private String description;
    private int rate;
    private String status;
    private Timestamp timeCreated;

    public Post(String postID, String emailShop, String emailBuy, String type, String title, String price, String description, int rate, String status, Timestamp timeCreated) {
        this.postID = postID;
        this.emailShop = emailShop;
        this.emailBuy = emailBuy;
        this.type = type;
        this.title = title;
        this.price = price;
        this.description = description;
        this.rate = rate;
        this.status = status;
        this.timeCreated = timeCreated;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getEmailShop() {
        return emailShop;
    }

    public void setEmailShop(String emailShop) {
        this.emailShop = emailShop;
    }

    public String getEmailBuy() {
        return emailBuy;
    }

    public void setEmailBuy(String emailBuy) {
        this.emailBuy = emailBuy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", emailShop='" + emailShop + '\'' +
                ", emailBuy='" + emailBuy + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", rate='" + rate + '\'' +
                ", status='" + status + '\'' +
                ", timeCreated=" + timeCreated +
                '}';
    }
}
