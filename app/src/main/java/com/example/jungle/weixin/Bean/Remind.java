package com.example.jungle.weixin.Bean;

/**
 * Created by derrickJ on 2017/11/21.
 */

public class Remind {

    private int status;
    private int follower;
    private int cmt;
    private int dm;
    private int mention_status;
    private int mention_cmt;
    private int notice;

    public Remind(int status, int follower, int cmt, int dm, int mention_status, int mention_cmt, int notice) {
        this.status = status;
        this.follower = follower;
        this.cmt = cmt;
        this.dm = dm;
        this.mention_status = mention_status;
        this.mention_cmt = mention_cmt;
        this.notice = notice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getCmt() {
        return cmt;
    }

    public void setCmt(int cmt) {
        this.cmt = cmt;
    }

    public int getDm() {
        return dm;
    }

    public void setDm(int dm) {
        this.dm = dm;
    }

    public int getMention_status() {
        return mention_status;
    }

    public void setMention_status(int mention_status) {
        this.mention_status = mention_status;
    }

    public int getMention_cmt() {
        return mention_cmt;
    }

    public void setMention_cmt(int mention_cmt) {
        this.mention_cmt = mention_cmt;
    }

    public int getNotice() {
        return notice;
    }

    public void setNotice(int notice) {
        this.notice = notice;
    }
}
