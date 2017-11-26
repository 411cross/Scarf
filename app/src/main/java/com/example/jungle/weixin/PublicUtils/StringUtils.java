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

/**
 * Created by derrickJ on 2017/11/10.
 */

public class StringUtils {

    public static SpannableStringBuilder transformWeiboBody(final Context context, final TextView tv, String original) {

        String regexAt = "@[\u4e00-\u9fa5a-zA-Z0-9+&@#\\-/%?=~_\\\\\\\\-|!:,\\\\\\\\.;]+";
        String regexTopic = "#[\u4e00-\u9fa5a-zA-Z0-9+&@#/%?=~_\\\\\\\\-|!:,\\\\\\\\.;]+#";
        String regexEmoji = "\\[[\u4e00-\u9fa5\\w]+\\]";
        String regexLink = "http://[a-zA-Z0-9+&@#/%?=~_\\\\-|!:,\\\\.;]*[a-zA-Z0-9+&@#/%=~_|]";
        String regexMore = "全文： http://m\\.weibo\\.cn[a-zA-Z0-9+&@#/%?=~_\\\\-|!:,\\\\.;]*[a-zA-Z0-9+&@#/%=~_|]";

        String regexGroup = "(" + regexAt + ")|(" + regexTopic + ")|(" + regexEmoji + ")|(" + regexLink + ")|(" + regexMore + ")";

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

}
