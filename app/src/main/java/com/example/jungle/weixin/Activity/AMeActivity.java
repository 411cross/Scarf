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

import com.example.jungle.weixin.Adapter.CommentListAdapter;
import com.example.jungle.weixin.Bean.BaseBean.Comment;
import com.example.jungle.weixin.Bean.ParticularBean.ReadCommentsData;
import com.example.jungle.weixin.CustomControls.AppCompatSwipeBack;
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
    private Comment[] comments;
    private List<Comment> commentList;
    private RecyclerView recyclerView;
    private CommentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ame);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Title");
        }


        token = "2.007qpDNCCgNPqC8ed90a54ffK4zQ1D";
        NetRequestFactory.getInstance().createService(MyService.class).commentsMentions(token).compose(Transform.<Response<ReadCommentsData>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<ReadCommentsData>>() {
            @Override
            public void onSuccess(Response<ReadCommentsData> ReadCommentsData) {
                comments = ReadCommentsData.body().getComments();
                recyclerView = (RecyclerView) findViewById(R.id.weibo_list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(AMeActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                commentList = Arrays.asList(comments);
                Log.i("TAG1111111111", "onSuccess: "+commentList.size());
                adapter = new CommentListAdapter(AMeActivity.this, commentList);
                recyclerView.setAdapter(adapter);
                setFooterView(recyclerView);
            }

            @Override
            public void _onError(Response<ReadCommentsData> ReadCommentsData) {

            }

        });


    }


    private void setFooterView(RecyclerView view) {
        View footer = LayoutInflater.from(AMeActivity.this).inflate(R.layout.recyclerview_footer, view, false);
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
