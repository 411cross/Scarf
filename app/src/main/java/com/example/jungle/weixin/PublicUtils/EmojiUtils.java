package com.example.jungle.weixin.PublicUtils;

import com.example.jungle.weixin.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by derrickJ on 2017/11/10.
 */

public class EmojiUtils {

    public static Map<String, Integer> emojiMap;
    private static int[] mImageIds = new int[] {R.drawable.d_aini,R.drawable.d_baibai, R.drawable.d_beishang,
            R.drawable.d_bishi,R.drawable.d_bizui,R.drawable.d_chanzui,R.drawable.d_chijing, R.drawable.d_dahaqi,
            R.drawable.d_dalian,R.drawable.d_ding,R.drawable.d_doge, R.drawable.d_ganmao,R.drawable.d_guzhang,
            R.drawable.d_haha,R.drawable.d_haixiu,R.drawable.d_han, R.drawable.d_hehe,R.drawable.d_heixian,
            R.drawable.d_heng,R.drawable.d_huaxin,R.drawable.d_jiyan, R.drawable.d_keai, R.drawable.d_kelian,
            R.drawable.d_ku,R.drawable.d_kun,R.drawable.d_landelini, R.drawable.d_lei,R.drawable.d_nu,R.drawable.d_numa,
            R.drawable.d_qian,R.drawable.d_qinqin,R.drawable.d_shayan,R.drawable.d_shengbing,
            R.drawable.d_shiwang,R.drawable.d_shuai,R.drawable.d_shuijiao,R.drawable.d_sikao, R.drawable.d_taikaixin,
            R.drawable.d_touxiao,R.drawable.d_tu,R.drawable.d_tuzi,R.drawable.d_wabishi, R.drawable.d_weiqu,
            R.drawable.d_xiaoku,R.drawable.d_xixi,R.drawable.d_xu, R.drawable.d_yinxian, R.drawable.d_yiwen,
            R.drawable.d_youhengheng,R.drawable.d_zuohengheng,R.drawable.d_yun,R.drawable.d_zhuakuang, R.drawable.f_hufen,
            R.drawable.d_shenshou,R.drawable.d_xiongmao, R.drawable.d_zhutou,R.drawable.f_geili,
            R.drawable.f_jiong,R.drawable.f_shenma,R.drawable.f_v5,R.drawable.f_xi, R.drawable.h_buyao,
            R.drawable.h_good,R.drawable.h_lai,R.drawable.h_haha,R.drawable.h_ok,R.drawable.h_ruo, R.drawable.h_woshou,
            R.drawable.h_ye,R.drawable.h_zan,R.drawable.h_zuoyi,R.drawable.l_shangxin,R.drawable.l_xin, R.drawable.o_dangao,
            R.drawable.o_feiji,R.drawable.o_ganbei,R.drawable.o_huatong,R.drawable.o_lazhu, R.drawable.o_liwu,
            R.drawable.o_lvsidai,R.drawable.o_weiguan,R.drawable.o_yinyue, R.drawable.o_zhaoxiangji,R.drawable.o_zhong,
            R.drawable.w_fuyun,R.drawable.w_shachenbao,R.drawable.w_taiyang, R.drawable.w_weifeng,R.drawable.w_xianhua,
            R.drawable.w_xiayu,R.drawable.w_yueliang,R.drawable.d_aoteman,R.drawable.d_feizao,R.drawable.o_weibo,
            R.drawable.d_nanhaier,R.drawable.d_nvhaier,R.drawable.d_lang,R.drawable.d_miao};
    private static String[] name = new String[]{
            "爱你","拜拜","悲伤","鄙视","闭嘴","馋嘴","吃惊","哈欠","打脸","顶","doge","感冒","鼓掌",
            "哈哈","害羞","汗","呵呵","黑线","哼","花心","挤眼","可爱","可怜","酷","困","白眼","泪",
            "怒","怒骂","钱","亲亲","傻眼","生病", "失望","衰","睡觉","思考","太开心","偷笑","吐","兔子",
            "挖鼻","委屈", "笑cry","嘻嘻","嘘","阴险","疑问","右哼哼","左哼哼","晕","抓狂","互粉","神兽",
            "熊猫","猪头","给力","囧","神马","威武","喜","不要","good","来","haha","ok","弱","握手","耶",
            "赞","作揖","伤心","心","蛋糕","飞机","干杯","话筒","蜡烛","礼物","绿丝带","围观","音乐","照相机",
            "钟","浮云","沙尘暴","太阳","微风","鲜花","下雨","月亮","奥特曼","肥皂","围脖","男孩儿","女孩儿",
            "浪","喵喵"
    };

    static {
        emojiMap = new HashMap<String, Integer>();
//        emojiMap.put("[害羞]", R.drawable.dit);
//        emojiMap.put("[吐舌]", R.drawable.diy);
//        emojiMap.put("[傻笑]", R.drawable.diz);
//        emojiMap.put("[冷笑]", R.drawable.djg);
//        emojiMap.put("[惊恐]", R.drawable.djl);
        for(int i=0;i<mImageIds.length;i++){
            emojiMap.put("["+name[i]+"]",mImageIds[i]);
        }
    }

    public static int getEmojiByName(String emojiName) {
        Integer integer = emojiMap.get(emojiName);
        return integer == null ? -1 : integer;
    }

}
