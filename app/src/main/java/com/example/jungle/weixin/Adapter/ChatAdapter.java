package com.example.jungle.weixin.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.jungle.weixin.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jungle on 2017/11/10.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private Context mContext;
    private List<String> chatList;
    private List<Integer> chatTypeList;

    final int iChat = 1;
    final int heChat = 2;

    public ChatAdapter(Context context, List<String> list,List<Integer> chatTypeList) {
        mContext = context;
        chatList = list;
        this.chatTypeList =chatTypeList;
    }


    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (iChat == viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_chat, parent, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.he_chat, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder holder, int position) {
        holder.textView.setText(chatList.get(position));
        holder.iconImage.setImageResource(R.mipmap.ic_launcher);
}

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private de.hdodenhof.circleimageview.CircleImageView iconImage;

        public ViewHolder(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.chatContent);
            iconImage = (CircleImageView) view.findViewById(R.id.icon_image);
        }

    }

    @Override

    public int getItemViewType(int position) {

        if (chatTypeList.get(position) == iChat) {
            return iChat;
        } else {
            return heChat;
        }
    }


}
