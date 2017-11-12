package com.example.jungle.weixin.Activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.jungle.weixin.Adapter.ViewPagerAdapter;
import com.example.jungle.weixin.CustomControls.CommomDialog;
import com.example.jungle.weixin.Fragment.FindFragment;
import com.example.jungle.weixin.Fragment.HomePageFragment;
import com.example.jungle.weixin.Fragment.InformationFragment;
import com.example.jungle.weixin.R;
import com.jauker.widget.BadgeView;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TotalActivity extends FragmentActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private de.hdodenhof.circleimageview.CircleImageView iconImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);
        final DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        List<Fragment> viewList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        titleList.add("首页");
        titleList.add("消息");
        titleList.add("发现");
        viewList.add(new HomePageFragment());
        viewList.add(new InformationFragment());
        viewList.add(new FindFragment());

        ViewPagerAdapter mViewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),viewList,titleList);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(mViewPagerAdapter);



//角标
        BadgeView badgeView1 = new BadgeView(TotalActivity.this);
        badgeView1.setTargetView(((ViewGroup)tabLayout.getChildAt(0)).getChildAt(0));
        badgeView1.setBadgeCount(3);

        BadgeView badgeView2 = new BadgeView(this);
        badgeView2.setTargetView(((ViewGroup)tabLayout.getChildAt(0)).getChildAt(1));
        badgeView2.setBadgeCount(3);

        BadgeView badgeView3 = new BadgeView(this);
        badgeView3.setTargetView(((ViewGroup)tabLayout.getChildAt(0)).getChildAt(2));
        badgeView3.setBadgeCount(3);


        ImageButton editButton = (ImageButton) findViewById(R.id.edit_button);
        editButton.setOnClickListener(this);



        iconImage = (CircleImageView) findViewById(R.id.icon_image);
        iconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);

//        navView.setCheckedItem(R.id.nav_call); //设置默认选择项
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.peripheralDynamics :
                        Intent intent = new Intent(TotalActivity.this,PeripheralDynamicsActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.left_in,R.anim.right_out);
                        break;
                    case R.id.myLikes :
                        Intent intent1 = new Intent(TotalActivity.this,HotSearchActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(R.anim.left_in,R.anim.right_out);
                        break;
                    case R.id.myCollection :
                        Intent intent2 = new Intent(TotalActivity.this,PrivateMessageActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(R.anim.left_in,R.anim.right_out);
                        break;
                    case R.id.accountManagement :
                        Intent intent3 = new Intent(TotalActivity.this,UserDetailActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(R.anim.left_in,R.anim.right_out);
                        break;
                    default:
                        drawerLayout.closeDrawers();
                        new CommomDialog(TotalActivity.this, R.style.dialog).show();
                        break;
                }

                return true;
            }
        }); // 选择的监听事件

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_button:
            Intent intent = new Intent(TotalActivity.this,Publish.class);
            startActivity(intent);
                overridePendingTransition(R.anim.left_in,R.anim.right_out);
                break;
            default:
                break;

        }
    }
}
