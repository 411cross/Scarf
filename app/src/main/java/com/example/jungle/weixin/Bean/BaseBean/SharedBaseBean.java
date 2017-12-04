package com.example.jungle.weixin.Bean.BaseBean;

/**
 * Created by chf on 2017/12/1.
 */

public class SharedBaseBean {
    private String uid;
    private String access_token;

    public SharedBaseBean(){

    }
    public SharedBaseBean(String uid,String access_token){
        this.access_token = access_token;
        this.uid = uid;
    }
    public String getAcc_token() {
        return access_token;
    }

    public void setAcc_token(String acc_token) {
        this.access_token = acc_token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
