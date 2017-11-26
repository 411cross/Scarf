package com.example.jungle.weixin.Bean.XHRBase;

/**
 * Created by derrickJ on 2017/11/26.
 */

public class ErrorData {

    private String error;
    private String error_code;
    private String request;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
