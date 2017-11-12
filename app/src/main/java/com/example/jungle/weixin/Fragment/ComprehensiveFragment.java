package com.example.jungle.weixin.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jungle.weixin.Adapter.HomePageAdapter;
import com.example.jungle.weixin.Adapter.WeiboAdapter;
import com.example.jungle.weixin.Bean.Weibo;
import com.example.jungle.weixin.Bean.WeiboImage;
import com.example.jungle.weixin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComprehensiveFragment extends Fragment {

    WeiboAdapter adapter;
    private List<Weibo> weiboList = new ArrayList<>();

    public ComprehensiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comprehensive, container, false);
        initWeibos();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.comprehensiveRecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new WeiboAdapter(view.getContext(), weiboList);
        recyclerView.setAdapter(adapter);
        setHeaderView(recyclerView);
        setFooterView(recyclerView);
//        return inflater.inflate(R.layout.fragment_home_page, container, false);
        return view;
    }

    private void initWeibos() {
        int avatar = R.mipmap.ic_launcher;
        String identity = "认证";
        String nickname = "我是BinBo";
        String date = "今天";
        String time = "10:00";
        String source = "BinBo客户端";
        String body = "测试一下";
        int image = R.mipmap.ic_launcher;
        String url1 = "http://i-7.vcimg.com/trim/5c94aab049b57ca9e309dc47b38da12a219904/trim.jpg";
        String url2 = "http://ym.zdmimg.com/201412/24/549a276c2cad2.jpg_e600.jpg";
        String url3 = "http://imgs.inkfrog.com/pix/ld654176499/TED01.jpg";
        String url4 = "http://ww1.sinaimg.cn/bmiddle/75e15551gw1dui7vay582j.jpg";
        WeiboImage image1 = new WeiboImage();
        WeiboImage image2 = new WeiboImage();
        WeiboImage image3 = new WeiboImage();
        WeiboImage image4 = new WeiboImage();
        image1.setUrl(url1);
        image2.setUrl(url2);
        image3.setUrl(url3);
        image4.setUrl(url4);
        List<WeiboImage> list1 = new ArrayList<>();
        List<WeiboImage> list = new ArrayList<>();
        list.add(image1);
        list.add(image2);
        list.add(image3);
        list.add(image4);
        list.add(image1);
        list.add(image2);
        list.add(image3);
        list1.add(image1);
        for (int i = 0; i < 8; i++) {
            Weibo weibo = new Weibo(avatar, identity, nickname, date, time, source, body, image, list, 0);
            weiboList.add(weibo);
            Weibo second = new Weibo(avatar, identity, nickname, date, time, source, body, image, list, 1);
            weiboList.add(second);
            Weibo third = new Weibo(avatar, identity, nickname, date, time, source, body, image, list1, 3);
            weiboList.add(third);
            Weibo forth = new Weibo(avatar, identity, nickname, date, time, source, body, image, list, 3);
            weiboList.add(forth);
        }
    }

    private void setHeaderView(RecyclerView view) {
        View header = LayoutInflater.from(this.getContext()).inflate(R.layout.search_result_header, view, false);
        adapter.setmHeaderView(header);
    }

    private void setFooterView(RecyclerView view) {
        View footer = LayoutInflater.from(this.getContext()).inflate(R.layout.recyclerview_footer, view, false);
        adapter.setmFooterView(footer);
    }


}
