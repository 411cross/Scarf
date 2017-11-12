package com.example.jungle.weixin.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.jungle.weixin.Activity.TotalActivity;
import com.example.jungle.weixin.Activity.WeiboDetailActivity;
import com.example.jungle.weixin.Bean.Weibo;
import com.example.jungle.weixin.Bean.WeiboImage;
import com.example.jungle.weixin.PublicUtils.DateUtils;
import com.example.jungle.weixin.PublicUtils.StringUtils;
import com.example.jungle.weixin.R;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by derrickJ on 2017/11/8.
 */

public class WeiboAdapter extends RecyclerView.Adapter<WeiboAdapter.ViewHolder> {

    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的

    private View mHeaderView;
    private View mFooterView;


    private TotalActivity mContext;
    private List<Weibo> weiboList;

    class ViewHolder extends RecyclerView.ViewHolder {

        private View itemView;

        ImageView avatarImage;
        ImageView identityIcon;
        TextView nickname;
        TextView date;
        TextView time;
        TextView source;

        View weiboMain;

        View bodyView;
        TextView body;

        View singlePicView;
        ImageView singlePic;

        View multiPicsView;
        NineGridView multiPicsGrid;

        View videoView;
        View videoContainerView;
        ImageView video;

        View passageView;
        ImageView passageImage;
        TextView passageTitle;
        TextView passageSubTitle;

        View reweiboView;

        View reweiboBodyView;
        TextView reweiboBody;

        View reweiboSinglePicView;
        ImageView reweiboSinglePic;

        View reweiboMultiPicsView;
        NineGridView reweiboMultiPicsGrid;

        View reweiboVideoView;
        View reweiboVideoContainerView;
        ImageView reweiboVideo;

        View reweiboPassageView;
        ImageView reweiboPassageImage;
        TextView reweiboPassageTitle;
        TextView reweiboPassageSubTitle;

        View view1;


        public ViewHolder(View view) {
            super(view);
            if (view == mHeaderView){
                return;
            }
            if (view == mFooterView){
                return;
            }
            this.itemView = view;
            avatarImage = (ImageView) view.findViewById(R.id.avatar);
            identityIcon = (ImageView) view.findViewById(R.id.identity_icon);
            nickname = (TextView) view.findViewById(R.id.nickname);
            date = (TextView) view.findViewById(R.id.date);
            source = (TextView) view.findViewById(R.id.source);

            weiboMain = view.findViewById(R.id.weibo_main);
//
            bodyView = (View) view.findViewById(R.id.body_layout);
            body = (TextView) bodyView.findViewById(R.id.body);
//            bodyView.setVisibility(View.GONE);

            singlePicView = (View) view.findViewById(R.id.single_pic_layout);
            singlePic = (ImageView) singlePicView.findViewById(R.id.single_pic);
//            singlePicView.setVisibility(View.GONE);

            multiPicsView = (View) view.findViewById(R.id.multi_pics_layout);
            multiPicsGrid = (NineGridView) multiPicsView.findViewById(R.id.multi_pics_grid);
//            multiPicsView.setVisibility(View.GONE);

            videoView = (View) view.findViewById(R.id.video_layout);
            videoContainerView = (View) videoView.findViewById(R.id.video_container);
            video = (ImageView) videoView.findViewById(R.id.video);
//            videoView.setVisibility(View.GONE);

            passageView = (View) view.findViewById(R.id.passage_layout);
            passageImage = (ImageView) passageView.findViewById(R.id.passage_image);
            passageTitle = (TextView) passageView.findViewById(R.id.passage_title);
            passageSubTitle = (TextView) passageView.findViewById(R.id.passage_subtitle);
//            passageView.setVisibility(View.GONE);

            reweiboView = (View) view.findViewById(R.id.reweibo_layout);
//            reweiboView.setVisibility(View.GONE);

            reweiboBodyView = (View) view.findViewById(R.id.reweibo_body_layout);
            reweiboBody = (TextView) reweiboBodyView.findViewById(R.id.body);
//            reweiboBodyView.setVisibility(View.GONE);

            reweiboSinglePicView = (View) view.findViewById(R.id.reweibo_single_pic_layout);
            reweiboSinglePic = (ImageView) reweiboSinglePicView.findViewById(R.id.single_pic);
//            reweiboSinglePicView.setVisibility(View.GONE);

            reweiboMultiPicsView = (View) view.findViewById(R.id.reweibo_multi_pics_layout);
            reweiboMultiPicsGrid = (NineGridView) reweiboMultiPicsView.findViewById(R.id.multi_pics_grid);
//            reweiboMultiPicsView.setVisibility(View.GONE);

            reweiboVideoView = (View) view.findViewById(R.id.reweibo_video_layout);
            reweiboVideoContainerView = (View) reweiboVideoView.findViewById(R.id.video_container);
            reweiboVideo = (ImageView) reweiboVideoView.findViewById(R.id.video);
//            reweiboVideoView.setVisibility(View.GONE);

            reweiboPassageView = (View) view.findViewById(R.id.reweibo_passage_layout);
            reweiboPassageImage = (ImageView) reweiboPassageView.findViewById(R.id.passage_image);
            reweiboPassageTitle = (TextView) reweiboPassageView.findViewById(R.id.passage_title);
            reweiboPassageSubTitle = (TextView) reweiboPassageView.findViewById(R.id.passage_subtitle);
//            reweiboPassageView.setVisibility(View.GONE);

        }

