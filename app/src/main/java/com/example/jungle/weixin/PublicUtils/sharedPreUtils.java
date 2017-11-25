package com.example.jungle.weixin.PublicUtils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import com.example.jungle.weixin.Bean.BaseBean.SharedPreUser;

import java.util.ArrayList;

/**
 * Created by chf on 2017/11/24.
 */

public class sharedPreUtils {
    private static final String first_id = "first_id";
    private static final String first_token = "first_token";
    private static final String first_name = "first_name";
    private static final String first_permission = "first_permission";
    private static final String second_id = "first_id";
    private static final String second_token = "first_token";
    private static final String second_name = "first_name";
    private static final String second_permission = "first_permission";
    private static final String third_id = "first_id";
    private static final String third_token = "first_token";
    private static final String third_name = "first_name";
    private static final String third_permission = "first_permission";
    private static final String user_count = "user_count";
    public static SharedPreferences getSp(Context c){
        Context otherAppContext = null;
        try {
            otherAppContext = c.createPackageContext("com.example.jungle.weixin", Context.CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        SharedPreferences sp = otherAppContext.getSharedPreferences("Scarf", Activity.MODE_PRIVATE);
        return sp;
    }
    public static boolean addUser(SharedPreferences sp, SharedPreUser user){
        ArrayList<SharedPreUser> temp = getAllUser(sp);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(first_id,user.getUid());
        ed.putString(first_token,user.getAcc_token());
        ed.putString(first_name,user.getUserName());
        ed.putString(first_permission,user.getPub_permission());
        if(temp.size()>0){
            //如果已存在一个用户
            ed.putString(second_id,temp.get(0).getUid());
            ed.putString(second_name,temp.get(0).getUserName());
            ed.putString(second_token,temp.get(0).getAcc_token());
            ed.putString(second_permission,temp.get(0).getPub_permission());
            //如果以存在俩用户
            if (temp.size() > 1) {
                ed.putString(third_id,temp.get(1).getUid());
                ed.putString(third_name,temp.get(1).getUserName());
                ed.putString(third_token,temp.get(1).getAcc_token());
                ed.putString(third_permission,temp.get(1).getPub_permission());
            }
        }
        ed.putInt(user_count,sp.getInt(user_count,0)+1);
        ed.commit();
        return true;
    }
    public static ArrayList<SharedPreUser> getAllUser(SharedPreferences sp){
        ArrayList<SharedPreUser> temp = new ArrayList<>();
        if(sp.getString(first_id,null)!=null)
          temp.add(new SharedPreUser(sp.getString(first_id,null),sp.getString(first_token,null),
                  sp.getString(first_name,null), sp.getString(first_permission,null)));
        if(sp.getString(second_id,null)!=null)
            temp.add(new SharedPreUser(sp.getString(second_id,null),sp.getString(second_token,null),
                    sp.getString(second_name,null), sp.getString(second_permission,null)));
        if(sp.getString(third_id,null)!=null)
            temp.add(new SharedPreUser(sp.getString(third_id,null),sp.getString(third_token,null),
                    sp.getString(third_name,null), sp.getString(third_permission,null)));
        return temp;
    }
    public static void addUserName(SharedPreferences sp,String uid,String name){
        SharedPreferences.Editor ed = sp.edit();
        ArrayList<SharedPreUser> temp  = getAllUser(sp);
        for(int i = 0;i<temp.size();i++){
            if(temp.get(i).getUid().equals(uid)){
                if(i ==0)
                    ed.putString(first_name,name);
                else if(i == 1)
                    ed.putString(second_name,name);
                else
                    ed.putString(third_name,name);
                break;
            }
        }
        ed.commit();
    }
    public static void addPermission(SharedPreferences sp,String uid,String permission){
        SharedPreferences.Editor ed = sp.edit();
        ArrayList<SharedPreUser> temp  = getAllUser(sp);
        for(int i = 0;i<temp.size();i++){
            if(temp.get(i).getUid().equals(uid)){
                if(i ==0)
                    ed.putString(first_permission,permission);
                else if(i == 1)
                    ed.putString(second_permission,permission);
                else
                    ed.putString(third_permission,permission);
                break;
            }
        }
        ed.commit();

    }
    public static int getUserCount(SharedPreferences sp){
        return sp.getInt(user_count,0);
    }
}
