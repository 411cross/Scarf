package com.example.jungle.weixin.Bean.ParticularBean;

import com.example.jungle.weixin.Bean.BaseBean.SharedPreUser;

/**
 * Created by derrickJ on 2017/11/27.
 */

public class SPData {

    private SharedPreUser data;

    public SPData(SharedPreUser data) {
        this.data = data;
    }

    public SharedPreUser getData() {
        return data;
    }

    public void setData(SharedPreUser data) {
        this.data = data;
    }

}
