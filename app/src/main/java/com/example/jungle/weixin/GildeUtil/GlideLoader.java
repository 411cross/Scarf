package com.example.jungle.weixin.GildeUtil;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by jungle on 2017/11/7.
 */

public class GlideLoader {

    public static void with(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).into(imageView);
    }



}
