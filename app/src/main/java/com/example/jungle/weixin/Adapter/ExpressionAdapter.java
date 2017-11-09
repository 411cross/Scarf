package com.example.jungle.weixin.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.jungle.weixin.R;


/**
 * Created by chf on 2017/11/9.
 */

public class ExpressionAdapter extends BaseAdapter{
    private Context mContext;
    private static int[] mImageIds = new int[] {R.drawable.dit,R.drawable.diu,R.drawable.div,R.drawable.diw,
            R.drawable.dix,R.drawable.diy,R.drawable.diz,R.drawable.dja,R.drawable.djb,R.drawable.djc,
            R.drawable.djd,R.drawable.dje,R.drawable.djf,R.drawable.djg,R.drawable.djh,R.drawable.dji,
            R.drawable.djj,R.drawable.djk,R.drawable.djl,R.drawable.djm,R.drawable.djn,R.drawable.djo,
            R.drawable.djp,R.drawable.djq,R.drawable.djr,R.drawable.djs,R.drawable.djt,R.drawable.dju,
            R.drawable.djv,R.drawable.djw,R.drawable.djx,R.drawable.djy,R.drawable.djz,R.drawable.dka,
            R.drawable.dkb,R.drawable.dkc,R.drawable.dkd,R.drawable.dke,R.drawable.dkf,R.drawable.dkg,
            R.drawable.dkh,R.drawable.dki,R.drawable.dkj,R.drawable.dkk,R.drawable.dkl,R.drawable.dkm,
            R.drawable.dkn,R.drawable.dko,R.drawable.dkp,R.drawable.dkq,R.drawable.dkr,R.drawable.dks,
            R.drawable.dkt,R.drawable.dku,R.drawable.dkv};
    public ExpressionAdapter(Context c){
        this.mContext = c;
    }
    public static int[] getmImageIds(){
        return mImageIds;
    }
    public int getCount(){
        return mImageIds.length;
    }
    public long getItemId(int position){
        return mImageIds[position];
    }

    public Object getItem(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mImageIds[position]);
        imageView.setTag("[" + position + "]");
        return imageView;
    }

}
