package com.example.jungle.weixin.PublicUtils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jungle.weixin.Activity.PublicWebViewActivity;
import com.example.jungle.weixin.Activity.UserDetailActivity;
import com.example.jungle.weixin.Adapter.HomePageAdapter;
import com.example.jungle.weixin.Bean.BaseBean.User;
import com.example.jungle.weixin.Bean.ParticularBean.StatusList;
import com.example.jungle.weixin.R;
import com.example.jungle.weixin.RetrofitUtil.HttpResultSubscriber;
import com.example.jungle.weixin.RetrofitUtil.MyService;
import com.example.jungle.weixin.RetrofitUtil.NetRequestFactory;
import com.example.jungle.weixin.RetrofitUtil.Transform;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Response;

import static io.vov.vitamio.utils.Log.TAG;

/**
 * Created by derrickJ on 2017/11/10.
 */

public class StringUtils {

    // 这玩意儿有错！！！！！！！！！！！！！
    public static String transformH5Body(final TextView tv, String original) {

//        String original = "土耳其街头的手工花生糖，开始我以为是一坨<span class=\"url-icon\"><img src=\"//h5.sinaimg.cn/m/emoticon/icon/default/d_yunbei-c6964bf237.png\" style=\"width:1em;height:1em;\" alt=\"[允悲]\"></span><span class=\"url-icon\"><img src=\"//h5.sinaimg.cn/m/emoticon/icon/default/d_yunbei-c6964bf237.png\" style=\"width:1em;height:1em;\" alt=\"[允悲]\"></span><span class=\"url-icon\"><img src=\"//h5.sinaimg.cn/m/emoticon/icon/default/d_yunbei-c6964bf237.png\" style=\"width:1em;height:1em;\" alt=\"[允悲]\"></span><br/><br/><a class='k' href='https://m.weibo.cn/k/%E8%B0%9C%E4%B9%8B%E5%BE%AE%E7%AC%91?from=feed'>#谜之微笑#</a><a class='k' href='https://m.weibo.cn/k/%E5%BE%AE%E5%8D%9A%E6%90%9E%E7%AC%91%E6%8E%92%E8%A1%8C%E6%A6%9C?from=feed'>#微博搞笑排行榜#</a><a class='k' href='https://m.weibo.cn/k/%E6%90%9E%E7%AC%91%E7%BA%A2%E4%BA%BA%E5%91%A8?from=feed'>#搞笑红人周#</a><a class='k' href='https://m.weibo.cn/k/%E5%B9%BD%E9%BB%98%E6%90%9E%E7%AC%91?from=feed'>#幽默搞笑#</a><a class='k' href='https://m.weibo.cn/k/%E6%90%9E%E7%AC%91%E8%A7%86%E9%A2%91?from=feed'>#搞笑视频#</a> <a data-url=\"http://t.cn/RYqLfPl\" href=\"https://m.weibo.cn/p/index?containerid=2304440ebc6ddc92fe7e29221056e88f47e34a&url_type=39&object_type=video&pos=1&luicode=10000011&lfid=102803&ep=FwOiXebvR%2C5337648308%2CFwOiXebvR%2C5337648308\" data-hide=\"\"><span class=\"url-icon\"><img src=\"https://h5.sinaimg.cn/upload/2015/09/25/3/timeline_card_small_video_default.png\"></span></i><span class=\"surl-text\">司机发车了的秒拍视频</a> ​​​";
        String regexH5EmojiHead = "<span";
        String regexH5EmojiMid = "\" alt=\"";
        String regexHrNoEmojiMid = ".png\">";
        String regexH5EmojiEnd = "\"></span>";

        String regexH5AtHead = "<a href=\'https";
        String regexH5AtMid = "\'>";

        String regexH5TopicHead = "<a class";
        String regexH5TopicMid = "\'>";

        String regexH5AtTopicEnd = "</a>";

        String regexH5LongHead = "<a href=\"/status";
        String regexH5LongMid = "\">";

        String regexH5Br = "<br/>";

        original = deleteHeadByMid(regexH5EmojiHead, regexH5EmojiMid, original);
        original = deleteHeadByMid(regexH5EmojiHead, regexHrNoEmojiMid, original);
        original = original.replaceAll(regexH5EmojiEnd, "");

        original = deleteHeadByHead(regexH5AtHead, regexH5AtMid, original);
        original = deleteHeadByHead(regexH5TopicHead, regexH5TopicMid, original);

        original = original.replaceAll(regexH5AtTopicEnd, "");

        original = deleteHeadByHead(regexH5LongHead, regexH5LongMid, original);

        original = original.replaceAll(regexH5Br, "\n");

        System.out.println(original);

        return original;

    }

    public static String deleteHeadByHead(String head, String middle, String original) {


        while (original.contains(head)) {
            int start = original.indexOf(head);
            int mid = original.indexOf(middle);
            String sub = original.substring(start, mid + middle.length());
            original = original.replace(sub, "");
        }

        return original;

    }

