package com.example.jungle.weixin.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jungle.weixin.Bean.BaseBean.User;
import com.example.jungle.weixin.Bean.ParticularBean.FriendsFriendFollowersFriendships;
import com.example.jungle.weixin.CustomControls.FollowButton;
import com.example.jungle.weixin.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jungle on 2017/11/23.
 */

public class FollowFansAdapter extends RecyclerView.Adapter<FollowFansAdapter.ViewHolder> {
    private Context mContext;
    private List<User> mList;

    public FollowFansAdapter(Context context, List<User> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fans_follow_item, parent, false);
        FollowFansAdapter.ViewHolder holder = new FollowFansAdapter.ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mList.get(position);
        holder.nameTv.setText(user.getScreen_name());
        Glide.with(mContext).load(user.getProfile_image_url()).fitCenter().into(holder.icon);
        holder.followButton.setButtonStates(user.isFollow_me());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private TextView nameTv;
        private de.hdodenhof.circleimageview.CircleImageView icon;
        private com.example.jungle.weixin.CustomControls.FollowButton followButton;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            nameTv = (TextView) mView.findViewById(R.id.user_name);
            icon = (de.hdodenhof.circleimageview.CircleImageView) mView.findViewById(R.id.user_icon);
            followButton = (com.example.jungle.weixin.CustomControls.FollowButton) mView.findViewById(R.id.follow_button);
        }

    }


}
