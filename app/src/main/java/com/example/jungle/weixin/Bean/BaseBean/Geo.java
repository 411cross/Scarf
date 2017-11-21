package com.example.jungle.weixin.Bean.BaseBean;

/**
 * Created by derrickJ on 2017/11/21.
 */

public class Geo {

    private String city_name;
    private String province_name;
    private String address;

    public Geo(String city_name, String province_name, String address) {
        this.city_name = city_name;
        this.province_name = province_name;
        this.address = address;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
