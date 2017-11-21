package com.example.jungle.weixin.Bean;

/**
 * Created by derrickJ on 2017/11/21.
 */

public class ShortURL {

    private String url_short;
    private String url_long;
    private int type; // 0：普通网页、1：视频、2：音乐、3：活动、5、投票
    private boolean result; // 短链的可用状态，true：可用、false：不可用。

    public ShortURL(String url_short, String url_long, int type, boolean result) {
        this.url_short = url_short;
        this.url_long = url_long;
        this.type = type;
        this.result = result;
    }

    public String getUrl_short() {
        return url_short;
    }

    public void setUrl_short(String url_short) {
        this.url_short = url_short;
    }

    public String getUrl_long() {
        return url_long;
    }

    public void setUrl_long(String url_long) {
        this.url_long = url_long;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
