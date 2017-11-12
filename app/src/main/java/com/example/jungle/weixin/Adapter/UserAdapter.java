package com.example.jungle.weixin.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jungle.weixin.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jungle on 2017/11/12.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<String> userList ;
    private Context mContext;



    public UserAdapter(Context context,List<String> userList){
        mContext=context;
       this.userList = userList;
    }
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_recyclerview_item, parent, false);
        UserAdapter.ViewHolder holder = new UserAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {
        holder.iconImage.setImageResource(R.mipmap.ic_launcher);
        holder.textView.setText(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private de.hdodenhof.circleimageview.CircleImageView iconImage;

        public ViewHolder(View view){
            super(view);

            textView = (TextView) view.findViewById(R.id.user_name);
            iconImage = (CircleImageView) view.findViewById(R.id.icon_image);
        }
    }
}
