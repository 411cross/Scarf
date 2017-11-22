package com.example.jungle.weixin.RetrofitUtil;

import android.util.Log;




import java.io.IOException;



import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;


/**
 * Created by jungle on 2017/11/7.
 */

public abstract class HttpResultSubscriber<T extends Response> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Log.e("OnError",e.getMessage());

        e.printStackTrace();
        //在这里做全局的错误处理
        if (e instanceof HttpException) {
            // ToastUtils.getInstance().showToast(e.getMessage());
        }
//        _onError(e);
        if(e instanceof HttpException){
            ResponseBody body = ((HttpException) e).response().errorBody();
            try {
                Log.i("ERROR❌", body.string());
            } catch (IOException IOe) {
                IOe.printStackTrace();
            }
        }
    }

    @Override
    public void onNext(T t) {
        if(t.code() == 200){
            onSuccess(t);
        } else {
            _onError(t);
        }
    }

    public abstract void onSuccess(T t);

    public abstract void _onError(T e);



}
