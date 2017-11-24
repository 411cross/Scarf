package com.example.jungle.weixin.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jungle.weixin.Adapter.HotTopicAdapter;
import com.example.jungle.weixin.Bean.HotTopic;
import com.example.jungle.weixin.R;

import java.util.ArrayList;
import java.util.List;

public class HotTopicActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton back;
    private RecyclerView hotTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_topic);

        hotTopic = (RecyclerView) findViewById(R.id.HotTopicList);
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);
        List<HotTopic> list = new ArrayList<>();
        list.add(new HotTopic("#测试标题1#","测试内容1",R.drawable.choose));
        list.add(new HotTopic("#测试标题2#","测试内容2",R.drawable.ic_default_image));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        HotTopicAdapter hotTopicAdapter = new HotTopicAdapter(list,this);
        hotTopic.setLayoutManager(layoutManager);
        hotTopic.setAdapter(hotTopicAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.back:
                onBackPressed();
                break;
            default:
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in,R.anim.right_out);
    }


}
