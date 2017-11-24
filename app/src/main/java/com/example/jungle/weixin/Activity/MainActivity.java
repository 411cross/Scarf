package com.example.jungle.weixin.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jungle.weixin.R;

public class MainActivity extends AppCompatActivity   {
    private SharedPreferences sp;
    //    private VideoView mVvv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
//            return;
        setContentView(R.layout.activity_main);
        sp = this.getSharedPreferences("Scarf", Activity.MODE_PRIVATE);

        //网络请求的例子：
        // —————————————一样———————/MyService方法，要设置URL等/-----------------------------------一样------------------------------------------------------------------------------/
//        NetRequestFactory.getInstance().createService(MyService.class).getData("e3da5859e3ecf2e8cdca731eb7952dfe_29").compose(Transform.<ResultBean<Data>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Data>() {
//            @Override
//            public void onSuccess(Data data) {
//                Log.i("Success",data.list.length+"");
//            }
//
//            @Override
//            public void _onError(Throwable e) {
//                Log.i("1111","Error");
//            }
//        });

//        if (Vitamio.isInitialized(this)) {
//            mVvv = (VideoView) findViewById(R.id.vv_main);//实例化
//            mVvv.setVideoURI(Uri.parse("http://qiubai-video.qiushibaike.com/91B2TEYP9D300XXH_3g.mp4"));//设置播放地址
//            mVvv.setMediaController(new MediaController(this));
//
//            //设置监听
//            mVvv.setOnPreparedListener(this);
//            mVvv.setOnErrorListener(this);
//            mVvv.setOnCompletionListener(this);
//            mVvv.start();
//        }
//    }
//    implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener  (加上)
//    @Override
//    public void onPrepared(MediaPlayer mp) {
//        Toast.makeText(this,"准备好了", Toast.LENGTH_LONG).show();
//        mVvv.start();//开始播放
//    }
//
//    @Override
//    public void onCompletion(MediaPlayer mp) {
//        Toast.makeText(this,"播放完成", Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public boolean onError(MediaPlayer mp, int what, int extra) {
//        Toast.makeText(this,"Error", Toast.LENGTH_LONG).show();
//        return false;
    }
}
