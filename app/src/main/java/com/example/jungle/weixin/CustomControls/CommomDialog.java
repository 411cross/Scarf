package com.example.jungle.weixin.CustomControls;

import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jungle.weixin.Activity.RelayWeiboActivity;
import com.example.jungle.weixin.Activity.ReviewWeiboActivity;
import com.example.jungle.weixin.Bean.BaseBean.Status;
import com.example.jungle.weixin.R;

import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by jungle on 2017/11/8.
 */

public class CommomDialog extends Dialog implements View.OnClickListener{
    private Context mContext;
    private Map<String,Status> mMap;
    private TextView contentTxt1;
    private TextView contentTxt2;
    private TextView contentTxt3;
    public CommomDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public CommomDialog(@NonNull Context context, @StyleRes int themeResId, Map<String,Status> map) {
        super(context, themeResId);
        this.mContext = context;
        mMap = map;

    }

    protected CommomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_dialog);
        setCanceledOnTouchOutside(true);
        initView();
    }

    private void initView() {
        contentTxt1 = (TextView) findViewById(R.id.contentTxt1);
        contentTxt1.setText("复 制");
        contentTxt2 = (TextView) findViewById(R.id.contentTxt2);
        contentTxt2.setText("评 论");
        contentTxt3 = (TextView) findViewById(R.id.contentTxt3);
        contentTxt3.setText("转 发");
        contentTxt1.setOnClickListener(this);
        contentTxt2.setOnClickListener(this);
        contentTxt3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.contentTxt1 :
                ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(mMap.get("status").getText());
                Toast.makeText(mContext, mMap.get("status").getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.contentTxt2 :
                Intent intent = new Intent(mContext, ReviewWeiboActivity.class);
                intent.putExtra("StatusId",mMap.get("status").getId());
                mContext.startActivity(intent);
                break;
            case R.id.contentTxt3 :
                Intent intent1 = new Intent(mContext, RelayWeiboActivity.class);
//
                mContext.startActivity(intent1);
                break;



        }
    }
}
