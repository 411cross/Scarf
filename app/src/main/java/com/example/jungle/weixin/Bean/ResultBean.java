package com.example.jungle.weixin.Bean;

/**
 * Created by jungle on 2017/11/7.
 */

public class ResultBean<T> {
    public int status_code;
    public String errmsg;
    public T data;
    public String request;
    public String error_code;
    public String error;
}
