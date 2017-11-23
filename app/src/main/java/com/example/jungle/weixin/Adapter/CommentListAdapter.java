package com.example.jungle.weixin.Adapter;

/**
 * Created by jungle on 2017/11/21.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jungle.weixin.Activity.AMeActivity;
import com.example.jungle.weixin.Activity.CommentActivity;
import com.example.jungle.weixin.Bean.BaseBean.Comment;
import com.example.jungle.weixin.PublicUtils.DateUtils;
import com.example.jungle.weixin.PublicUtils.StringUtils;
import com.example.jungle.weixin.PublicUtils.TypeUtils;
import com.example.jungle.weixin.R;
import com.lzy.ninegrid.NineGridView;



import java.util.List;


/**
 * Created by derrickJ on 2017/11/8.
 */

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {

    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的

    private View mHeaderView;
    private View mFooterView;


    private Context mContext;
    private List<Comment> commentList;

    class ViewHolder extends RecyclerView.ViewHolder {

        private View itemView;

        ImageView avatarImage;
        ImageView identityIcon;
        TextView nickname;
        TextView date;
        TextView time;
        TextView source;
        TextView sourceTag;

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

        LinearLayout commentLinear;
        LinearLayout aMeLinear;

        View transmit_layout;
        ImageView transmit_image;
        TextView transmit_comment;
        TextView transmit_weibo_title;
        TextView transmit_weibo_content;

        View weiboFunctionView;
        TextView repostNum;
        TextView commentNum;
        TextView likeNum;


        public ViewHolder(View view) {
            super(view);
            if (view == mHeaderView){
                commentLinear = (LinearLayout) mHeaderView.findViewById(R.id.comment);
                aMeLinear = (LinearLayout) mHeaderView.findViewById(R.id.a_me);
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
            sourceTag = (TextView) view.findViewById(R.id.source_tag);
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

            transmit_layout = view.findViewById(R.id.weibo_transmit_layout);
            transmit_comment = (TextView) transmit_layout.findViewById(R.id.transmit_comment);
            transmit_image = (ImageView) transmit_layout.findViewById(R.id.transmit_weibo_image);
            transmit_weibo_title = (TextView) transmit_layout.findViewById(R.id.transmit_weibo_title);
            transmit_weibo_content = (TextView) transmit_layout.findViewById(R.id.transmit_weibo_passage);

            weiboFunctionView = view.findViewById(R.id.weibo_functions_layout);
            repostNum = (TextView) weiboFunctionView.findViewById(R.id.repost_num);
            commentNum = (TextView) weiboFunctionView.findViewById(R.id.comment_num);
            likeNum = (TextView) weiboFunctionView.findViewById(R.id.like_num);
            weiboFunctionView.setVisibility(View.GONE);

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
            transmit_layout.setVisibility(View.GONE);
        }

    }

    public CommentListAdapter(Context context, List<Comment> list) {
        mContext = context;
        commentList = list;
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
                final Comment comment = commentList.get(position);
                Glide.with(mContext).load(comment.getUser().getProfile_image_url()).into(holder.avatarImage);
                holder.nickname.setText(comment.getUser().getScreen_name());
                holder.date.setText(DateUtils.formatDate(comment.getCreated_at()));
                String source = comment.getSource();
                if (source.length() != 0) {
                    int start = source.indexOf(">") + 1;
                    int end = source.indexOf("</a>");
                    holder.source.setText(source.substring(start, end));
                } else {
                    holder.sourceTag.setVisibility(View.GONE);
                    holder.source.setVisibility(View.GONE);
                }
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(mContext, WeiboDetailActivity.class);
//                        intent.putExtra("weibo", weibo);
//                        mContext.startActivity(intent);
//                        mContext.overridePendingTransition(R.anim.left_in,R.anim.right_out);
//
//                    }
//                });
                holder.goneEverything();
                holder.bodyView.setVisibility(View.VISIBLE);
                holder.body.setText(StringUtils.transformWeiboBody(mContext, holder.body, comment.getText()));
                holder.transmit_layout.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(comment.getStatus().getThumbnail_pic()).centerCrop().into(holder.transmit_image);
                holder.transmit_weibo_content.setText(StringUtils.transformWeiboBody(mContext, holder.body, comment.getStatus().getText()));
                holder.transmit_weibo_title.setText(comment.getStatus().getUser().getScreen_name());
                if(comment.getReply_comment()!=null){
                    holder.transmit_comment.setText(StringUtils.transformWeiboBody(mContext, holder.body, comment.getReply_comment().getText()));
                }else{
                    holder.transmit_comment.setVisibility(View.GONE);
                }


            }
            return;
        } else if (getItemViewType(position) == TYPE_HEADER) {
            holder.commentLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,CommentActivity.class);
                    mContext.startActivity(intent);
                }
            });

            holder.aMeLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,AMeActivity.class);
                    mContext.startActivity(intent);
                }
            });
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
            return commentList.size();
        }else if(mHeaderView == null && mFooterView != null){
            return commentList.size() + 1;
        }else if (mHeaderView != null && mFooterView == null){
            return commentList.size() + 1;
        }else {
            return commentList.size() + 2;
        }
    }



}
