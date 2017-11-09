package com.example.jungle.weixin.RetrofitUtil;



import com.example.jungle.weixin.Bean.Data;
import com.example.jungle.weixin.Bean.ResultBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface MyService {

    String BASE_URL = "http://120.25.176.231:8000/";  // 地址

    @FormUrlEncoded
    @POST("buyer-cloth/cloth/get-seller-cloth")
    Observable<ResultBean<String>> getData(@Field("token") String token, @Field("clothId") int clothId);

    @FormUrlEncoded
    @POST("buyer-cloth/collect-record")
    Observable<ResultBean<Data>> getData(@Field("token") String token);
}
