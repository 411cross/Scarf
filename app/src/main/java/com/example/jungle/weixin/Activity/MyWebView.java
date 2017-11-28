package com.example.jungle.weixin.Activity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jungle.weixin.Bean.BaseBean.SharedPreUser;
import com.example.jungle.weixin.Bean.ParticularBean.SPData;
import com.example.jungle.weixin.PublicUtils.ManagerUtils;
import com.example.jungle.weixin.PublicUtils.ToastUtils;
import com.example.jungle.weixin.R;
import com.example.jungle.weixin.RetrofitUtil.HttpResultSubscriber;
import com.example.jungle.weixin.RetrofitUtil.MyService;
import com.example.jungle.weixin.RetrofitUtil.NetRequestFactory;
import com.example.jungle.weixin.RetrofitUtil.Transform;

import java.io.ByteArrayInputStream;
import java.util.Map;

import retrofit2.Response;

import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.addUser;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getCurrent;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getSp;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getUserCount;

public class MyWebView extends BaseActivity {
    private WebView mWebView;
    private TextView title_WebView;
    private ImageButton back;
    private SharedPreferences sp;
    private final String loginUrl =
            "https://api.weibo.com/oauth2/authorize?client_id=2604262634&redirect_uri=http://111.230.18.20:8080/weiboApp/Auth/getToken&display=moblie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_view);

        mWebView = (WebView) findViewById(R.id.webView);
        title_WebView = (TextView) findViewById(R.id.title);
        back = (ImageButton) findViewById(R.id.back);
        CookieSyncManager.createInstance(this);
        CookieManager.getInstance().removeAllCookie();//清除所有cookie

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
                Log.i("fuckurl", url);
                if (url.contains("getToken?code=")) {
                    mWebView.setVisibility(View.GONE);
                    super.onPageStarted(view, url, favicon);
                    NetRequestFactory.getInstance().createService(MyService.class).requestUrl(url).compose(Transform.<Response<SPData>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<SPData>>() {
                        @Override
                        public void onSuccess(Response<SPData> spuResponse) {
                            SharedPreUser spu = spuResponse.body().getData();
                            addUser(sp,new SharedPreUser(spu.getUid(),spu.getAcc_token(),null,null));
                            //请求完成后需要将此activity结束 避免用户看到关键信息
                            ManagerUtils.exit();
                        }

                        @Override
                        public void _onError(Response<SPData> loginResponse) {
                            //重新加载授权页面
                            mWebView.setVisibility(View.VISIBLE);
                            mWebView.loadUrl(loginUrl);
                        }
                    });
                }
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                super.onPageFinished(view, url);
            }

        });
        mWebView.loadUrl(loginUrl);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(getUserCount(sp)>0){
            mWebView.destroy();
            super.onBackPressed();
        }else{
            Toast.makeText(MyWebView.this, "请先登录", Toast.LENGTH_SHORT).show();
            mWebView.loadUrl(loginUrl);
        }
    }

}
