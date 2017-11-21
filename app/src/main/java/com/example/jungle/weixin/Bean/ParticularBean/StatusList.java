package com.example.jungle.weixin.Bean.ParticularBean;

import com.example.jungle.weixin.Bean.BaseBean.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by derrickJ on 2017/11/21.
 */

public class StatusList {

    private Status[] statuses;

    public StatusList(Status[] statuses) {
        this.statuses = statuses;
    }

    public Status[] getStatuses() {
        return statuses;
    }

    public void setStatuses(Status[] statuses) {
        this.statuses = statuses;
    }
}
