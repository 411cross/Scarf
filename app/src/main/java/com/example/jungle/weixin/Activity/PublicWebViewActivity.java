package com.example.jungle.weixin.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jungle.weixin.CustomControls.AppCompatSwipeBack;
import com.example.jungle.weixin.R;

public class PublicWebViewActivity extends BaseActivity {
    private WebView mWebView;
    private TextView title_WebView;
    private ImageButton back;
    private SharedPreferences sp;
    private SharedPreferences.Editor ed;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_web_view);

        mWebView = (WebView) findViewById(R.id.public_web_view);
        title_WebView = (TextView) findViewById(R.id.title);
        back = (ImageButton) findViewById(R.id.back);

        url = getIntent().getStringExtra("url");

//        //尝试操作MainActivity中的sharedpreferences
//        try {
//            Context otherAppContext = createPackageContext("com.example.jungle.weixin", Context.CONTEXT_IGNORE_SECURITY);
//            sp = otherAppContext.getSharedPreferences("Scarf", Activity.MODE_PRIVATE);
//            ed = sp.edit();
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }

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
                Log.i("fuck", "onPageStarted: "+ url+"     "+url.indexOf("getToken?code="));
            }
//            @Override
//            public void onPageFinished(WebView view, final String url) {
//                if (url.indexOf("getToken?code=") > 0) {
//                    NetRequestFactory.getInstance().createService(MyService.class).requestUrl(url).compose(Transform.<ResultBean<Login>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Login>() {
//                        @Override
//                        public void onSuccess(Login login) {
//                            ed.putString("Access_token",login.getAccess_token());
//                            ed.commit();
//                            Log.i("Success", login.getAccess_token());
//                            Log.i("sp", sp.getString("Access_token",null));
//                            //请求完成后需要将此activity结束 避免用户看到关键信息
//                            finish();
//                        }
//
//                        @Override
//                        public void _onError(Throwable e) {
//                            //重新加载授权页面
//                            mWebView.loadUrl(loginUrl);
//                            Log.i("error", e.toString());
//                        }
//                    });
//                }
//                super.onPageFinished(view, url);
//            }
        });
        mWebView.loadUrl(url);

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
