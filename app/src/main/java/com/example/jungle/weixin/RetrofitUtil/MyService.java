package com.example.jungle.weixin.RetrofitUtil;



import com.example.jungle.weixin.Bean.BaseBean.Status;
import com.example.jungle.weixin.Bean.BaseBean.User;
import com.example.jungle.weixin.Bean.Data;
import com.example.jungle.weixin.Bean.ParticularBean.CreateDestoryCommentsData;
import com.example.jungle.weixin.Bean.ParticularBean.FriendsFriendFollowersFriendships;
import com.example.jungle.weixin.Bean.ParticularBean.ReadCommentsData;
import com.example.jungle.weixin.Bean.ParticularBean.StatusList;
import com.example.jungle.weixin.Bean.ResultBean;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface MyService {

    String BASE_URL = "https://api.weibo.com/2/";  // 地址

    @FormUrlEncoded
    @POST("buyer-cloth/cloth/get-seller-cloth")
    Observable<ResultBean<String>> getData(@Field("token") String token, @Field("clothId") int clothId);

    @FormUrlEncoded
    @POST("buyer-cloth/collect-record")
    Observable<ResultBean<Data>> getData(@Field("token") String token);

    // 首页 - 获取当前登录用户及其所关注（授权）用户的最新微博
    @GET("statuses/home_timeline.json")
//    Observable<Response<StatusList>> getHomeTimeline(@Query("access_token") String access_token, @Query("count") int count, @Query("page") int page);
    Observable<Response<StatusList>> getHomeTimeline(@Query("access_token") String access_token,@Query("count") int count ,@Query("page") int page);

    // 个人页 - 获取自己最新发表的微博列表
    @GET("statuses/user_timeline.json")
    Observable<Response<StatusList>> getUserTimeLine(@Query("access_token") String access_token);

    // 个人页 - 使用ID获取其他用户最新发表的微博列表
    @GET("statuses/home_timeline.json")
    Observable<Response<StatusList>> getOtherTimeLineWithID(@Query("access_token") String access_token, @Query("uid") long uid);

    // 个人页 - 使用昵称获取其他用户最新发表的微博列表
    @GET("statuses/user_timeline.json")
    Observable<Response<StatusList>> getOtherTimeLineWithName(@Query("access_token") String access_token, @Query("screen_name") String screen_name);

    // 获取最新的提到登录用户的微博列表，即@我的微博
    @GET("statuses/mentions.json")
    Observable<Response<StatusList>> getMentions(@Query("access_token") String access_token);

    // 根据微博ID获取单条微博内容
    @GET("statuses/show.json")
    Observable<Response<Status>> getSingleStatus(@Query("access_token") String access_token, @Query("id") int id);

    // 根据微博ID返回某条微博的评论列表
    @GET("comments/show.json")
    Observable<Response<ReadCommentsData>> commentsShow(@Query("access_token") String access_token, @Query("id") long id,@Query("count") int count,@Query("page") int page);

    // 我发出的评论列表
    @GET("comments/by_me.json")
    Observable<Response<ReadCommentsData>> commentsByMe(@Query("access_token") String access_token);

    // 获取当前登录用户所接收到的评论列表
    @GET("comments/to_me.json")
    Observable<Response<ReadCommentsData>> commentsToMe(@Query("access_token") String access_token);

    // 获取@到我的评论
    @GET("comments/mentions.json")
    Observable<Response<ReadCommentsData>> commentsMentions(@Query("access_token") String access_token);

    // 对一条微博进行评论
    @FormUrlEncoded
    @POST("comments/create.json")
    Observable<Response<CreateDestoryCommentsData>> commentsCreate(@Field("access_token") String access_token, @Field("comment") String comment, @Field("id") Long id);

    // 删除一条我的评论
    @FormUrlEncoded
    @POST("comments/destroy.json")
    Observable<Response<ResultBean<StatusList>>> commentsDestroy(@Field("access_token") String access_token, @Field("cid") int cid);

    // 回复一条我收到的评论
    @FormUrlEncoded
    @POST("comments/reply.json")
    Observable<Response<ResultBean<StatusList>>> commentsReply(@Field("access_token") String access_token, @Field("cid") int cid, @Field("id") int id, @Field("comment") String commment);

    // 获取当前用户信息
    @GET("users/show.json")
    Observable<Response<User>> usersShow(@Query("access_token") String access_token);

    // 根据用户ID获取用户信息
    @GET("users/show.json")
    Observable<Response<User>> usersShowWithID(@Query("access_token") String access_token, @Query("uid") long uid);

    // 根据用户昵称获取用户信息
    @GET("users/show.json")
    Observable<Response<User>> usersShowWithName(@Query("access_token") String access_token, @Query("screen_name") String screen_name);

    // 通过个性域名获取用户信息
    @GET("users/domain_show.json")
    Observable<Response<User>> usersDomainShow(@Query("access_token") String access_token, @Query("domain") String domain);

    // 获取用户的关注列表
    @GET("friendships/friends.json")
    Observable<Response<FriendsFriendFollowersFriendships>> friendshipsFriends(@Query("access_token") String access_token,@Query("screen_name") String screen_name,@Query("cursor") int cursor);

    // 获取用户粉丝列表
    @GET("friendships/followers.json")
    Observable<Response<StatusList>> friendshipsFollowers(@Query("access_token") String access_token);

    // 获取两个用户之间是否存在关注关系
    @GET("friendships/show.json")
    Observable<Response<StatusList>> friendshipsShow(@Query("access_token") String access_token);

    // 搜索某一话题下的微博
    @GET("search/topics.json")
    Observable<Response<StatusList>> searchTopics(@Query("access_token") String access_token,@Query("q") String q);


}
