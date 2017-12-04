package com.example.jungle.weixin.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.jungle.weixin.CustomControls.AppCompatSwipeBack;
import com.example.jungle.weixin.PublicUtils.ManagerUtils;

/**
 * Created by derrickJ on 2017/11/26.
 */

public class BaseActivity extends AppCompatSwipeBack {

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        ManagerUtils.addActivity(this);
        //将全部继承此activity的活动集中在ManagerUtils管理
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //若活动已经被销毁则无需重复回收，此时出栈
        ManagerUtils.removeActivity(this);
    }

}
