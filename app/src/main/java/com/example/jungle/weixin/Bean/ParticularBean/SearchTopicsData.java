package com.example.jungle.weixin.Bean.ParticularBean;

import com.example.jungle.weixin.Bean.BaseBean.Status;

/**
 * Created by jungle on 2017/11/21.
 */

public class SearchTopicsData {
    private Status[] statuses;
    private long total_number;

    public SearchTopicsData(Status[] statuses, int total_number) {
        this.statuses = statuses;
        this.total_number = total_number;
    }
}
