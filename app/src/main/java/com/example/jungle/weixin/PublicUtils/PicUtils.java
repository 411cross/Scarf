package com.example.jungle.weixin.PublicUtils;

/**
 * Created by derrickJ on 2017/11/21.
 */

public class PicUtils {

    public static String getMiddlePic(String thumbnail) {
        String middlePic = thumbnail.replaceAll("thumbnail", "bmiddle");
        return middlePic;
    }

    public static String getOrignal(String thumbnail) {
        String originalPic = thumbnail.replaceAll("thumbnail", "large");
        return originalPic;
    }

}
