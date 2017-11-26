package com.example.jungle.weixin.Bean;

/**
 * Created by derrickJ on 2017/11/26.
 */

public class Base {

    private String error;
    private int error_code;
    private String request;

    public Base(String error, int error_code, String request) {
        this.error = error;
        this.error_code = error_code;
        this.request = request;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
