package com.example.jungle.weixin.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jungle.weixin.Adapter.ViewPagerAdapter;
import com.example.jungle.weixin.CustomControls.AppCompatSwipeBack;
import com.example.jungle.weixin.Fragment.ComprehensiveFragment;
import com.example.jungle.weixin.Fragment.ConcernFragment;
import com.example.jungle.weixin.Fragment.RealTimeFragment;
import com.example.jungle.weixin.Fragment.UserFragment;
import com.example.jungle.weixin.Fragment.VideoFragment;
import com.example.jungle.weixin.R;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        List<Fragment> viewList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        titleList.add("综合");
        titleList.add("实时");
        titleList.add("用户");
        titleList.add("关注人");
        titleList.add("视频");
        viewList.add(new ComprehensiveFragment());
        viewList.add(new RealTimeFragment());
        viewList.add(new UserFragment());
        viewList.add(new ConcernFragment());
        viewList.add(new VideoFragment());

        ViewPagerAdapter mViewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),viewList,titleList);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(mViewPagerAdapter);


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
