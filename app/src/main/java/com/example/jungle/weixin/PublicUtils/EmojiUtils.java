package com.example.jungle.weixin.PublicUtils;

import com.example.jungle.weixin.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by derrickJ on 2017/11/10.
 */

public class EmojiUtils {

    public static Map<String, Integer> emojiMap;

    static {
        emojiMap = new HashMap<String, Integer>();
        emojiMap.put("[害羞]", R.drawable.dit);
        emojiMap.put("[吐舌]", R.drawable.diy);
        emojiMap.put("[傻笑]", R.drawable.diz);
        emojiMap.put("[冷笑]", R.drawable.djg);
        emojiMap.put("[惊恐]", R.drawable.djl);
    }

    public static int getEmojiByName(String emojiName) {
        Integer integer = emojiMap.get(emojiName);
        return integer == null ? -1 : integer;
    }

}
