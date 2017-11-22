package com.example.jungle.weixin.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.jungle.weixin.R;


/**
 * Created by chf on 2017/11/9.
 */

public class ExpressionAdapter extends BaseAdapter{
    private Context mContext;
    private static int[] mImageIds = new int[] {R.drawable.ad_new0902_org,R.drawable.aliwanew_org,
            R.drawable.alizuoguiliannew_org,R.drawable.allstar_dp_org,R.drawable.angrya_org,R.drawable.bba_org,
            R.drawable.bhsj5_nainai_thumb,R.drawable.blankstar_dp_org,R.drawable.bmkeai_org,R.drawable.boaini_org,
            R.drawable.bs2_org,R.drawable.bs_org,R.drawable.buyao_org,R.drawable.byebye,R.drawable.bz_org,
            R.drawable.cakev2_org,R.drawable.camera_org,R.drawable.carnation_org,R.drawable.cheer,R.drawable.cj_org,
            R.drawable.clock_org,R.drawable.come_org,R.drawable.crazya_org,R.drawable.cry,R.drawable.cza_org,
            R.drawable.d_org,R.drawable.dalian_org,R.drawable.dintuizhuang_org,R.drawable.dizzya_org,R.drawable.doge_org,
            R.drawable.dora_kaixin_org,R.drawable.dora_meiwei_org,R.drawable.dora_qinqin_org,R.drawable.dora_wunai_org,
            R.drawable.dora_xiao_org,R.drawable.dorachijing_org,R.drawable.dorahaipa_org,R.drawable.dorahan_org,
            R.drawable.dorahaose_org,R.drawable.earth1r_org,R.drawable.eventtravel_org,R.drawable.fan,R.drawable.flag_org,
            R.drawable.flower_org,R.drawable.football,R.drawable.fuyun_org,R.drawable.gangnamstyle_org,R.drawable.geiliv2_org,
            R.drawable.gm_org,R.drawable.good_org,R.drawable.green_band_org,R.drawable.gza_org,R.drawable.h_org,
            R.drawable.ha_org,R.drawable.halfstar_dp_org,R.drawable.haqianv2_org,R.drawable.hatea_org,R.drawable.hearta_org,
            R.drawable.heia_org,R.drawable.hongbaofei2014_org,R.drawable.hongyun_org,R.drawable.horse2_org,R.drawable.huanglianse_org,
            R.drawable.huangliansj_org,R.drawable.huanglianwx_org,R.drawable.huatongv2_org,R.drawable.hufen_org,R.drawable.j_org,
            R.drawable.jiqimaodaxiong_org,R.drawable.jiqimaojingxiang_org,R.drawable.jiqimaopanghu_org,R.drawable.jiqimaoxiaofu_org,
            R.drawable.jqmbwtxing_org,R.drawable.jqmweixiao_org,R.drawable.kandiev2_org,R.drawable.kanzhangv2_org,R.drawable.kissboy_org,
            R.drawable.kissgirl_org,R.drawable.kl2_org,R.drawable.kl_org,R.drawable.kunv2_org,R.drawable.kxwanpi_org,R.drawable.landeln_org,
            R.drawable.lang_org,R.drawable.laugh,R.drawable.lazhuv2_org,R.drawable.liwu_org,R.drawable.lovea_org,R.drawable.ltqiekenao_org,
            R.drawable.lxhainio_org,R.drawable.lxhdeyidixiao_org,R.drawable.lxhlike_org,R.drawable.lxhqiuguanzhu_org,
            R.drawable.lxhtouxiao_org,R.drawable.lxhwahaha_org,R.drawable.lxhxiudada_org,R.drawable.lxhxuyuan_org,R.drawable.lxhzan_org,
            R.drawable.lxhzhuanfa_org,R.drawable.madaochenggong_org,R.drawable.mango_02_org,R.drawable.mango_03_org,R.drawable.mango_07_org,
            R.drawable.mango_11_org,R.drawable.mango_12_org,R.drawable.mashangyouduixiang_org,R.drawable.mb_org,R.drawable.mm_org,
            R.drawable.moczhuanfa_org,R.drawable.money_org,R.drawable.moon,R.drawable.moren_bbjdnew_org,R.drawable.moren_chiguaqunzhong_org,
            R.drawable.moren_chongjing_org,R.drawable.moren_feijie_org,R.drawable.moren_hashiqi_org,R.drawable.moren_xiaoerbuyu_org,
            R.drawable.moren_yunbei_org,R.drawable.mothersday_org,R.drawable.music_org,R.drawable.numav2_org,R.drawable.o_org,
            R.drawable.ok_org,R.drawable.otm_org,R.drawable.panda_org,R.drawable.pcmoren_baobao_org,R.drawable.pcmoren_cool2017_org,
            R.drawable.pcmoren_guile_org,R.drawable.pcmoren_huaixiao_org,R.drawable.pcmoren_jiayou_org,R.drawable.pcmoren_tanshou_org,
            R.drawable.pcmoren_tian_org,R.drawable.pcmoren_wu_org,R.drawable.pig,R.drawable.ppbguzhang_org,R.drawable.qq_org,
            R.drawable.rabbit_org,R.drawable.rain,R.drawable.sad_org,R.drawable.sada_org,R.drawable.sb_org,R.drawable.sc_org,
            R.drawable.shamea_org,R.drawable.shayan_org,R.drawable.shenshou_org,R.drawable.sk_org,R.drawable.snow_org,R.drawable.soap_org,
            R.drawable.sun,R.drawable.sw_org,R.drawable.sweata_org,R.drawable.t_org,R.drawable.tootha_org,R.drawable.travel_org,
            R.drawable.tza_org,R.drawable.unheart,R.drawable.vw_org,R.drawable.wabi_org,R.drawable.watermelon,R.drawable.weijin_org,
            R.drawable.wg_org,R.drawable.wind_org,R.drawable.wq_org,R.drawable.ws_org,R.drawable.x_org,R.drawable.xi_org,
            R.drawable.xiaohuangren_baiyan_org,R.drawable.xiaohuangren_buxie_org,R.drawable.xiaohuangren_deyi_org,R.drawable.xiaohuangren_gaoxing_org,
            R.drawable.xiaohuangren_huaixiao_org,R.drawable.xiaohuangren_jiandaoshou_org,R.drawable.xiaohuangren_jingya_org,
            R.drawable.xiaohuangren_weiqu_org,R.drawable.xiaohuangren_weixiao_org,R.drawable.xiaohuangren_wunai_org,R.drawable.xiaoku_org,
            R.drawable.xklzhuanquan_org,R.drawable.xman_baofengnv_org,R.drawable.xman_kuaiyin_org,R.drawable.yangniandj_org,R.drawable.ye_org,
            R.drawable.yhh_org,R.drawable.youqian_org,R.drawable.yunying_zylmbianfuxia_org,R.drawable.yunying_zylmganggu_org,
            R.drawable.yunying_zylmhaiwang_org,R.drawable.yunying_zylmlogo_org,R.drawable.yunying_zylmshandianxia_org,R.drawable.yunying_zylmshenqi_org,
            R.drawable.yw_org,R.drawable.yx_org,R.drawable.z2_org,R.drawable.zhaji_org,R.drawable.zhh_org,R.drawable.zuoyi_org,
            R.drawable.zy_org
            };
    public ExpressionAdapter(Context c){
        this.mContext = c;
    }
    public static int[] getmImageIds(){
        return mImageIds;
    }
    public int getCount(){
        return mImageIds.length;
    }
    public long getItemId(int position){
        return mImageIds[position];
    }

    public Object getItem(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setBackgroundColor(Color.TRANSPARENT);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mImageIds[position]);
        imageView.setTag("[" + position + "]");
        return imageView;
    }

}
