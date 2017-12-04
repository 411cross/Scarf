package com.example.jungle.weixin.Bean.ParticularBean;

import com.example.jungle.weixin.Bean.BaseBean.Status;
import com.example.jungle.weixin.Bean.BaseBean.User;

/**
 * Created by jungle on 2017/11/21.
 */

public class CreateDestoryCommentsData {
    private long id;
    private long mid;
    private String created_at;
    private String text;
    private String source;
    private User user;
    private Status status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
