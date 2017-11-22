package com.example.jungle.weixin.PublicUtils;

import android.content.Context;
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
import android.widget.TextView;

import com.example.jungle.weixin.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by derrickJ on 2017/11/10.
 */

public class StringUtils {

    public static SpannableStringBuilder transformWeiboBody(final Context context, final TextView tv, String original) {

        String regexAt = "@[\u4e00-\u9fa5\\w]+";
        String regexTopic = "#[\u4e00-\u9fa5\\w]+#";
        String regexEmoji = "\\[[\u4e00-\u9fa5\\w]+\\]";
        String regexLink = "http://[a-zA-Z0-9+&@#/%?=~_\\\\-|!:,\\\\.;]*[a-zA-Z0-9+&@#/%=~_|]";

        String regexGroup = "(" + regexAt + ")|(" + regexTopic + ")|(" + regexEmoji + ")|(" + regexLink + ")";

        SpannableStringBuilder spannableString = new SpannableStringBuilder(original);
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

            int size = (int) tv.getTextSize();

            // @部分
            if (atStr != null) {
                int start = matcher.start(1);
                int end = matcher.end(1);
                ScarfClickableSpan clickableSpan = new ScarfClickableSpan(context) {
                    @Override
                    public void onClick(View widget) {
                        ToastUtils.showShortToast(context, "用户: " + atStr);
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
                System.out.println("_+_++_+_+_+_+_+_+_+_+" + start);
                System.out.println("+_+_+_+_+_+_+_+_+_+_+" + end);
                ScarfClickableSpan clickableSpan = new ScarfClickableSpan(context) {
                    @Override
                    public void onClick(View widget) {
                        ToastUtils.showShortToast(context, "链接: " + linkStr);
                    }
                };
                SpannableStringBuilder urlSpannableString = getUrlTextSpannableString(context, linkStr, size);
                spannableString.replace(start, end, urlSpannableString);
                spannableString.setSpan(clickableSpan, start, start + urlSpannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                matcher = pattern.matcher(spannableString);
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
//        Drawable drawable = context.getResources().getDrawable(R.drawable.link_icon);
//        drawable.setBounds(0, 0, size, size);
//        builder.setSpan(drawable, 0, prefix.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        builder.append(" 网页链接");
//
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.link_icon);

        if (bitmap != null) {
            bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
            ImageSpan imageSpan = new ImageSpan(context, bitmap);
            builder.setSpan(imageSpan, 0, prefix.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append(" 网页链接");
        }
        return builder;
    }

}
