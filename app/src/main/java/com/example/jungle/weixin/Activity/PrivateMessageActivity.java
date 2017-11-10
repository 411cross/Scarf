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

import com.example.jungle.weixin.Adapter.ChatAdapter;
import com.example.jungle.weixin.Adapter.WeiboAdapter;
import com.example.jungle.weixin.CustomControls.AppCompatSwipeBack;
import com.example.jungle.weixin.R;

import java.util.ArrayList;
import java.util.List;

public class PrivateMessageActivity extends AppCompatSwipeBack {

    private List<String> chatList;
    private ArrayList<Integer> chatTypeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_message);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Title");
        }

        //___________________________recyclerview


        chatList = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.chat_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        for(int i=0;i<8;i++){
            chatList.add("你好！");
            chatList.add("我好！");
        }
        chatTypeList = new ArrayList<>();
        for(int i=0;i<8;i++){
            chatTypeList.add(1);
            chatTypeList.add(2);
        }

        ChatAdapter adapter = new ChatAdapter(this,chatList,chatTypeList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_in,R.anim.right_out);
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
