package com.example.jungle.weixin.Bean.BaseBean;

/**
 * Created by derrickJ on 2017/11/10.
 */

public class Comment {

    private long id;
    private String created_at;
    private String text;
    private String source;
    private User user;
    private String mid;
    private Status status;
    private Comment reply_comment;

    public Comment(long id, String created_at, String text, String source, User user, String mid, Status status, Comment reply_comment) {
        this.id = id;
        this.created_at = created_at;
        this.text = text;
        this.source = source;
        this.user = user;
        this.mid = mid;
        this.status = status;
        this.reply_comment = reply_comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Comment  getReply_comment() {
        return reply_comment;
    }

    public void setReply_comment(Comment  reply_comment) {
        this.reply_comment = reply_comment;
    }
}
