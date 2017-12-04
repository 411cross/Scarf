package com.example.jungle.weixin.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jungle.weixin.Bean.HotTopic;
import com.example.jungle.weixin.R;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by chf on 2017/11/22.
 */

public class HotTopicAdapter extends RecyclerView.Adapter<HotTopicAdapter.ViewHolder>{
    private List<HotTopic> hotTopicList;
    private Activity activity;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView hotTopicImage;
        TextView hotTopicTitle;
        TextView hotTopicContent;
        View view;
        public ViewHolder(View v){
            super(v);
            view = v;
            hotTopicImage = (ImageView) v.findViewById(R.id.hotTopicImage);
            hotTopicTitle = (TextView) v.findViewById(R.id.hotTopicTitle);
            hotTopicContent = (TextView) v.findViewById(R.id.hotTopicContent);
        }
    }
    public HotTopicAdapter(List<HotTopic> list, Activity a){
        this.hotTopicList = list;
        this.activity = a;
    }
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hottopic_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        HotTopic hotTopic = hotTopicList.get(position);
        holder.hotTopicImage.setImageResource(hotTopic.getImageId());
        holder.hotTopicTitle.setText(hotTopic.getTitle());
        holder.hotTopicContent.setText(hotTopic.getContent());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
                intent.putExtra("title",hotTopicList.get(position).getTitle());
                activity.setResult(RESULT_OK,intent);
                activity.finish();
                activity.overridePendingTransition(R.anim.left_in,R.anim.right_out);
            }
        });
    }

    @Override
    public int getItemCount(){
        return hotTopicList.size();
    }
}
