package com.example.jungle.weixin.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jungle.weixin.Activity.TotalActivity;
import com.example.jungle.weixin.Adapter.HomePageAdapter;
import com.example.jungle.weixin.Bean.BaseBean.Status;
import com.example.jungle.weixin.Bean.ParticularBean.ReadCommentsData;
import com.example.jungle.weixin.Bean.ParticularBean.StatusList;
import com.example.jungle.weixin.Bean.ResultBean;
import com.example.jungle.weixin.Bean.Weibo;
import com.example.jungle.weixin.Bean.WeiboImage;
import com.example.jungle.weixin.PublicUtils.CodeUtils;
import com.example.jungle.weixin.PublicUtils.EndlessRecyclerOnScrollListener;
import com.example.jungle.weixin.R;
import com.example.jungle.weixin.RetrofitUtil.HttpResultSubscriber;
import com.example.jungle.weixin.RetrofitUtil.MyService;
import com.example.jungle.weixin.RetrofitUtil.NetRequestFactory;
import com.example.jungle.weixin.RetrofitUtil.Transform;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.vov.vitamio.utils.Log;
import retrofit2.Response;


public class HomePageFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomePageAdapter adapter;
    private List<Status> statusesList = new ArrayList<>();
    private int page;

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
        page = 1;
        loadStatus(page);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.weibo_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomePageAdapter(getContext(), statusesList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadStatus(currentPage);
            }
        });

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

    private void loadStatus(final int page) {
        NetRequestFactory.getInstance().createService(MyService.class).getHomeTimeline(CodeUtils.getmToken(),20,page).compose(Transform.<Response<StatusList>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<StatusList>>() {
            @Override
            public void onSuccess(Response<StatusList> statusList) {
                statusesList.addAll(statusList.body().getStatuses());
                adapter.notifyDataSetChanged();
                if (page == 1) {
                    setFooterView(recyclerView);
                }
            }
            @Override
            public void _onError(Response<StatusList> statusList) {
            }
        });
    }

    private void setFooterView(RecyclerView recyclerView) {
        View footer = LayoutInflater.from(getContext()).inflate(R.layout.public_loadmore_footer, recyclerView, false);
        adapter.setFooterView(footer);
    }

}
