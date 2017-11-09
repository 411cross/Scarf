package com.example.jungle.weixin.CustomControls;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jungle.weixin.R;

import static android.content.ContentValues.TAG;

/**
 * Created by jungle on 2017/11/8.
 */

public class CommomDialog extends Dialog implements View.OnClickListener{
    private Context mContext;
    private TextView contentTxt1;
    private TextView contentTxt2;
    private TextView contentTxt3;
    private TextView contentTxt4;
    public CommomDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public CommomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;

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
        contentTxt2 = (TextView) findViewById(R.id.contentTxt2);
        contentTxt3 = (TextView) findViewById(R.id.contentTxt3);
        contentTxt4 = (TextView) findViewById(R.id.contentTxt4);
        contentTxt1.setOnClickListener(this);
        contentTxt2.setOnClickListener(this);
        contentTxt3.setOnClickListener(this);
        contentTxt4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.contentTxt1 :
                Log.i(TAG, "onClick: 1");
                break;
            case R.id.contentTxt2 :
                Log.i(TAG, "onClick: 2");
                break;
            case R.id.contentTxt3 :
                Log.i(TAG, "onClick: 3");
                break;
            case R.id.contentTxt4 :
                Log.i(TAG, "onClick: 4");
                break;


        }
    }
}
