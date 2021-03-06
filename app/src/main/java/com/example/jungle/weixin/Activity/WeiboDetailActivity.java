package com.example.jungle.weixin.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import com.bumptech.glide.Glide;
import com.example.jungle.weixin.Bean.BaseBean.Comment;
import com.example.jungle.weixin.Bean.BaseBean.PicURL;
import com.example.jungle.weixin.Bean.BaseBean.Status;
import com.example.jungle.weixin.Bean.BaseBean.User;
import com.example.jungle.weixin.Bean.ParticularBean.ReadCommentsData;
import com.example.jungle.weixin.Adapter.CommentAdapter;
import com.example.jungle.weixin.Bean.XHRBase.XHRBaseBean;
import com.example.jungle.weixin.Bean.XHRBase.XHRLongStatus;
import com.example.jungle.weixin.CustomControls.AppCompatSwipeBack;
import com.example.jungle.weixin.CustomControls.CommomDialog;
import com.example.jungle.weixin.PublicUtils.CodeUtils;
import com.example.jungle.weixin.PublicUtils.DateUtils;
import com.example.jungle.weixin.PublicUtils.PicUtils;
import com.example.jungle.weixin.PublicUtils.StringUtils;
import com.example.jungle.weixin.PublicUtils.ToastUtils;
import com.example.jungle.weixin.PublicUtils.TypeUtils;
import com.example.jungle.weixin.R;
import com.example.jungle.weixin.RetrofitUtil.H5Service;
import com.example.jungle.weixin.RetrofitUtil.HttpResultSubscriber;
import com.example.jungle.weixin.RetrofitUtil.MyService;
import com.example.jungle.weixin.RetrofitUtil.NetRequestFactory;
import com.example.jungle.weixin.RetrofitUtil.Transform;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import retrofit2.Response;

public class WeiboDetailActivity extends BaseActivity implements View.OnClickListener {
    private PopupWindow popupWindow;

    private Status status;
    private List<Comment> commentList = new ArrayList<>();
    private CommentAdapter adapter;
    private boolean liked;

    private PullToRefreshListView pullLV;

    private View weiboView;

    private View weiboHead;
    private CircleImageView avatarImage;
    private ImageView identityIcon;
    private TextView nickname;
    private TextView date;
    private TextView source;
    private TextView sourceTag;

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
        status = (Status) getIntent().getSerializableExtra("status");

        // 初始化界面
        initView();

