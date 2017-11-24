package com.example.jungle.weixin.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jungle.weixin.Bean.BaseBean.SharedPreUser;
import com.example.jungle.weixin.Bean.Data;
import com.example.jungle.weixin.Bean.Login;
import com.example.jungle.weixin.Bean.ResultBean;
import com.example.jungle.weixin.R;
import com.example.jungle.weixin.RetrofitUtil.HttpResultSubscriber;
import com.example.jungle.weixin.RetrofitUtil.MyService;
import com.example.jungle.weixin.RetrofitUtil.NetRequestFactory;
import com.example.jungle.weixin.RetrofitUtil.Transform;

import retrofit2.Response;

import static com.example.jungle.weixin.PublicUtils.shaedPreUtils.addUser;
import static com.example.jungle.weixin.PublicUtils.shaedPreUtils.getSp;
import static com.example.jungle.weixin.PublicUtils.shaedPreUtils.getUserCount;

public class MyWebView extends AppCompatActivity {
    private WebView mWebView;
    private TextView title_WebView;
    private ImageButton back;
    private SharedPreferences sp;
    private SharedPreferences.Editor ed;
    private final String loginUrl =
            "https://api.weibo.com/oauth2/authorize?client_id=2604262634&redirect_uri=http://139.199.226.190:8888/weiboApp/Auth/getToken&display=moblie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_view);

        mWebView = (WebView) findViewById(R.id.webView);
        title_WebView = (TextView) findViewById(R.id.title);
        back = (ImageButton) findViewById(R.id.back);

        //尝试操作MainActivity中的sharedpreferences
        sp = getSp(this);

        mWebView.getSettings().setJavaScriptEnabled(true);
        WebChromeClient wcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                title_WebView.setText(title);
            }
        };
        mWebView.setWebChromeClient(wcc);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public void onPageFinished(WebView view, final String url) {
                if (url.indexOf("getToken?code=") > 0) {
                    NetRequestFactory.getInstance().createService(MyService.class).requestUrl(url).compose(Transform.<Response<Login>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<Login>>() {
                        @Override
                        public void onSuccess(Response<Login> loginResponse) {
                            Login login = loginResponse.body();
                            addUser(sp,new SharedPreUser(login.getUid(),login.getAccess_token(),null,null));
                            //请求完成后需要将此activity结束 避免用户看到关键信息
                            finish();
                        }

                        @Override
                        public void _onError(Response<Login> loginResponse) {
                            //重新加载授权页面
                            mWebView.loadUrl(loginUrl);
                        }
                    });
                }
                super.onPageFinished(view, url);
            }
        });
        mWebView.loadUrl(loginUrl);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.destroy();
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        mWebView.destroy();
        super.onBackPressed();
    }
}
