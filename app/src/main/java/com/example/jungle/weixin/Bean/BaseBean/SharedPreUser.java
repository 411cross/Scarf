package com.example.jungle.weixin.Bean.BaseBean;

import android.content.SharedPreferences;

/**
 * Created by chf on 2017/11/24.
 */

public class SharedPreUser {
    private String uid;
    private String acc_token;
    private String userName;
    private String head_url;

    public SharedPreUser(){

    }
    public SharedPreUser(String uid,String acc_token,String userName,String head_url){
        this.uid = uid;
        this.acc_token = acc_token;
        this.userName = userName;
        this.head_url = head_url;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAcc_token() {
        return acc_token;
    }

    public void setAcc_token(String acc_token) {
        this.acc_token = acc_token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }
}
