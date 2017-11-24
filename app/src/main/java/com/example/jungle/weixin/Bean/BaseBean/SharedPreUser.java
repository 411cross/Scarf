package com.example.jungle.weixin.Bean.BaseBean;

import android.content.SharedPreferences;

/**
 * Created by chf on 2017/11/24.
 */

public class SharedPreUser {
    private String uid;
    private String acc_token;
    private String userName;
    private String pub_permission;

    public SharedPreUser(){

    }
    public SharedPreUser(String uid,String acc_token,String userName,String pub_permission){
        this.uid = uid;
        this.acc_token = acc_token;
        this.userName = userName;
        this.pub_permission = pub_permission;
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

    public String getPub_permission() {
        return pub_permission;
    }

    public void setPub_permission(String pub_permission) {
        this.pub_permission = pub_permission;
    }
}
