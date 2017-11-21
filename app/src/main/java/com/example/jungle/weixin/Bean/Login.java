package com.example.jungle.weixin.Bean;

/**
 * Created by chf on 2017/11/21.
 */

public class Login {
    private String access_token;
    private String uid;

    public Login(String access_token, String uid){
        this.access_token =  access_token;
        this.uid = uid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
