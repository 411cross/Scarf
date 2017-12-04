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
import com.example.jungle.weixin.Adapter.CommentListAdapter;
import com.example.jungle.weixin.Bean.BaseBean.Comment;
import com.example.jungle.weixin.Bean.ParticularBean.ReadCommentsData;
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

public class CommentActivity extends BaseActivity {

    private Comment[] comments;
    private List<Comment> commentList;
    private RecyclerView recyclerView;
    private CommentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("评论");
        }

        NetRequestFactory.getInstance().createService(MyService.class).commentsByMe(CodeUtils.getmToken()).compose(Transform.<Response<ReadCommentsData>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<ReadCommentsData>>() {
            @Override
            public void onSuccess(Response<ReadCommentsData> ReadCommentsData) {
                comments = ReadCommentsData.body().getComments();
                recyclerView = (RecyclerView) findViewById(R.id.weibo_list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(CommentActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                commentList = Arrays.asList(comments);
                adapter = new CommentListAdapter(CommentActivity.this, commentList);
                recyclerView.setAdapter(adapter);
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        switch(newState){
                            case 0:
                                try {
                                    if(CommentActivity.this!= null) Glide.with(CommentActivity.this).resumeRequests();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 1:
                                try {
                                    if(CommentActivity.this!= null) Glide.with(CommentActivity.this).resumeRequests();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 2:
                                try {
                                    if(CommentActivity.this!= null) Glide.with(CommentActivity.this).pauseRequests();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;

                        }
                    }
                });
                setFooterView(recyclerView);

            }

            @Override
            public void _onError(Response<ReadCommentsData> ReadCommentsData) {

            }

        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Glide.get(CommentActivity.this).clearMemory();
    }

    private void setFooterView(RecyclerView view) {
        View footer = LayoutInflater.from(CommentActivity.this).inflate(R.layout.recyclerview_footer, view, false);
        adapter.setmFooterView(footer);
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