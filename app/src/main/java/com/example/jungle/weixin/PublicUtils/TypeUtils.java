package com.example.jungle.weixin.PublicUtils;

import com.example.jungle.weixin.Bean.BaseBean.Status;

/**
 * Created by derrickJ on 2017/11/21.
 */

public class TypeUtils {

    public static int getStatusType(Status status) {

        /*

        0 - 文字
        1 - 图片
        2 - 视频
        3 - 文章
        4 - 链接
        5 - 转发

         */

        int type = 0;

        if (status.getPic_urls().size() != 0) {
            type = 1;
        }

        if (status.getRetweeted_status() != null) {
            type = 5;
        }

        return type;
    }

}
