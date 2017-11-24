package com.example.jungle.weixin.Activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.jungle.weixin.Adapter.AMeAdapter;
import com.example.jungle.weixin.Bean.Login;
import com.example.jungle.weixin.Bean.ParticularBean.CreateDestoryCommentsData;
import com.example.jungle.weixin.Bean.ParticularBean.StatusList;
import com.example.jungle.weixin.CustomControls.AppCompatSwipeBack;
import com.example.jungle.weixin.PublicUtils.CodeUtils;
import com.example.jungle.weixin.PublicUtils.UrlUtils;
import com.example.jungle.weixin.R;
import com.example.jungle.weixin.RetrofitUtil.HttpResultSubscriber;
import com.example.jungle.weixin.RetrofitUtil.MyService;
import com.example.jungle.weixin.RetrofitUtil.NetRequestFactory;
import com.example.jungle.weixin.RetrofitUtil.Transform;

import io.vov.vitamio.utils.Log;
import retrofit2.Response;

public class ReviewWeiboActivity extends AppCompatSwipeBack {
    private ImageButton sendButton;
    private EditText content;
    private long StatusId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_weibo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("评论");
        }

        content = (EditText) findViewById(R.id.content);
        final String text = UrlUtils.getURLEncoderString(content.getText().toString());
        StatusId = getIntent().getLongExtra("StatusId",-1);


        sendButton= (ImageButton) findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetRequestFactory.getInstance().createService(MyService.class).commentsCreate(CodeUtils.getmToken(),text,StatusId).compose(Transform.<Response<CreateDestoryCommentsData>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<CreateDestoryCommentsData>>() {
                    @Override
                    public void onSuccess(Response<CreateDestoryCommentsData> CreateDestoryCommentsData) {
                        Log.i("11111",CreateDestoryCommentsData.body().getText());

                    }

                    @Override
                    public void _onError(Response<CreateDestoryCommentsData> CreateDestoryCommentsData) {

                    }

                });
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
