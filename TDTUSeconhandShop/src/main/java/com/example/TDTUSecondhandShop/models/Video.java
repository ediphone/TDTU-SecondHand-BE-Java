package com.example.TDTUSecondhandShop.models;

public class Video {
    private int videoID;
    private String postID;
    private String link;

    public Video(int videoID, String postID, String link) {
        this.videoID = videoID;
        this.postID = postID;
        this.link = link;
    }

    public int getVideoID() {
        return videoID;
    }

    public void setVideoID(int videoID) {
        this.videoID = videoID;
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
        return "Video{" +
                "videoID=" + videoID +
                ", postID=" + postID +
                ", link='" + link + '\'' +
                '}';
    }
}
