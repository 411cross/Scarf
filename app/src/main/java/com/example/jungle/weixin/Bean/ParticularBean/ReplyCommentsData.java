package com.example.jungle.weixin.Bean.ParticularBean;

import com.example.jungle.weixin.Bean.BaseBean.Comment;
import com.example.jungle.weixin.Bean.BaseBean.Status;
import com.example.jungle.weixin.Bean.BaseBean.User;

/**
 * Created by jungle on 2017/11/21.
 */

public class ReplyCommentsData {
    private long id;
    private long mid;
    private String created_at;
    private String text;
    private String source;
    private Comment reply_comment;
    private User user;
    private Status status;
}
