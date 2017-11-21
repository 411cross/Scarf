package com.example.jungle.weixin.PublicUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
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

    public static SpannableString transformWeiboBody(final Context context, final TextView tv, String original) {

        String regexAt = "@[\u4e00-\u9fa5\\w]+";
        String regexTopic = "#[\u4e00-\u9fa5\\w]+#";
        String regexEmoji = "\\[[\u4e00-\u9fa5\\w]+\\]";

        String regexGroup = "(" + regexAt + ")|(" + regexTopic + ")|(" + regexEmoji + ")";

        SpannableString spannableString = new SpannableString(original);
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

            // @部分
            if (atStr != null) {
                int start = matcher.start(1);
                ScarfClickableSpan clickableSpan = new ScarfClickableSpan(context) {
                    @Override
                    public void onClick(View widget) {
                        ToastUtils.showShortToast(context, "用户: " + atStr);
                    }
                };
                spannableString.setSpan(clickableSpan, start, start + atStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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
                    int size = (int) tv.getTextSize();
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

}
