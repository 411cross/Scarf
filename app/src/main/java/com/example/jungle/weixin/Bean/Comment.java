package com.example.jungle.weixin.Bean;

/**
 * Created by derrickJ on 2017/11/10.
 */

public class Comment {

    private String avatarURL;
    private int indentity;
    private String name;
    private String comment;
    private String date;
    private int likeNum;

    public Comment(String avatarURL, int indentity, String name, String comment, String date, int likeNum) {
        this.avatarURL = avatarURL;
        this.indentity = indentity;
        this.name = name;
        this.comment = comment;
        this.date = date;
        this.likeNum = likeNum;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public int getIndentity() {
        return indentity;
    }

    public void setIndentity(int indentity) {
        this.indentity = indentity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

}
