package com.example.jungle.weixin.Bean.BaseBean;

/**
 * Created by derrickJ on 2017/11/21.
 */

public class Remind {

    private long status;
    private long follower;
    private long cmt;
    private long dm;
    private long mention_status;
    private long mention_cmt;
    private long notice;

    public Remind(long status, long follower, long cmt, long dm, long mention_status, long mention_cmt, long notice) {
        this.status = status;
        this.follower = follower;
        this.cmt = cmt;
        this.dm = dm;
        this.mention_status = mention_status;
        this.mention_cmt = mention_cmt;
        this.notice = notice;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getFollower() {
        return follower;
    }

    public void setFollower(long follower) {
        this.follower = follower;
    }

    public long getCmt() {
        return cmt;
    }

    public void setCmt(long cmt) {
        this.cmt = cmt;
    }

    public long getDm() {
        return dm;
    }

    public void setDm(long dm) {
        this.dm = dm;
    }

    public long getMention_status() {
        return mention_status;
    }

    public void setMention_status(long mention_status) {
        this.mention_status = mention_status;
    }

    public long getMention_cmt() {
        return mention_cmt;
    }

    public void setMention_cmt(long mention_cmt) {
        this.mention_cmt = mention_cmt;
    }

    public long getNotice() {
        return notice;
    }

    public void setNotice(long notice) {
        this.notice = notice;
    }
}
