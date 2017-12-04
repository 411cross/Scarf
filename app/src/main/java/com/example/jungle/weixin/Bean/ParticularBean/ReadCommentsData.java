package com.example.jungle.weixin.Bean.ParticularBean;

import com.example.jungle.weixin.Bean.BaseBean.Comment;

/**
 * Created by jungle on 2017/11/21.
 */

public class ReadCommentsData {
    private Comment[] comments;
    private long previous_cursor;
    private long next_cursor;
    private long total_number;

    public ReadCommentsData(Comment[] comments, long previous_cursor, long next_cursor, long total_number) {
        this.comments = comments;
        this.previous_cursor = previous_cursor;
        this.next_cursor = next_cursor;
        this.total_number = total_number;
    }

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }

    public long getPrevious_cursor() {
        return previous_cursor;
    }

    public void setPrevious_cursor(long previous_cursor) {
        this.previous_cursor = previous_cursor;
    }

    public long getNext_cursor() {
        return next_cursor;
    }

    public void setNext_cursor(long next_cursor) {
        this.next_cursor = next_cursor;
    }

    public long getTotal_number() {
        return total_number;
    }

    public void setTotal_number(long total_number) {
        this.total_number = total_number;
    }


}
