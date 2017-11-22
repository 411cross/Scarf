package com.example.jungle.weixin.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jungle.weixin.Bean.HotTopic;
import com.example.jungle.weixin.R;

import java.util.List;

/**
 * Created by chf on 2017/11/22.
 */

public class HotTopicAdapter extends RecyclerView.Adapter<HotTopicAdapter.ViewHolder>{
    private List<HotTopic> hotTopicList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView hotTopicImage;
        TextView hotTopicTitle;
        TextView hotTopicContent;
        public ViewHolder(View v){
            super(v);
            hotTopicImage = (ImageView) v.findViewById(R.id.hotTopicImage);
            hotTopicTitle = (TextView) v.findViewById(R.id.hotTopicTitle);
            hotTopicContent = (TextView) v.findViewById(R.id.hotTopicContent);
        }
    }
    public HotTopicAdapter(List<HotTopic> list){
        this.hotTopicList = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hottopic_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HotTopic hotTopic = hotTopicList.get(position);
        holder.hotTopicImage.setImageResource(hotTopic.getImageId());
        holder.hotTopicTitle.setText(hotTopic.getTitle());
        holder.hotTopicContent.setText(hotTopic.getContent());
    }

    @Override
    public int getItemCount(){
        return hotTopicList.size();
    }
}
