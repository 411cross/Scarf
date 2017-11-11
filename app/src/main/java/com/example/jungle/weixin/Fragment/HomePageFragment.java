package com.example.jungle.weixin.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jungle.weixin.Activity.TotalActivity;
import com.example.jungle.weixin.Adapter.HomePageAdapter;
import com.example.jungle.weixin.Adapter.WeiboAdapter;
import com.example.jungle.weixin.Bean.Weibo;
import com.example.jungle.weixin.Bean.WeiboImage;
import com.example.jungle.weixin.R;

import java.util.ArrayList;
import java.util.List;


public class HomePageFragment extends Fragment {

    private List<Weibo> weiboList = new ArrayList<>();

    // TODO: Rename and change types of parameters


    public HomePageFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        initWeibos();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.weibo_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        HomePageAdapter adapter = new HomePageAdapter((TotalActivity) getActivity(), weiboList);
        recyclerView.setAdapter(adapter);
//        return inflater.inflate(R.layout.fragment_home_page, container, false);
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void initWeibos() {
        int avatar = R.mipmap.ic_launcher;
        String identity = "认证";
        String nickname = "我是BinBo";
        String date1 = "Tue May 31 17:46:55 +0800 2011";
        String date2 = "Tue Oct 31 07:07:07 +0800 2017";
        String date3 = "Thu Nov 9 17:46:55 +0800 2011";
        String date4 = "Fri Nov 10 17:16:55 +0800 2017";
        String source = "BinBo客户端";
        String body = "@Scarf [害羞] [傻笑] [惊恐] #Scarf客户端# [冷笑] [吐舌] [不适配的表情]";
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
            Weibo weibo = new Weibo(avatar, identity, nickname, date1, source, body, image, list, 0);
            weiboList.add(weibo);
            Weibo second = new Weibo(avatar, identity, nickname, date2, source, body, image, list, 1);
            weiboList.add(second);
            Weibo third = new Weibo(avatar, identity, nickname, date3, source, body, image, list1, 3);
            weiboList.add(third);
            Weibo forth = new Weibo(avatar, identity, nickname, date4, source, body, image, list, 3);
            weiboList.add(forth);
        }
    }

}
