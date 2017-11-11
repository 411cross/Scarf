package com.example.jungle.weixin.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by derrickJ on 2017/11/8.
 */

public class Weibo implements Serializable{

    private int avatarURL;
    private String identity;
    private String nickname;
    private String date;
    private String source;
    private String body;
    private int image;
    private List<WeiboImage> imageurls;
    private int type;

    public Weibo() {

    }

    public Weibo(int avatarURL, String identity, String nickname, String date, String source, String body, int image, List<WeiboImage> imageurls, int type) {
        setAvatarURL(avatarURL);
        setIdentity(identity);
        setNickname(nickname);
        setDate(date);
        setSource(source);
        setBody(body);
        setImage(image);
        setAvatarURL(image);
        setImageurls(imageurls);
        setType(type);
    }

    public int getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(int avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String recourse) {
        this.source = recourse;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<WeiboImage> getImageurls() {
        return imageurls;
    }

    public void setImageurls(List<WeiboImage> imageurls) {
        this.imageurls = imageurls;
    }

}