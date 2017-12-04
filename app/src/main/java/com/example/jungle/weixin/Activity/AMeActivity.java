package com.example.jungle.weixin.Activity;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.jungle.weixin.Adapter.AMeAdapter;
import com.example.jungle.weixin.Adapter.CommentListAdapter;
import com.example.jungle.weixin.Bean.BaseBean.Comment;
import com.example.jungle.weixin.Bean.BaseBean.Status;
import com.example.jungle.weixin.Bean.ParticularBean.ReadCommentsData;
import com.example.jungle.weixin.Bean.ParticularBean.StatusList;
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

public class AMeActivity extends AppCompatSwipeBack {
    private String token;

    private List<Status> status;
    private RecyclerView recyclerView;
    private  AMeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ame);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("@我");
        }



        NetRequestFactory.getInstance().createService(MyService.class).getMentions(CodeUtils.getmToken()).compose(Transform.<Response<StatusList>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<StatusList>>() {
            @Override
            public void onSuccess(Response<StatusList> StatusList) {
                status = StatusList.body().getStatuses();
                recyclerView = (RecyclerView) findViewById(R.id.weibo_list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(AMeActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AMeAdapter(AMeActivity.this, status );
                recyclerView.setAdapter(adapter);
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        switch(newState){
                            case 0:
                                try {
                                    if(AMeActivity.this!= null) Glide.with(AMeActivity.this).resumeRequests();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 1:
                                try {
                                    if(AMeActivity.this!= null) Glide.with(AMeActivity.this).resumeRequests();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 2:
                                try {
                                    if(AMeActivity.this!= null) Glide.with(AMeActivity.this).pauseRequests();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;

                        }
                    }
                });
            }

            @Override
            public void _onError(Response<StatusList> StatusList) {

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
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(AMeActivity.this).clearMemory();
    }

    @Override
    public void onBackPressed() {
        scrollToFinishActivity();//左滑退出activity
    }

}
