package com.example.jungle.weixin.Bean;

/**
 * Created by chf on 2017/11/22.
 */

public class HotTopic {
    private String title;
    private String content;
    private int imageId;

    public HotTopic(){
    }
    public HotTopic(String title,String content,int image){
        this.title = title;
        this.content = content;
        this.imageId  = image;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int image) {
        this.imageId = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
