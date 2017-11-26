package com.example.jungle.weixin.PublicUtils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.jungle.weixin.Activity.MyWebView;
import com.example.jungle.weixin.Bean.BaseBean.SharedPreUser;

import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getCurrent;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getSp;

/**
 * Created by derrickJ on 2017/11/26.
 */

public class LoginUtils {

    public static void judgeSP(Context context, int activityCode) {
        SharedPreferences sp = getSp(context);
        SharedPreUser spUser = getCurrent(sp);
        if (spUser == null) {
            Intent intent = new Intent(context, MyWebView.class);
            context.startActivity(intent);
        }
    }

}
