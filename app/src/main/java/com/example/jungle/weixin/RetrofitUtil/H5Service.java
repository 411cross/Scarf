package com.example.jungle.weixin.RetrofitUtil;

import com.example.jungle.weixin.Bean.BaseBean.SharedBaseBean;
import com.example.jungle.weixin.Bean.ParticularBean.StatusList;
import com.example.jungle.weixin.Bean.XHRBase.XHRHotStatus;
import com.example.jungle.weixin.Bean.XHRBase.XHRLongStatus;
import com.example.jungle.weixin.Bean.XHRBase.XHRUserDetail;
import com.example.jungle.weixin.Bean.XHRBase.XHRBaseBean;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
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

    @GET("User/detail.json")
    Observable<Response<XHRBaseBean<XHRUserDetail>>> getUserDetail(@Query("access_token") String access_token, @Query("page") int page, @Query("uid") String uid);

    @FormUrlEncoded
    @POST("Compose/send")
    Observable<Response<XHRBaseBean<String>>> send(@Field("access_token") String access_token, @Field("content") String content);

    @GET("User/getLongPage")
    Observable<Response<XHRBaseBean<XHRLongStatus>>> getLongStatus(@Query("access_token") String access_token, @Query("id") String id);

    @GET("User/getHotPage")
    Observable<Response<XHRBaseBean<XHRHotStatus>>> getHotStatus(@Query("access_token") String access_token, @Query("page") int page);

    @GET("Auth/getTokenByCode")
    Observable<Response<XHRBaseBean<SharedBaseBean>>> getTokenByCode(@Query("code") String code);
}
