package com.example.jungle.weixin.RetrofitUtil;

import android.util.Log;


import com.example.jungle.weixin.Bean.ResultBean;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;


/**
 * Created by jungle on 2017/11/7.
 */

public abstract class HttpResultSubscriber<T> extends Subscriber<ResultBean<T>> {

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
        _onError(e);
    }

    @Override
    public void onNext(ResultBean<T> t) {

        if (t.status.equals("200"))
            onSuccess(t.data);
        else
            _onError(new Throwable("error code=" + t.status));
    }

    public abstract void onSuccess(T t);

    public abstract void _onError(Throwable e);



}
