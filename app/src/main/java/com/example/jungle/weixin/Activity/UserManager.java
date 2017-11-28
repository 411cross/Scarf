package com.example.jungle.weixin.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jungle.weixin.Adapter.SharedPreUserAdapter;
import com.example.jungle.weixin.Bean.BaseBean.SharedPreUser;
import com.example.jungle.weixin.R;

import java.util.ArrayList;

import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.addUser;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getAllUser;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getCurrent;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getSp;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getUserCount;

public class UserManager extends BaseActivity implements View.OnClickListener{
    private LinearLayout addUser;
    private SharedPreferences sp;
    private ListView userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);

        sp = getSp(UserManager.this);

        addUser = (LinearLayout) findViewById(R.id.addUser);
        addUser.setOnClickListener(this);
        userList = (ListView) findViewById(R.id.userList);
        ArrayList<SharedPreUser> temp = getAllUser(sp);
        SharedPreUserAdapter adapter = new SharedPreUserAdapter(this,R.layout.sharedpreuseritem,temp,sp);
        userList.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("账号管理");
        }
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
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.addUser:
                if(getUserCount(sp)>=3){
                    Toast.makeText(this, "不允许存储超过三个用户", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(UserManager.this,MyWebView.class);
                    startActivity(intent);
                }
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
