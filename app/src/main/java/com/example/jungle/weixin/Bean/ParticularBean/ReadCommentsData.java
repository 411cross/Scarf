package com.example.jungle.weixin.Bean.ParticularBean;

import com.example.jungle.weixin.Bean.BaseBean.Comment;

/**
 * Created by jungle on 2017/11/21.
 */

public class ReadCommentsData {
    private Comment[] comments;
    private int previous_cursor;
    private int next_cursor;
    private int total_number;

    public ReadCommentsData(Comment[] comments, int previous_cursor, int next_cursor, int total_number) {
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

    public int getPrevious_cursor() {
        return previous_cursor;
    }

    public void setPrevious_cursor(int previous_cursor) {
        this.previous_cursor = previous_cursor;
    }

    public int getNext_cursor() {
        return next_cursor;
    }

    public void setNext_cursor(int next_cursor) {
        this.next_cursor = next_cursor;
    }

    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }


}
