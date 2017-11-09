package com.example.jungle.weixin.LBSApplication;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jungle.weixin.R;
import com.lzy.ninegrid.NineGridView;

/**
 * Created by chf on 2017/11/8.
 */

public class LocationApplication extends Application{
    public LocationClient mLocationClient;
    public MyLocationListner mMyLocationListenr;
    public Button mLocationResult;
    @Override
    public void onCreate(){
        super.onCreate();
        mLocationClient = new LocationClient(this.getApplicationContext());
        mMyLocationListenr = new MyLocationListner();
        mLocationClient.registerLocationListener(mMyLocationListenr);
        NineGridView.setImageLoader(new GlideImageLoader());
    }
    public class MyLocationListner implements BDLocationListener {
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());//获得当前时间
            sb.append("\nerror code : ");
            sb.append(location.getLocType());//获得erro code得知定位现状
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());//获得纬度
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());//获得经度
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {//通过GPS定位
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());//获得速度
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\ndirection : ");
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());//获得当前地址
                sb.append(location.getDirection());//获得方位
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {//通过网络连接定位
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());//获得当前地址
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());//获得经营商？
            }
            logMsg(location.getAddrStr());
            Log.i("LBS", location.getLocType()+"");
            mLocationClient.stop();
        }
    }
    private void logMsg(String sb){
        mLocationResult.setText(sb);
    }

    /** Glide 加载 */
    private class GlideImageLoader implements NineGridView.ImageLoader {
        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            Glide.with(context).load(url)//
                    .placeholder(R.drawable.bg_round_white)//
                    .error(R.drawable.bg_round_white)//
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }

}
