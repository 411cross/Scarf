package com.example.jungle.weixin.PublicUtils;

/**
 * Created by derrickJ on 2017/11/21.
 */

public class PicUtils {

    public static String getMiddlePic(String thumbnail) {
        String middlePic;
        if (thumbnail.contains("orj360")) {
            middlePic = thumbnail.replaceAll("orj360", "bmiddle");
        } else {
            middlePic = thumbnail.replaceAll("thumbnail", "bmiddle");
        }
        return middlePic;
    }

    public static String getOrignal(String thumbnail) {
        String originalPic;
        if (thumbnail.contains("orj360")) {
            originalPic = thumbnail.replaceAll("orj360", "large");
        } else {
            originalPic = thumbnail.replaceAll("thumbnail", "large");
        }
        return originalPic;
    }

}
