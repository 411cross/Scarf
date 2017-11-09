package com.example.jungle.weixin.Bean;

import java.io.Serializable;

/**
 * Created by derrickJ on 2017/11/9.
 */

public class WeiboImage implements Serializable {

    private int width;
    private int height;
    private String url;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewsImage{" +
                "width=" + width +
                ", height=" + height +
                ", url='" + url + '\'' +
                '}';
    }

}
