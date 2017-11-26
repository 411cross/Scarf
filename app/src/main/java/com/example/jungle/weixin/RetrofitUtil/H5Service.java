package com.example.jungle.weixin.RetrofitUtil;

import com.example.jungle.weixin.Bean.ParticularBean.StatusList;
import com.example.jungle.weixin.Bean.ResultBean;
import com.example.jungle.weixin.Bean.XHRBase.XHRBaseBean;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by derrickJ on 2017/11/26.
 */

public interface H5Service {

    String BASE_URL = "http://111.230.18.20:8080/weiboApp/";  // 地址

    @FormUrlEncoded
    @POST("Compose/login")
    Observable<Response<XHRBaseBean<String>>> login(@Field("username") String username, @Field("password") String password);

    @GET("statuses/user_timeline.json")
    Observable<Response<StatusList>> getUserTimeLine(@Query("access_token") String access_token);

}
