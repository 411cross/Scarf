package com.example.jungle.weixin.Bean.ParticularBean;

import com.example.jungle.weixin.Bean.BaseBean.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by derrickJ on 2017/11/21.
 */

public class StatusList {

    private List<Status> statuses = new ArrayList<>();

    public StatusList(List<Status> statuses) {
        this.statuses = statuses;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

}
