package com.example.jungle.weixin.RetrofitUtil;



import com.example.jungle.weixin.Bean.Data;
import com.example.jungle.weixin.Bean.ResultBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface MyService {

    String BASE_URL = "https://api.weibo.com/2/";

    @FormUrlEncoded
    @POST("buyer-cloth/cloth/get-seller-cloth")
    Observable<ResultBean<String>> getData(@Field("token") String token, @Field("clothId") int clothId);

    @FormUrlEncoded
    @POST("buyer-cloth/collect-record")
    Observable<ResultBean<Data>> getData(@Field("token") String token);

    @GET("buyer-cloth/collect-record")
    Observable<ResultBean<Data>> geData(@Query("token") String token);

    //首页 - 获取当前登录用户及其所关注（授权）用户的最新微博
    @GET("statuses/home_timeline.json")
    Observable<ResultBean<Data>> getHomeTimeline(@Query("token") String token);

}
