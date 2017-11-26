package com.example.jungle.weixin.Bean.XHRBase;

/**
 * Created by derrickJ on 2017/11/26.
 */

public class XHRBaseBean<T> {

    private int status;
    private T data;
    private ErrorData exception;

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

    public ErrorData getException() {
        return exception;
    }

    public void setException(ErrorData exception) {
        this.exception = exception;
    }

}
