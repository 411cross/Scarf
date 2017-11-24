package com.example.jungle.weixin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jungle.weixin.Bean.BaseBean.Comment;
import com.example.jungle.weixin.Bean.BaseBean.User;
import com.example.jungle.weixin.PublicUtils.DateUtils;
import com.example.jungle.weixin.PublicUtils.StringUtils;
import com.example.jungle.weixin.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by derrickJ on 2017/11/10.
 */

public class CommentAdapter extends BaseAdapter {

    private Context context;
    private List<Comment> commentList;
    private LayoutInflater inflater;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.comment_item, null);
            holder = new ViewHolder();
            holder.avatarImage = (CircleImageView) convertView.findViewById(R.id.avatar);
            holder.identityIcon = (ImageView) convertView.findViewById(R.id.identity_icon);
            holder.nickname = (TextView) convertView.findViewById(R.id.nickname);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.comment = (TextView) convertView.findViewById(R.id.comment_body);
            holder.likeBtn = (View) convertView.findViewById(R.id.like_btn);
            holder.likeIcon = (ImageView) convertView.findViewById(R.id.like_image);
            holder.likeNum = (TextView) convertView.findViewById(R.id.like_num);
            holder.likeBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Comment comment = commentList.get(position);
        User user = comment.getUser();
        Glide.with(context).load(comment.getUser().getProfile_image_url()).fitCenter().into(holder.avatarImage);
        switch (user.getVerified_type()) {
            case 0:
                holder.identityIcon.setImageResource(R.drawable.avatar_vip_golden);
                break;
            case 2:
                holder.identityIcon.setImageResource(R.drawable.avatar_enterprise_vip);
                break;
            case 220:
                holder.identityIcon.setImageResource(R.drawable.avatar_grassroot);
                break;
            default:
                holder.identityIcon.setVisibility(View.GONE);
                break;
        }
        holder.nickname.setText(user.getScreen_name());
        holder.date.setText(DateUtils.formatDate(comment.getCreated_at()));
        holder.likeNum.setVisibility(View.GONE);
        holder.comment.setText(StringUtils.transformWeiboBody(context, holder.comment, comment.getText()));

        return convertView;
    }

    public static class ViewHolder {

        private CircleImageView avatarImage;
        private ImageView identityIcon;
        private TextView nickname;
        private TextView date;
        private TextView comment;
        private View likeBtn;
        private ImageView likeIcon;
        private TextView likeNum;

    }

}
