package com.example.TDTUSecondhandShop.models;

public class Image {
    private int imageID;
    private String postID;
    private String link;

    public Image(int imageID, String postID, String link) {
        this.imageID = imageID;
        this.postID = postID;
        this.link = link;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageID=" + imageID +
                ", postID=" + postID +
                ", link='" + link + '\'' +
                '}';
    }
}