        private void goneEverything() {
            bodyView.setVisibility(View.GONE);
            singlePicView.setVisibility(View.GONE);
            multiPicsView.setVisibility(View.GONE);
            videoView.setVisibility(View.GONE);
            passageView.setVisibility(View.GONE);
            reweiboView.setVisibility(View.GONE);
            reweiboBodyView.setVisibility(View.GONE);
            reweiboSinglePicView.setVisibility(View.GONE);
            reweiboMultiPicsView.setVisibility(View.GONE);
            reweiboVideoView.setVisibility(View.GONE);
            reweiboPassageView.setVisibility(View.GONE);
        }

    }

    public WeiboAdapter(TotalActivity context, List<Weibo> list) {
        mContext = context;
        weiboList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new ViewHolder(mHeaderView);
        }
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new ViewHolder(mFooterView);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weibo, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            if (holder instanceof ViewHolder) {
                final Weibo weibo = weiboList.get(position-1);
                holder.avatarImage.setImageResource(weibo.getAvatarURL());
                holder.nickname.setText(weibo.getNickname());
                holder.date.setText(DateUtils.formatDate(weibo.getDate()));
                holder.source.setText(weibo.getSource());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, WeiboDetailActivity.class);
                        intent.putExtra("weibo", weibo);
                        mContext.startActivity(intent);
                        mContext.overridePendingTransition(R.anim.left_in,R.anim.right_out);

                    }
                });
                switch (weibo.getType()) {
                    case 0:
                        holder.goneEverything();
                        holder.bodyView.setVisibility(View.VISIBLE);
                        holder.body.setText(StringUtils.transformWeiboBody(mContext, holder.body, weibo.getBody()));
                        break;
                    case 1:
                        holder.goneEverything();
                        holder.singlePicView.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        holder.goneEverything();
                        holder.bodyView.setVisibility(View.VISIBLE);
                        holder.singlePicView.setVisibility(View.VISIBLE);
                        holder.body.setText(StringUtils.transformWeiboBody(mContext, holder.body, weibo.getBody()));
                        holder.singlePic.setImageResource(weibo.getImage());
                        break;
                    case 3:
                        holder.goneEverything();
                        holder.bodyView.setVisibility(View.VISIBLE);
                        holder.multiPicsView.setVisibility(View.VISIBLE);
                        holder.body.setText(StringUtils.transformWeiboBody(mContext, holder.body, weibo.getBody()));
                        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
                        List<WeiboImage> images = weiboList.get(position-1).getImageurls();
                        if (images != null) {
                            for (WeiboImage image : images) {
                                ImageInfo info = new ImageInfo();
                                info.setThumbnailUrl(image.getUrl());
                                info.setBigImageUrl(image.getUrl());
                                imageInfo.add(info);
                            }
                        }
                        holder.multiPicsGrid.setAdapter(new NineGridViewClickAdapter(mContext, imageInfo));
                        if (images != null && images.size() == 1) {
//                    holder.multiPicsGrid.setSingleImageRatio(images.get(0).getWidth() * 1.0f / images.get(0).getHeight());
                            holder.multiPicsGrid.setSingleImageRatio(3.0f / 2);
                        } else {
                            holder.multiPicsGrid.setGridSpacing(16);
                        }
                        break;
                }
                return;
            }
            return;
        } else if (getItemViewType(position) == TYPE_HEADER) {
            return;
        } else {
            return;
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        if (position == getItemCount() -1 ) {
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }


    public View getmHeaderView() {
        return mHeaderView;
    }

    public View getmFooterView() {
        return mFooterView;
    }

    public void setmHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
        notifyItemInserted(0);

    }

    public void setmFooterView(View mFooterView) {
        this.mFooterView = mFooterView;
        notifyItemInserted(getItemCount()-1);

    }

    @Override
    public int getItemCount() {
        if(mHeaderView == null && mFooterView == null){
            return weiboList.size();
        }else if(mHeaderView == null && mFooterView != null){
            return weiboList.size() + 1;
        }else if (mHeaderView != null && mFooterView == null){
            return weiboList.size() + 1;
        }else {
            return weiboList.size() + 2;
        }
    }



}
