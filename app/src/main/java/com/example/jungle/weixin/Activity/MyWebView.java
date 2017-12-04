package com.example.jungle.weixin.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import com.example.jungle.weixin.Bean.BaseBean.SharedBaseBean;
import com.example.jungle.weixin.Bean.BaseBean.SharedPreUser;
import com.example.jungle.weixin.Bean.XHRBase.XHRBaseBean;
import com.example.jungle.weixin.PublicUtils.CodeUtils;
import com.example.jungle.weixin.PublicUtils.ManagerUtils;
import com.example.jungle.weixin.PublicUtils.ToastUtils;
import com.example.jungle.weixin.R;
import com.example.jungle.weixin.RetrofitUtil.H5Service;
import com.example.jungle.weixin.RetrofitUtil.HttpResultSubscriber;
import com.example.jungle.weixin.RetrofitUtil.MyService;
import com.example.jungle.weixin.RetrofitUtil.NetRequestFactory;
import com.example.jungle.weixin.RetrofitUtil.Transform;

import java.io.ByteArrayInputStream;
import java.util.Map;

import retrofit2.Response;

import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.addUser;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.checkUser;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.checkUser;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getCurrent;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getSp;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getUserCount;
import static java.lang.Thread.sleep;

public class MyWebView extends AppCompatActivity {
    private WebView mWebView;
    private TextView title_WebView;
    private ImageButton back;
    private SharedPreferences sp;
    private final String loginUrl =
            "https://api.weibo.com/oauth2/authorize?client_id=2604262634&redirect_uri=http://111.230.18.20:8080/weiboApp/Auth/getToken&display=moblie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSp(this);
        if (getCurrent(sp) == null) {
            setContentView(R.layout.activity_my_web_view);
            mWebView = (WebView) findViewById(R.id.webView);
            title_WebView = (TextView) findViewById(R.id.title);
            back = (ImageButton) findViewById(R.id.back);
            CookieSyncManager.createInstance(this);
            CookieManager.getInstance().removeAllCookie();//清除所有cookie

            mWebView.loadUrl(loginUrl);
            //尝试操作MainActivity中的sharedpreferences
            sp = getSp(this);

            mWebView.getSettings().setJavaScriptEnabled(true);
            WebChromeClient wcc = new WebChromeClient() {
                @Override
                public void onReceivedTitle(WebView view, String title) {
                    super.onReceivedTitle(view, title);
                    if (title.contains("getToken?code=")) {
                        title_WebView.setText("登录成功");
                    } else {
                        title_WebView.setText(title);
                    }
                }
            };
            mWebView.setWebChromeClient(wcc);
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, final String url, Bitmap favicon) {
                    Log.i("fuckurl", url);
                    if (url.contains("getToken?code=")) {
                        String code = url.substring(url.indexOf("getToken?code=") + 14);
                        Log.i("code", code);
                        NetRequestFactory.getInstance().createService(H5Service.class).getTokenByCode(code)
                                .compose(Transform.<Response<XHRBaseBean<SharedBaseBean>>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<XHRBaseBean<SharedBaseBean>>>() {
                            @Override
                            public void onSuccess(Response<XHRBaseBean<SharedBaseBean>> xhrBaseBeanResponse) {
                                SharedBaseBean bean = xhrBaseBeanResponse.body().getData();
                                addUser(sp, new SharedPreUser(bean.getUid(), bean.getAcc_token(), null, null));
                                CodeUtils.setmToken(bean.getAcc_token());
                                CodeUtils.setmID(Long.parseLong(bean.getUid()));
                                Intent intent = new Intent(MyWebView.this, TotalActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void _onError(Response<XHRBaseBean<SharedBaseBean>> xhrBaseBeanResponse) {
                                Toast.makeText(MyWebView.this, "登录失败,请重试", Toast.LENGTH_SHORT).show();
                                mWebView.loadUrl(loginUrl);
                            }

                        });
                        super.onPageStarted(view, url, favicon);
                    } else {
                        super.onPageStarted(view, url, favicon);
                    }

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
        } else {
            CodeUtils.setmToken(getCurrent(sp).getAcc_token());
            CodeUtils.setmID(Long.parseLong(getCurrent(sp).getUid()));
            Intent intent = new Intent(MyWebView.this, TotalActivity.class);
            startActivity(intent);
        }



    }



    @Override
    public void onBackPressed() {
        finish();
    }

}
