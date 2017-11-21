package com.example.jungle.weixin.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.jungle.weixin.Bean.BaseBean.Comment;
import com.example.jungle.weixin.CustomControls.AppCompatSwipeBack;
import com.example.jungle.weixin.Adapter.CommentAdapter;
import com.example.jungle.weixin.Bean.Weibo;
import com.example.jungle.weixin.Bean.WeiboImage;
import com.example.jungle.weixin.PublicUtils.DateUtils;
import com.example.jungle.weixin.PublicUtils.StringUtils;
import com.example.jungle.weixin.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeiboDetailActivity extends AppCompatSwipeBack implements View.OnClickListener {
    private PopupWindow popupWindow;

    private Weibo weibo;
    private List<Comment> commentList = new ArrayList<>();
    private CommentAdapter adapter;

    private PullToRefreshListView pullLV;

    private View weiboView;

    private View weiboHead;
    private ImageView avatarImage;
    private ImageView identityIcon;
    private TextView nickname;
    private TextView date;
    private TextView source;

    private View bodyView;
    private TextView body;

    private View singlePicView;
    private ImageView singlePic;

    private View multiPicsView;
    private NineGridView multiPicsGrid;

    private View videoView;
    private View videoContainerView;
    private ImageView video;

    private View passageView;
    private ImageView passageImage;
    private TextView passageTitle;
    private TextView passageSubTitle;

    private View reweiboView;

    private View reweiboBodyView;
    private TextView reweiboBody;

    private View reweiboSinglePicView;
    private ImageView reweiboSinglePic;

    private View reweiboMultiPicsView;
    private NineGridView reweiboMultiPicsGrid;

    private View reweiboVideoView;
    private View reweiboVideoContainerView;
    private ImageView reweiboVideo;

    private View reweiboPassageView;
    private ImageView reweiboPassageImage;
    private TextView reweiboPassageTitle;
    private TextView reweiboPassageSubTitle;

    private View weiboFunctionView;
    private View repostBtn;
    private ImageView repostIcon;
    private TextView repostNum;
    private View commentBtn;
    private ImageView commentIcon;
    private TextView commentNum;
    private View likeBtn;
    private ImageView likeIcon;
    private TextView likeNum;

    private View floatWeiboFunctionView;
    private View floatRepostBtn;
    private ImageView floatRepostIcon;
    private TextView floatRepostNum;
    private View floatCommentBtn;
    private ImageView floatCommentIcon;
    private TextView floatCommentNum;
    private View floatLikeBtn;
    private ImageView floatLikeIcon;
    private TextView floatLikeNum;

    private View shareMenu;
    private ImageButton share_wechat;
    private ImageButton share_weibo;
    private ImageButton share_circle;
    private Button cancle;
    private ImageButton more;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_detail);

        shareMenu  = LayoutInflater.from(WeiboDetailActivity.this).inflate(R.layout.shareto, null);
        initShare();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("微博");
        }

        // 传入的微博
        weibo = (Weibo) getIntent().getSerializableExtra("weibo");
        // 初始化界面
        initView();
        setData();
    }

    private void initView() {

        weiboView = View.inflate(this, R.layout.weibo, null);
        weiboView.findViewById(R.id.weibo_functions_layout).setVisibility(View.GONE);

        weiboHead = (View) weiboView.findViewById(R.id.weibo_head_layout);
        avatarImage = (ImageView) weiboHead.findViewById(R.id.avatar);
        identityIcon = (ImageView) weiboHead.findViewById(R.id.identity_icon);
        nickname = (TextView) weiboHead.findViewById(R.id.nickname);
        date = (TextView) weiboHead.findViewById(R.id.date);
        source = (TextView) weiboHead.findViewById(R.id.source);

        bodyView = (View) weiboView.findViewById(R.id.body_layout);
        body = (TextView) bodyView.findViewById(R.id.body);

        singlePicView = (View) weiboView.findViewById(R.id.single_pic_layout);
        singlePic = (ImageView) singlePicView.findViewById(R.id.single_pic);

        multiPicsView = (View) weiboView.findViewById(R.id.multi_pics_layout);
        multiPicsGrid = (NineGridView) multiPicsView.findViewById(R.id.multi_pics_grid);

        videoView = (View) weiboView.findViewById(R.id.video_layout);
        videoContainerView = (View) videoView.findViewById(R.id.video_container);
        video = (ImageView) videoView.findViewById(R.id.video);

        passageView = (View) weiboView.findViewById(R.id.passage_layout);
        passageImage = (ImageView) passageView.findViewById(R.id.passage_image);
        passageTitle = (TextView) passageView.findViewById(R.id.passage_title);
        passageSubTitle = (TextView) passageView.findViewById(R.id.passage_subtitle);

        reweiboView = (View) weiboView.findViewById(R.id.reweibo_layout);

        reweiboBodyView = (View) reweiboView.findViewById(R.id.reweibo_body_layout);
        reweiboBody = (TextView) reweiboBodyView.findViewById(R.id.body);

        reweiboSinglePicView = (View) reweiboView.findViewById(R.id.reweibo_single_pic_layout);
        reweiboSinglePic = (ImageView) reweiboSinglePicView.findViewById(R.id.single_pic);

        reweiboMultiPicsView = (View) reweiboView.findViewById(R.id.reweibo_multi_pics_layout);
        reweiboMultiPicsGrid = (NineGridView) reweiboMultiPicsView.findViewById(R.id.multi_pics_grid);

        reweiboVideoView = (View) reweiboView.findViewById(R.id.reweibo_video_layout);
        reweiboVideoContainerView = (View) reweiboVideoView.findViewById(R.id.video_container);
        reweiboVideo = (ImageView) reweiboVideoView.findViewById(R.id.video);

        reweiboPassageView = (View) reweiboView.findViewById(R.id.reweibo_passage_layout);
        reweiboPassageImage = (ImageView) reweiboPassageView.findViewById(R.id.passage_image);
        reweiboPassageTitle = (TextView) reweiboPassageView.findViewById(R.id.passage_title);
        reweiboPassageSubTitle = (TextView) reweiboPassageView.findViewById(R.id.passage_subtitle);

        share_wechat = (ImageButton)shareMenu.findViewById(R.id.share_wechat);
        share_weibo = (ImageButton)shareMenu.findViewById(R.id.share_weibo);
        share_circle = (ImageButton)shareMenu.findViewById(R.id.share_circle);
        cancle = (Button) shareMenu.findViewById(R.id.cancle);
        more = (ImageButton)shareMenu.findViewById(R.id.more);

        initFunctionTab();
        initListView();

    }

    public void initFunctionTab() {

        // Header部分Tab
        weiboFunctionView = View.inflate(this, R.layout.weibo_functions, null);
        likeBtn = weiboFunctionView.findViewById(R.id.like_btn);
        likeIcon = (ImageView) weiboFunctionView.findViewById(R.id.like_image);
        likeNum = (TextView) weiboFunctionView.findViewById(R.id.like_num);
        likeIcon.setImageResource(R.drawable.like_icon);
        commentBtn = weiboFunctionView.findViewById(R.id.comment_btn);
        commentIcon = (ImageView) weiboFunctionView.findViewById(R.id.comment_image);
        commentNum = (TextView) weiboFunctionView.findViewById(R.id.comment_num);
        commentIcon.setImageResource(R.drawable.comment_icon);
        repostBtn = weiboFunctionView.findViewById(R.id.repost_btn);
        repostIcon = (ImageView) weiboFunctionView.findViewById(R.id.repost_image);
        repostNum = (TextView) weiboFunctionView.findViewById(R.id.repost_num);
        repostIcon.setImageResource(R.drawable.repost_icon);

        repostBtn.setOnClickListener(this);
        commentBtn.setOnClickListener(this);
        likeBtn.setOnClickListener(this);

        // 悬浮部分Tab
        floatWeiboFunctionView = findViewById(R.id.function_float);
        floatRepostBtn = floatWeiboFunctionView.findViewById(R.id.repost_btn);
        floatRepostIcon = (ImageView) floatWeiboFunctionView.findViewById(R.id.repost_image);
        floatRepostNum = (TextView) floatWeiboFunctionView.findViewById(R.id.repost_num);
        floatCommentBtn = floatWeiboFunctionView.findViewById(R.id.comment_btn);
        floatCommentIcon = (ImageView) floatWeiboFunctionView.findViewById(R.id.comment_image);
        floatCommentNum = (TextView) floatWeiboFunctionView.findViewById(R.id.comment_num);
        floatLikeBtn = floatWeiboFunctionView.findViewById(R.id.like_btn);
        floatLikeIcon = (ImageView) floatWeiboFunctionView.findViewById(R.id.like_image);
        floatLikeNum = (TextView) floatWeiboFunctionView.findViewById(R.id.like_num);
        floatRepostIcon.setImageResource(R.drawable.repost_icon);
        floatCommentIcon.setImageResource(R.drawable.comment_icon);
        floatLikeIcon.setImageResource(R.drawable.like_icon);

        floatRepostBtn.setOnClickListener(this);
        floatCommentBtn.setOnClickListener(this);
        floatLikeBtn.setOnClickListener(this);

        share_wechat.setOnClickListener(this);
        share_weibo.setOnClickListener(this);
        share_circle.setOnClickListener(this);
        more.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }

    public void initListView() {
        // ListView主体
        pullLV = (PullToRefreshListView) findViewById(R.id.pull_list_view);
        adapter = new CommentAdapter(this, commentList);
        pullLV.setAdapter(adapter);
        // HeaderView
        final ListView listView = pullLV.getRefreshableView();
        listView.addHeaderView(weiboView);
        listView.addHeaderView(weiboFunctionView);

        pullLV.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                floatWeiboFunctionView.setVisibility(firstVisibleItem >= 2 ? View.VISIBLE : View.GONE);
            }
        });

    }

    public void setData() {
        initFakeComments();
        setWeibo();
    }

    public void initFakeComments(){
        Comment comment = new Comment("", 1, "阿宝", "哈利路亚", "Fri Nov 10 17:16:55 +0800 2017", 250);
        for (int i = 0; i < 10; i++) {
            commentList.add(comment);
        }
    }

    public void setWeibo() {
        avatarImage.setImageResource(weibo.getAvatarURL());
        nickname.setText(weibo.getNickname());
        date.setText(DateUtils.formatDate(weibo.getDate()));
        source.setText(weibo.getSource());
        switch (weibo.getType()) {
            case 0:
                goneEverything();
                bodyView.setVisibility(View.VISIBLE);
                body.setText(StringUtils.transformWeiboBody(this, body, weibo.getBody()));
                break;
            case 1:
                goneEverything();
                singlePicView.setVisibility(View.VISIBLE);
                break;
            case 2:
                goneEverything();
                bodyView.setVisibility(View.VISIBLE);
                singlePicView.setVisibility(View.VISIBLE);
                body.setText(StringUtils.transformWeiboBody(this, body, weibo.getBody()));
                singlePic.setImageResource(weibo.getImage());
                break;
            case 3:
                goneEverything();
                bodyView.setVisibility(View.VISIBLE);
                multiPicsView.setVisibility(View.VISIBLE);
                body.setText(StringUtils.transformWeiboBody(this, body, weibo.getBody()));
                ArrayList<ImageInfo> imageInfo = new ArrayList<>();
                List<WeiboImage> images = weibo.getImageurls();
                if (images != null) {
                    for (WeiboImage image : images) {
                        ImageInfo info = new ImageInfo();
                        info.setThumbnailUrl(image.getUrl());
                        info.setBigImageUrl(image.getUrl());
                        imageInfo.add(info);
                    }
                }
                multiPicsGrid.setAdapter(new NineGridViewClickAdapter(this, imageInfo));
                if (images != null && images.size() == 1) {
//                    multiPicsGrid.setSingleImageRatio(images.get(0).getWidth() * 1.0f / images.get(0).getHeight());
                    multiPicsGrid.setSingleImageRatio(3.0f / 2);
                } else {
                    multiPicsGrid.setGridSpacing(16);
                }
                break;
        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share_wechat:
                break;
            case R.id.share_weibo:
                break;
            case R.id.share_circle:
                break;
            case R.id.more:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, this.weibo.getBody());
                shareIntent = Intent.createChooser(shareIntent, "分享");
                startActivity(shareIntent);
                popupWindow.dismiss();
                break;
            case R.id.cancle:
                popupWindow.dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                break;
            case R.id.shared:
                popupWindow.showAtLocation(shareMenu, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.copy:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClipData = ClipData.newPlainText("Label", this.weibo.getBody());
                cm.setPrimaryClip(mClipData);
                break;
            default:
                break;
        }

        return true;
    }
    private void initShare(){
        popupWindow = new PopupWindow(shareMenu, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
    }

    @Override
    public void onBackPressed() {
        scrollToFinishActivity();//左滑退出activity
    }

}