    public static String deleteHeadByMid(String head, String middle, String original) {

        while (original.contains(middle)) {
            int start = original.indexOf(head);
            int mid = original.indexOf(middle);
            String sub = original.substring(start, mid + middle.length());
            original = original.replace(sub, "");
        }

        return original;

    }

    public static SpannableStringBuilder transformWeiboBody(final Context context, final TextView tv, String original) {

        String regexAt = "@[\u4e00-\u9fa5a-zA-Z0-9+&@#\\-/%?=~_\\\\\\\\-|!:,\\\\\\\\.;]+";
        String regexTopic = "#[\u4e00-\u9fa5a-zA-Z0-9+&@#/%?=~_\\\\\\\\-|!:,\\\\\\\\.;]+#";
        String regexEmoji = "\\[[\u4e00-\u9fa5\\w]+\\]";
        String regexLink = "http://[a-zA-Z0-9+&@#/%?=~_\\\\-|!:,\\\\.;]*[a-zA-Z0-9+&@#/%=~_|]";
        String regexMore = "全文： http://m\\.weibo\\.cn[a-zA-Z0-9+&@#/%?=~_\\\\-|!:,\\\\.;]*[a-zA-Z0-9+&@#/%=~_|]";


        String regexGroup = "(" + regexAt + ")|(" + regexTopic + ")|("
                + regexEmoji + ")|(" + regexLink + ")|(" + regexMore + ")";

        String recognizeColonString = original.replaceAll(":", " : ");
        String recognizeHttpString = recognizeColonString.replaceAll("http : ", "http:");

        SpannableStringBuilder spannableString = new SpannableStringBuilder(recognizeHttpString);
        Pattern pattern = Pattern.compile(regexGroup);
        Matcher matcher = pattern.matcher(spannableString);

        if (matcher.find()) {
            tv.setMovementMethod(LinkMovementMethod.getInstance());
            matcher.reset();
        }

        while (matcher.find()) {
            final String atStr = matcher.group(1);
            final String topicStr = matcher.group(2);
            String emojiStr = matcher.group(3);
            final String linkStr = matcher.group(4);
            final String moreStr = matcher.group(5);

            int size = (int) tv.getTextSize();

            // @部分
            if (atStr != null) {
                int start = matcher.start(1);
                int end = matcher.end(1);
                ScarfClickableSpan clickableSpan = new ScarfClickableSpan(context) {
                    @Override
                    public void onClick(View widget) {
//                        ToastUtils.showShortToast(context, "用户: " + atStr);
                        String username = atStr.replaceAll("@", "");
                        goToUserDetail(context, username);
                    }
                };
                spannableString.removeSpan(atStr);
                spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            // #话题部分
            if (topicStr != null) {
                int start = matcher.start(2);
                ScarfClickableSpan clickableSpan = new ScarfClickableSpan(context) {
                    @Override
                    public void onClick(View widget) {
                        ToastUtils.showShortToast(context, "话题: " + topicStr);
                    }
                };
                spannableString.setSpan(clickableSpan, start, start + topicStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            // 😊表情部分
            if (emojiStr != null) {
                int start = matcher.start(3);

                int imgRes = EmojiUtils.getEmojiByName(emojiStr);
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imgRes);

                if (bitmap != null) {
                    bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
                    ImageSpan imageSpan = new ImageSpan(context, bitmap);
                    spannableString.setSpan(imageSpan, start, start + emojiStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }

            // 🔗链接部分
            if (linkStr != null) {
                int start = matcher.start(4);
                int end = matcher.end(4);
                ScarfClickableSpan clickableSpan = new ScarfClickableSpan(context) {
                    @Override
                    public void onClick(View widget) {
//                        ToastUtils.showShortToast(context, "链接: " + linkStr);
                        Intent intent = new Intent(context, PublicWebViewActivity.class);
                        intent.putExtra("url", linkStr);
                        context.startActivity(intent);
                    }
                };
                SpannableStringBuilder urlSpannableString = getUrlTextSpannableString(context, linkStr, size);
                spannableString.replace(start, end, urlSpannableString);
                spannableString.setSpan(clickableSpan, start, start + urlSpannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                matcher = pattern.matcher(spannableString);
            }

            // 全文部分
            if (moreStr != null) {
                int start = matcher.start(5);
                int end = matcher.end(5);
                ScarfClickableSpan clickableSpan = new ScarfClickableSpan(context) {
                    @Override
                    public void onClick(View widget) {
                        ToastUtils.showShortToast(context, moreStr);
                    }
                };
                SpannableStringBuilder moreSpannableString = getMoreSpannableString(moreStr);
                spannableString.replace(start, end, moreSpannableString);
                spannableString.setSpan(clickableSpan, start, start + moreSpannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                matcher = pattern.matcher(spannableString);
            }

        }

        return spannableString;
    }

    public static SpannableStringBuilder transformPublish(final Context context, final EditText tv, String original) {

        String regexAt = "@[\u4e00-\u9fa5a-zA-Z0-9+&@#\\-/%?=~_\\\\\\\\-|!:,\\\\\\\\.;]+";
        String regexTopic = "#[\u4e00-\u9fa5a-zA-Z0-9+&@#/%?=~_\\\\\\\\-|!:,\\\\\\\\.;]+#";
        String regexEmoji = "\\[[\u4e00-\u9fa5\\w]+\\]";

        String regexGroup = "(" + regexAt + ")|(" + regexTopic + ")|(" + regexEmoji + ")";

        String recognizeColonString = original.replaceAll(":", " : ");
        String recognizeHttpString = recognizeColonString.replaceAll("http : ", "http:");

        SpannableStringBuilder spannableString = new SpannableStringBuilder(recognizeHttpString);
        Pattern pattern = Pattern.compile(regexGroup);
        Matcher matcher = pattern.matcher(spannableString);

        if (matcher.find()) {
            tv.setMovementMethod(LinkMovementMethod.getInstance());
            matcher.reset();
        }

        while (matcher.find()) {
            final String atStr = matcher.group(1);
            final String topicStr = matcher.group(2);
            String emojiStr = matcher.group(3);

            int size = (int) tv.getTextSize();

            // @部分
            if (atStr != null) {
                int start = matcher.start(1);
                int end = matcher.end(1);
                ScarfClickableSpan clickableSpan = new ScarfClickableSpan(context) {
                    @Override
                    public void onClick(View widget) {
                    }
                };
                spannableString.removeSpan(atStr);
                spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            // #话题部分
            if (topicStr != null) {
                int start = matcher.start(2);
                ScarfClickableSpan clickableSpan = new ScarfClickableSpan(context) {
                    @Override
                    public void onClick(View widget) {
                    }
                };
                spannableString.setSpan(clickableSpan, start, start + topicStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            // 😊表情部分
            if (emojiStr != null) {
                int start = matcher.start(3);

                int imgRes = EmojiUtils.getEmojiByName(emojiStr);
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imgRes);

                if (bitmap != null) {
                    bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
                    ImageSpan imageSpan = new ImageSpan(context, bitmap);
                    spannableString.setSpan(imageSpan, start, start + emojiStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }

        }

        return spannableString;
    }

    static class ScarfClickableSpan extends ClickableSpan {

        private Context context;

        public ScarfClickableSpan(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View widget) {

        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(context.getResources().getColor(R.color.colorLink));
            ds.setUnderlineText(false);
        }
    }

    private static SpannableStringBuilder getUrlTextSpannableString(Context context, String source, int size) {
        SpannableStringBuilder builder = new SpannableStringBuilder(source);
        String prefix = " ";
        builder.replace(0, source.length(), prefix);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.link_icon);

        if (bitmap != null) {
            bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
            ImageSpan imageSpan = new ImageSpan(context, bitmap);
            builder.setSpan(imageSpan, 0, prefix.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append(" 网页链接");
        }
        return builder;
    }

    private static SpannableStringBuilder getMoreSpannableString(String source) {
        SpannableStringBuilder builder = new SpannableStringBuilder(source);
        String prefix = " ";
        builder.replace(0, source.length(), prefix);
        builder.append("全文");
        return builder;
    }

    public static String getMoreURL(String original) {

        String result = "noMore";

        String regexMore = "全文： http://m\\.weibo\\.cn[a-zA-Z0-9+&@#/%?=~_\\\\-|!:,\\\\.;]*[a-zA-Z0-9+&@#/%=~_|]";

        String regexGroup = "(" + regexMore + ")";

        SpannableStringBuilder spannableString = new SpannableStringBuilder(original);
        Pattern pattern = Pattern.compile(regexGroup);
        Matcher matcher = pattern.matcher(spannableString);

        while (matcher.find()) {
            final String moreStr = matcher.group(1);
            if (moreStr != null) {
                result = moreStr.replaceAll("全文： ", "");
            }
        }

        return result;
    }

    public static void goToUserDetail(final Context context, String username) {
        NetRequestFactory.getInstance().createService(MyService.class).usersShowWithName(CodeUtils.getmToken(), username).compose(Transform.<Response<User>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<User>>() {
            @Override
            public void onSuccess(Response<User> userResponse) {
                User user = userResponse.body();
                Intent intent = new Intent(context, UserDetailActivity.class);
                intent.putExtra("user", user);
                context.startActivity(intent);
            }

            @Override
            public void _onError(Response<User> userResponse) {

            }

        });
    }

    private static SpannableStringBuilder deleteTrash(String source) {
        SpannableStringBuilder builder = new SpannableStringBuilder(source);
        String prefix = " ";
        builder.replace(0, source.length(), prefix);
        builder.append("");
        return builder;
    }

    private static SpannableStringBuilder newLine(String source) {
        SpannableStringBuilder builder = new SpannableStringBuilder(source);
        String prefix = " ";
        builder.replace(0, source.length(), prefix);
        builder.append("\n");
        return builder;
    }

}
