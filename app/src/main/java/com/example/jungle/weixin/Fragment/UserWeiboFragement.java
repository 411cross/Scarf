package com.example.jungle.weixin.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jungle.weixin.Activity.UserDetailActivity;
import com.example.jungle.weixin.Adapter.HomePageAdapter;
import com.example.jungle.weixin.Adapter.UserWeiboAdapter;
import com.example.jungle.weixin.Bean.BaseBean.Status;
import com.example.jungle.weixin.Bean.ParticularBean.StatusList;
import com.example.jungle.weixin.Bean.Weibo;
import com.example.jungle.weixin.Bean.WeiboImage;
import com.example.jungle.weixin.R;
import com.example.jungle.weixin.RetrofitUtil.HttpResultSubscriber;
import com.example.jungle.weixin.RetrofitUtil.MyService;
import com.example.jungle.weixin.RetrofitUtil.NetRequestFactory;
import com.example.jungle.weixin.RetrofitUtil.Transform;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by derrickJ on 2017/11/12.
 */

public class UserWeiboFragement extends Fragment {

    private RecyclerView recyclerView;
    private List<Status> statusesList = new ArrayList<>();
    private String token;

    public UserWeiboFragement() {
        // Required empty public constructor
    }

    public static UserWeiboFragement newInstance(String param1, String param2) {
        UserWeiboFragement fragment = new UserWeiboFragement();
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
        View view = inflater.inflate(R.layout.fragment_user_weibo, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.user_weibo_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        token = "2.007qpDNCCgNPqC8ed90a54ffK4zQ1D";
        NetRequestFactory.getInstance().createService(MyService.class).getUserTimeLine(token).compose(Transform.<Response<StatusList>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<StatusList>>() {
            @Override
            public void onSuccess(Response<StatusList> statusList) {
                statusesList = statusList.body().getStatuses();
                HomePageAdapter adapter = new HomePageAdapter(getContext(), statusesList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void _onError(Response<StatusList> statusList) {

            }

        });

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

}