        if (status.isLongText()) {
            NetRequestFactory.getInstance().createService(H5Service.class).getLongStatus("test", status.getIdstr())
                    .compose(Transform.<Response<XHRBaseBean<XHRLongStatus>>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<XHRBaseBean<XHRLongStatus>>>() {
                @Override
                public void onSuccess(Response<XHRBaseBean<XHRLongStatus>> longStatus) {
                    if (longStatus.body().getStatus() == 1) {
                        status.setText(StringUtils.transformH5Body(body, longStatus.body().getData().getLongTextContent()).toString());
                        setData();
                    } else {
                        System.out.println("YUYUYUYUYUYUYUYUYUYUYU" + longStatus.body().getException().getError());
                    }
                }

                @Override
                public void _onError(Response<XHRBaseBean<XHRLongStatus>> e) {
                    super._onError(e);
                }
            });
        } else {
            setData();
        }

    }

    private void initView() {

        weiboView = View.inflate(this, R.layout.weibo, null);
        weiboView.findViewById(R.id.weibo_functions_layout).setVisibility(View.GONE);
        weiboView.findViewById(R.id.weibo_transmit_layout).setVisibility(View.GONE);

        weiboHead = (View) weiboView.findViewById(R.id.weibo_head_layout);
        avatarImage = (CircleImageView) weiboHead.findViewById(R.id.avatar);
        identityIcon = (ImageView) weiboHead.findViewById(R.id.identity_icon);
        nickname = (TextView) weiboHead.findViewById(R.id.nickname);
        date = (TextView) weiboHead.findViewById(R.id.date);
        source = (TextView) weiboHead.findViewById(R.id.source);
        sourceTag = (TextView) weiboHead.findViewById(R.id.source_tag);

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
        cancle = (Button) shareMenu.findViewById(R.id.cancel);
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

        NetRequestFactory.getInstance().createService(MyService.class).commentsShow(CodeUtils.getmToken(),status.getId(),50,1).compose(Transform.<Response<ReadCommentsData>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<ReadCommentsData>>() {
            @Override
            public void onSuccess(Response<ReadCommentsData> ReadCommentsData) {
                commentList = Arrays.asList(ReadCommentsData.body().getComments());
                adapter = new CommentAdapter(WeiboDetailActivity.this, commentList);
                pullLV.setAdapter(adapter );
            }

            @Override
            public void _onError(Response<ReadCommentsData> ReadCommentsData) {

            }

        });

        pullLV = (PullToRefreshListView) findViewById(R.id.pull_list_view);
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
        setWeibo();
    }

    public void setWeibo() {

        final User user = status.getUser();
        liked = status.isLiked();
        Glide.with(this).load(user.getAvatar_hd()).into(avatarImage);
        switch (user.getVerified_type()) {
            case 0:
                identityIcon.setVisibility(View.GONE);
                identityIcon.setVisibility(View.VISIBLE);
                identityIcon.setImageResource(R.drawable.avatar_vip_golden);
                break;
            case 2:
                identityIcon.setVisibility(View.GONE);
                identityIcon.setVisibility(View.VISIBLE);
                identityIcon.setImageResource(R.drawable.avatar_enterprise_vip);
                break;
            case 220:
                identityIcon.setVisibility(View.GONE);
                identityIcon.setVisibility(View.VISIBLE);
                identityIcon.setImageResource(R.drawable.avatar_grassroot);
                break;
            default:
                identityIcon.setVisibility(View.GONE);
                identityIcon.setVisibility(View.VISIBLE);
                identityIcon.setImageResource(R.drawable.transparent);
                break;
        }
        nickname.setText(user.getScreen_name());
        // API和H5微博时间处理
        String tempDate = status.getCreated_at();
        if (tempDate.contains(" +")) {
            date.setText(DateUtils.formatDate(tempDate));
        } else {
            date.setText(tempDate);
        }
        // API和H5微博来源处理
        String tempSource = status.getSource();
        if (tempSource.length() != 0) {
            if (tempSource.contains("</a>")) {
                int start = tempSource.indexOf(">") + 1;
                int end = tempSource.indexOf("</a>");
                source.setText(tempSource.substring(start, end));
            } else {
                source.setText(tempSource);
            }
        } else {
            sourceTag.setVisibility(View.GONE);
            source.setVisibility(View.GONE);
        }
        String textBody = StringUtils.transformH5Body(body, status.getText());
        status.setText(textBody);

        avatarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(WeiboDetailActivity.this, UserDetailActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(WeiboDetailActivity.this, UserDetailActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        repostNum.setText(status.getReposts_count() + "");
        commentNum.setText(status.getComments_count() + "");
        likeNum.setText(status.getAttitudes_count() + "");
//        if (liked) {
//            likeIcon.setImageResource(R.drawable.like_pressed_icon);
//        }
//        likeBtn.setOnClickListener(new View.OnClickListener() {
//            int likeNumber = status.getAttitudes_count();
//
//            @Override
//            public void onClick(View v) {
//                if (liked) {
//                    likeIcon.setImageResource(R.drawable.like_icon);
//                    likeNumber = likeNumber - 1;
//                    likeNum.setText(likeNumber + "");
//                    liked = false;
//                } else {
//                    likeIcon.setImageResource(R.drawable.like_pressed_icon);
//                    likeNumber = likeNumber + 1;
//                    likeNum.setText(likeNumber + "");
//                    liked = true;
//                }
//            }
//        });
//        commentBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.showShortToast(WeiboDetailActivity.this, "点击评论");
//            }
//        });
//        repostBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.showShortToast(WeiboDetailActivity.this, "点击转发");
//            }
//        });


        int type = TypeUtils.getStatusType(status);
        switch (type) {
            case 0:
                goneEverything();
                bodyView.setVisibility(View.VISIBLE);
                body.setText(StringUtils.transformWeiboBody(WeiboDetailActivity.this, body, status.getText()));
                System.out.println(body.getText());
                Log.i("+++++++++++++++++++++++", "setWeibo: " + body.getText());
                break;
            case 1:
                goneEverything();
                bodyView.setVisibility(View.VISIBLE);
                multiPicsView.setVisibility(View.VISIBLE);
                body.setText(StringUtils.transformWeiboBody(WeiboDetailActivity.this, body, status.getText()));
                ArrayList<ImageInfo> imageInfo = new ArrayList<>();

                    if (!tempDate.contains(" +")) {
                        List<Status.Pic> urls = status.getPics();
                        if (urls != null) {
                            for (Status.Pic pic : urls) {
                                ImageInfo info = new ImageInfo();
                                info.setThumbnailUrl(PicUtils.getMiddlePic(pic.getUrl()));
                                info.setBigImageUrl(PicUtils.getOrignal(pic.getUrl()));
                                imageInfo.add(info);
                            }
                            multiPicsGrid.setAdapter(new NineGridViewClickAdapter(WeiboDetailActivity.this, imageInfo));
                            if (urls.size() == 1) {
//                    multiPicsGrid.setSingleImageRatio(images.get(0).getWidth() * 1.0f / images.get(0).getHeight());
                                multiPicsGrid.setSingleImageRatio(3.0f / 2);
                            } else {
                                multiPicsGrid.setGridSpacing(16);
                            }
                        }

                    } else {
                        List<PicURL> urls = status.getPic_urls();
                        if (urls != null) {
                            for (PicURL picURL : urls) {
                                ImageInfo info = new ImageInfo();
                                info.setThumbnailUrl(PicUtils.getMiddlePic(picURL.getThumbnail_pic()));
                                info.setBigImageUrl(PicUtils.getOrignal(picURL.getThumbnail_pic()));
                                imageInfo.add(info);
                            }
                        }
                        multiPicsGrid.setAdapter(new NineGridViewClickAdapter(WeiboDetailActivity.this, imageInfo));
                        if (urls != null && urls.size() == 1) {
//                    multiPicsGrid.setSingleImageRatio(images.get(0).getWidth() * 1.0f / images.get(0).getHeight());
                            multiPicsGrid.setSingleImageRatio(3.0f / 2);
                        } else {
                            multiPicsGrid.setGridSpacing(16);
                        }

                    }




                break;
            case 5:
                goneEverything();
                bodyView.setVisibility(View.VISIBLE);
                body.setText(StringUtils.transformWeiboBody(WeiboDetailActivity.this, body, status.getText()));
                reweiboView.setVisibility(View.VISIBLE);
                Status restatus = status.getRetweeted_status();
                String addName = "@" + restatus.getUser().getScreen_name() + ":" + restatus.getText();
                int reType = TypeUtils.getStatusType(restatus);
                switch (reType) {
                    case 0:
                        reweiboBodyView.setVisibility(View.VISIBLE);
                        reweiboBody.setText(StringUtils.transformWeiboBody(WeiboDetailActivity.this, reweiboBody, addName));
                        break;
                    case 1:
                        reweiboBodyView.setVisibility(View.VISIBLE);
                        reweiboMultiPicsView.setVisibility(View.VISIBLE);
                        reweiboBody.setText(StringUtils.transformWeiboBody(WeiboDetailActivity.this, reweiboBody, addName));
                        ArrayList<ImageInfo> reImageInfo = new ArrayList<>();
                        List<PicURL> reUrls = restatus.getPic_urls();
                        if (reUrls != null) {
                            for (PicURL picURL : reUrls) {
                                ImageInfo info = new ImageInfo();
                                info.setThumbnailUrl(PicUtils.getMiddlePic(picURL.getThumbnail_pic()));
                                info.setBigImageUrl(PicUtils.getOrignal(picURL.getThumbnail_pic()));
                                reImageInfo.add(info);
                            }
                        }
                        reweiboMultiPicsGrid.setAdapter(new NineGridViewClickAdapter(WeiboDetailActivity.this, reImageInfo));
                        if (reUrls != null && reUrls.size() == 1) {
//                    multiPicsGrid.setSingleImageRatio(images.get(0).getWidth() * 1.0f / images.get(0).getHeight());
                            reweiboMultiPicsGrid.setSingleImageRatio(3.0f / 2);
                        } else {
                            reweiboMultiPicsGrid.setGridSpacing(16);
                        }
                        break;
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
                Intent intent = new Intent(WeiboDetailActivity.this,Publish.class);
                intent.putExtra("previousActivity",1);
                intent.putExtra("Body",this.status.getText());
                startActivity(intent);
                break;
            case R.id.share_circle:
                break;
            case R.id.more:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, this.status.getText());
                shareIntent = Intent.createChooser(shareIntent, "分享");
                startActivity(shareIntent);
                popupWindow.dismiss();
                break;
            case R.id.cancel:
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
                ClipData mClipData = ClipData.newPlainText("Label", this.status.getText());
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
