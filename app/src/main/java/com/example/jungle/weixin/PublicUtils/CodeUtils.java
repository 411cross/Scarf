package com.example.jungle.weixin.PublicUtils;

import com.example.jungle.weixin.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by derrickJ on 2017/11/21.
 */

public class CodeUtils {
//2.008CRz6CCgNPqCd973f3769bmUgGbB
    public static Map<String, String> codeMap;
    public static String mToken ="";
    public static long mID ;

    static {
        codeMap = new HashMap<String, String>();
        codeMap.put("20003", "该用户不存在");
        codeMap.put("20101", "该微博不存在");
        codeMap.put("20111", "10分钟内不能发布相同微博");
        codeMap.put("20508", "根据对方的设置，你不能进行此操作");
        codeMap.put("20521", "Hi 超人，你今天已经关注很多喽，接下来的时间想想如何让大家都来关注你吧！");
        codeMap.put("20524", "Hi 超人，你今天已经取消关注很多喽，接下来的时间想想如何让大家都来关注你吧！");

        // 开发阶段使用
        codeMap.put("21332", "Token不合法");
        codeMap.put("21314", "Token已经被使用");
        codeMap.put("21315", "Token已经过期");
        codeMap.put("21316", "Token不合法");
        codeMap.put("21317", "Token不合法");
        codeMap.put("21319", "授权关系已经被解除");
        codeMap.put("21321", "未审核的应用使用人数超过限制");
        codeMap.put("21327", "Token过期");
        codeMap.put("21335", "uid参数仅允许传入当前授权用户uid");

    }

    public static String getChineseMsg(String code) {
        String chineseMsg = codeMap.get(code);
        return chineseMsg == null ? "内部错误" : chineseMsg;
    }

//    public static void tokenExpired()

    public static Map<String, String> getCodeMap() {
        return codeMap;
    }

    public static void setCodeMap(Map<String, String> codeMap) {
        CodeUtils.codeMap = codeMap;
    }

    public static String getmToken() {
        return mToken;
    }

    public static void setmToken(String mToken) {
        CodeUtils.mToken = mToken;
    }

    public static long getmID() {
        return mID;
    }

    public static void setmID(long mID) {
        CodeUtils.mID = mID;
    }

}
