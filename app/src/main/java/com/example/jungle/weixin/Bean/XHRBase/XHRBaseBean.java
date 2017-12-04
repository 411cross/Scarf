package com.example.jungle.weixin.Bean.XHRBase;

/**
 * Created by derrickJ on 2017/11/26.
 */

public class XHRBaseBean<T> {

    private int status;
    private T data;
    private XHRErrorData exception;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public XHRErrorData getException() {
        return exception;
    }

    public void setException(XHRErrorData exception) {
        this.exception = exception;
    }

}
