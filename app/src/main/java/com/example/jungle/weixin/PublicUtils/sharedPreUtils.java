package com.example.jungle.weixin.PublicUtils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.jungle.weixin.Bean.BaseBean.SharedPreUser;

import java.util.ArrayList;

/**
 * Created by chf on 2017/11/24.
 */

public class sharedPreUtils {
    private static final String first_id = "first_id";
    private static final String first_token = "first_token";
    private static final String first_name = "first_name";
    private static final String first_head = "first_head";
    private static final String second_id = "second_id";
    private static final String second_token = "second_token";
    private static final String second_name = "second_name";
    private static final String second_head = "second_head";
    private static final String third_id = "third_id";
    private static final String third_token = "third_token";
    private static final String third_name = "third_name ";
    private static final String third_head = "third_head";
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
        ed.putString(first_head,user.getHead_url());
        if(temp.size() > 0){
            //如果已存在一个用户
            ed.putString(second_id,temp.get(0).getUid());
            ed.putString(second_name,temp.get(0).getUserName());
            ed.putString(second_token,temp.get(0).getAcc_token());
            ed.putString(second_head,temp.get(0).getHead_url());
            //如果以存在俩用户
            if (temp.size() > 1) {
                ed.putString(third_id,temp.get(1).getUid());
                ed.putString(third_name,temp.get(1).getUserName());
                ed.putString(third_token,temp.get(1).getAcc_token());
                ed.putString(third_head,temp.get(1).getHead_url());
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
                  sp.getString(first_name,null),sp.getString(first_head,null)));
        if(sp.getString(second_id,null)!=null)
            temp.add(new SharedPreUser(sp.getString(second_id,null),sp.getString(second_token,null),
                    sp.getString(second_name,null),sp.getString(second_head,null)));
        if(sp.getString(third_id,null)!=null)
            temp.add(new SharedPreUser(sp.getString(third_id,null),sp.getString(third_token,null),
                    sp.getString(third_name,null),sp.getString(third_head,null)));
        return temp;
    }
    public static void addUserNameAndHead(SharedPreferences sp,String name, String head){
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(first_head,head);
        ed.putString(first_name,name);
        ed.commit();
    }
    public static void deleteUser(SharedPreferences sp,String uid){
        ArrayList<SharedPreUser> temp = getAllUser(sp);
        SharedPreferences.Editor ed = sp.edit();
        int i;
        for(i = 0;i<temp.size();i++){
            if(temp.get(i).getUid().equals(uid)){
                temp.remove(i);
                break;
            }
        }
        ed.remove(first_id);
        ed.remove(first_name);
        ed.remove(first_token);
        ed.remove(second_id);
        ed.remove(second_name);
        ed.remove(second_token);
        ed.remove(third_id);
        ed.remove(third_token);
        ed.remove(third_name);
        ed.putInt(user_count,0);
        ed.commit();
        for(int c = temp.size();c>0;c--){
            addUser(sp,temp.get(c-1));
        }
    }
    public static int getUserCount(SharedPreferences sp){
        return sp.getInt(user_count,0);
    }

    public static SharedPreUser getCurrent(SharedPreferences sp){
        if(sp.getString(first_id,null)!=null){
            return new SharedPreUser(sp.getString(first_id,null),sp.getString(first_token,null),
                    sp.getString(first_name,null),sp.getString(first_head,null));
        }else
        return null;
    }
    public static void exChange(SharedPreferences sp,int index){
        ArrayList<SharedPreUser> temp = getAllUser(sp);
        SharedPreUser user = temp.get(index);
        temp.remove(index);
        temp.add(0,user);
        for(int i = temp.size();i>0;i --){
            addUser(sp,temp.get(i));
        }
    }
}
