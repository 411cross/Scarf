package com.example.jungle.weixin.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.design.widget.NavigationView;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jungle.weixin.Adapter.ViewPagerAdapter;
import com.example.jungle.weixin.Bean.BaseBean.User;
import com.example.jungle.weixin.Bean.XHRBase.XHRBaseBean;
import com.example.jungle.weixin.Fragment.FindFragment;
import com.example.jungle.weixin.Fragment.HomePageFragment;
import com.example.jungle.weixin.Fragment.InformationFragment;
import com.example.jungle.weixin.PublicUtils.CodeUtils;
import com.example.jungle.weixin.PublicUtils.ManagerUtils;
import com.example.jungle.weixin.PublicUtils.ToastUtils;
import com.example.jungle.weixin.R;
import com.example.jungle.weixin.RetrofitUtil.H5Service;
import com.example.jungle.weixin.RetrofitUtil.HttpResultSubscriber;
import com.example.jungle.weixin.RetrofitUtil.MyService;
import com.example.jungle.weixin.RetrofitUtil.NetRequestFactory;
import com.example.jungle.weixin.RetrofitUtil.Transform;
import com.jauker.widget.BadgeView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Response;

import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getCurrent;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getSp;


public class TotalActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private de.hdodenhof.circleimageview.CircleImageView iconImage;
    private de.hdodenhof.circleimageview.CircleImageView iconImageInDrawer;
    private ImageView backgroundImgV;
    private TextView usernameTv;
    private TextView descTv;
    private User user;
    private SharedPreferences sp;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp = getSp(this);
        if (getCurrent(sp) == null) {
            Intent intent = new Intent(TotalActivity.this,MyWebView.class);
            startActivity(intent);
            overridePendingTransition(R.anim.left_in,R.anim.right_out);
        }

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
        View header = navView.getHeaderView(0);
        iconImageInDrawer = (CircleImageView) header.findViewById(R.id.icon_image);
        usernameTv = (TextView) header.findViewById(R.id.username);
        descTv = (TextView) header.findViewById(R.id.description);
        backgroundImgV = (ImageView) header.findViewById(R.id.background);

        getUserInfo();



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
                        Intent intent1 = new Intent(TotalActivity.this,PublicWebViewActivity.class);
                        intent1.putExtra("url", "https://m.weibo.cn/p/index?containerid=2302572216277571");
                        startActivity(intent1);
                        overridePendingTransition(R.anim.left_in,R.anim.right_out);
                        break;
                    case R.id.myCollection :
                        Intent intent2 = new Intent(TotalActivity.this,PublicWebViewActivity.class);
                        intent2.putExtra("url", "https://m.weibo.cn/p/index?containerid=2302592216277571");
                        startActivity(intent2);
                        overridePendingTransition(R.anim.left_in,R.anim.right_out);
                        break;
                    case R.id.accountManagement :
                        Intent intent3 = new Intent(TotalActivity.this,UserManager.class);
                        startActivity(intent3);
                        overridePendingTransition(R.anim.left_in,R.anim.right_out);
                        break;
                    case R.id.clearCache :
                        ToastUtils.showShortToast(TotalActivity.this, "已清理 35 MB");
//                        Intent intent4 = new Intent(TotalActivity.this,SearchResultActivity.class);
//                        startActivity(intent4);
//                        overridePendingTransition(R.anim.left_in,R.anim.right_out);
                        break;
                    default:
                        drawerLayout.closeDrawers();
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

    public void getUserInfo() {
        NetRequestFactory.getInstance().createService(MyService.class).usersShowWithID(CodeUtils.getmToken(), id).compose(Transform.<Response<User>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<User>>() {
            @Override
            public void onSuccess(Response<User> userResponse) {
                user = userResponse.body();
                Glide.with(TotalActivity.this).load(user.getProfile_image_url()).into(iconImage);
                Glide.with(TotalActivity.this).load(user.getAvatar_hd()).into(iconImageInDrawer);
                Glide.with(TotalActivity.this).load(user.getCover_image_phone()).into(backgroundImgV);
                usernameTv.setText(user.getScreen_name());
                descTv.setText(user.getDescription());

                iconImageInDrawer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TotalActivity.this, UserDetailActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                        overridePendingTransition(R.anim.left_in,R.anim.right_out);
                    }
                });
//                SharedPreferences sp  = getSp(TotalActivity.this);
//                addUser(sp, new SharedPreUser("68768","",null,null));
//                addUserName(sp,"68768", "tyy");
//                System.out.println("==========================");
//                System.out.println(getAllUser(sp).get(0).getUserName());
//                System.out.println(getAllUser(sp).get(0).getAcc_token());
//                System.out.println(getAllUser(sp).get(0).getUid());
            }

            @Override
            public void _onError(Response<User> userResponse) {

            }

        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // 授权返回
        if (ManagerUtils.isFlag()) {
            ManagerUtils.setFlag(false);
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            LayoutInflater layoutInflater = LayoutInflater.from(TotalActivity.this); // 创建视图容器并设置上下文
            final View view = layoutInflater.inflate(R.layout.loginalterdialog,null); // 获取布局文件的视图
            final AlertDialog.Builder temp = new AlertDialog.Builder(TotalActivity.this);
            final AlertDialog a = temp.setTitle("登录授权").setView(view).show();
            Button ensure = (Button) view.findViewById(R.id.ensure);
            Button cancel = (Button) view.findViewById(R.id.cancel);
            final EditText usernameEt = (EditText) view.findViewById(R.id.userName);
            final EditText passwordEt = (EditText) view.findViewById(R.id.password);
            ensure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(TotalActivity.this, "没见过toast吗", Toast.LENGTH_SHORT).show();

                    NetRequestFactory.getInstance().createService(H5Service.class).login(usernameEt.getText().toString(), passwordEt.getText().toString())
                            .compose(Transform.<Response<XHRBaseBean<String>>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<XHRBaseBean<String>>>() {
                        @Override
                        public void onSuccess(Response<XHRBaseBean<String>> stringResponse) {
                            if (stringResponse.body().getStatus() == 1) {

                            } else {

                            }
                        }

                        @Override
                        public void _onError(Response<XHRBaseBean<String>> e) {
                            super._onError(e);
                        }
                    });
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a.dismiss();
                }
            });
        }

    }

}
