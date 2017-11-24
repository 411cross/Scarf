package com.example.jungle.weixin.Activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jungle.weixin.Adapter.FollowFansAdapter;
import com.example.jungle.weixin.Bean.BaseBean.User;
import com.example.jungle.weixin.Bean.ParticularBean.FriendsFriendFollowersFriendships;
import com.example.jungle.weixin.CustomControls.AppCompatSwipeBack;
import com.example.jungle.weixin.PublicUtils.CodeUtils;
import com.example.jungle.weixin.R;
import com.example.jungle.weixin.RetrofitUtil.HttpResultSubscriber;
import com.example.jungle.weixin.RetrofitUtil.MyService;
import com.example.jungle.weixin.RetrofitUtil.NetRequestFactory;
import com.example.jungle.weixin.RetrofitUtil.Transform;

import java.util.Arrays;
import java.util.List;

import retrofit2.Response;

public class FollowActivity extends AppCompatSwipeBack {

    private User[] users;
    private List<User> userList;
    private RecyclerView recyclerView;
    private FollowFansAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Title");
        }

        NetRequestFactory.getInstance().createService(MyService.class).friendshipsFriends(CodeUtils.getmToken(),"我是步轩同学的分身",0).compose(Transform.<Response<FriendsFriendFollowersFriendships>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<FriendsFriendFollowersFriendships>>() {
            @Override
            public void onSuccess(Response<FriendsFriendFollowersFriendships> FriendsFriendFollowersFriendships) {
                users= FriendsFriendFollowersFriendships.body().getUsers();
                userList = Arrays.asList(users);
                recyclerView = (RecyclerView) findViewById(R.id.weibo_list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(FollowActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new FollowFansAdapter(FollowActivity.this,userList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void _onError(Response<FriendsFriendFollowersFriendships> FriendsFriendFollowersFriendships) {

            }

        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public void onBackPressed() {
        scrollToFinishActivity();//左滑退出activity
    }


}
