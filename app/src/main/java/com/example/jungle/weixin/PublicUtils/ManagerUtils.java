package com.example.jungle.weixin.PublicUtils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.example.jungle.weixin.Activity.MyWebView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by derrickJ on 2017/11/26.
 */

public class ManagerUtils extends Application {

    private static List<Activity> managerList = new LinkedList<>();
    public static void addActivity(Activity a){
        managerList.add(a);
    }
    // 关闭至 TotalActivity设置标志位2表示来自UserManager
    public static void exit() {
        try {
            for (int i = 0; i < managerList.size(); i++) {
                Activity activity = managerList.get(i);
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
    public static void removeActivity(Activity activity){
        managerList.remove(activity);
    }

    //杀进程
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    public static void jumpToAuthorize(Context context) {
        Intent intent = new Intent(context, MyWebView.class);
        context.startActivity(intent);
    }

}

