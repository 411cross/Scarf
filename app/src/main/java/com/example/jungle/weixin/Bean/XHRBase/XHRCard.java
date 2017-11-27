package com.example.jungle.weixin.Bean.XHRBase;

import com.example.jungle.weixin.Bean.BaseBean.Status;

/**
 * Created by derrickJ on 2017/11/26.
 */

public class XHRCard {

    private Status mblog = null;

    public XHRCard(Status mblog) {
        this.mblog = mblog;
    }

    public Status getMblog() {
        return mblog;
    }

    public void setMblog(Status mblog) {
        this.mblog = mblog;
    }

}
