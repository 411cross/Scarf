package com.example.jungle.weixin.Activity;

import android.support.v7.app.ActionBar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.jungle.weixin.CustomControls.AppCompatSwipeBack;
import com.example.jungle.weixin.CustomControls.MSearchView;

import com.example.jungle.weixin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scut.carson_ho.searchview.ICallBack;


public class HotSearchActivity extends AppCompatSwipeBack {
    private MSearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_search);


        searchView = (MSearchView) findViewById(R.id.search_view);


        // 4. 设置点击搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                Toast.makeText(HotSearchActivity.this,"1",Toast.LENGTH_LONG).show();
            }
        });
        Toolbar toolbar = searchView.getToolbar();
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }



//-------------------------------GridView
        GridView gridView = (GridView) findViewById(R.id.gridView);
        List list = new ArrayList<Map<String,String>>();
        for (int i = 0; i < 8; i++) {
            Map<String, String> map = new HashMap<String,String>();
            map.put("text","Content11111111111111");
            list.add(map);
        }
        String [] from ={"text"};
        int [] to = {R.id.gridViewTxt};
        SimpleAdapter sim_adapter = new SimpleAdapter(this, list, R.layout.gridview_item, from, to);
        gridView.setAdapter(sim_adapter);
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
