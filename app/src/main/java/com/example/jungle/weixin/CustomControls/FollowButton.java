package com.example.jungle.weixin.CustomControls;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.jungle.weixin.R;

/**
 * Created by jungle on 2017/11/23.
 */

public class FollowButton extends LinearLayout {
    private Context mContext;
    private Button mButton;
    private boolean buttonStates;

    public boolean isButtonStates() {
        return buttonStates;
    }

    public void setButtonStates(boolean buttonStates) {
        this.buttonStates = buttonStates;
    }

    public FollowButton(Context context) {
        super(context);
        mContext = context;
        initView();
    }


    public FollowButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initAttrs(context, attrs);
        initView();

    }


    public FollowButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttrs(context, attrs);
        initView();

    }

    public FollowButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        initAttrs(context, attrs);
        initView();

    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.follow_button, this);

        mButton = (Button) findViewById(R.id.followButton);
        if(buttonStates == true){
            mButton.setBackgroundResource(R.drawable.follow_button_bgb);
            mButton.setText("相互关注");
            mButton.setTextColor(this.getResources().getColor(R.color.colorWhite));
        }else {
            mButton.setBackgroundResource(R.drawable.follow_button_bgw);
            mButton.setText("关注");
            mButton.setTextColor(this.getResources().getColor(R.color.colorBlue));
        }

        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonStates == true){
                    buttonStates = false;
                    mButton.setText("关注");
                    mButton.setBackgroundResource(R.drawable.follow_button_bgw);
                    mButton.setTextColor(FollowButton.this.getResources().getColor(R.color.colorBlue));

                }else {
                    buttonStates = true;
                    mButton.setText("相互关注");
                    mButton.setBackgroundResource(R.drawable.follow_button_bgb);
                    mButton.setTextColor(FollowButton.this.getResources().getColor(R.color.colorWhite));

                }
            }
        });
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Follow_Button);
        buttonStates = typedArray.getBoolean(R.styleable.Follow_Button_isMutualConcern, false);
        typedArray.recycle();
    }

}
