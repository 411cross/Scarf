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

import com.bumptech.glide.Glide;
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
import com.example.jungle.weixin.PublicUtils.ManagerUtils;
import com.example.jungle.weixin.PublicUtils.ToastUtils;
import com.example.jungle.weixin.R;
import com.example.jungle.weixin.RetrofitUtil.HttpResultSubscriber;
import com.example.jungle.weixin.RetrofitUtil.MyService;
import com.example.jungle.weixin.RetrofitUtil.NetRequestFactory;
import com.example.jungle.weixin.RetrofitUtil.Transform;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.vov.vitamio.utils.Log;
import retrofit2.Response;


public class HomePageFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomePageAdapter adapter;
    LinearLayoutManager layoutManager;
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
        loadStatus(1);
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.weibo_list);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomePageAdapter(getContext(), statusesList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadStatus(currentPage);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch(newState){
                    case 0:
                        try {
                            if(getContext() != null) Glide.with(getContext()).resumeRequests();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                            if(getContext() != null) Glide.with(getContext()).resumeRequests();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            if(getContext() != null) Glide.with(getContext()).pauseRequests();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;

                }
            }
        });

        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            //TODO now visible to user
        } else {
            //TODO now invisible to user
            new Thread(new Runnable() {
                @Override
                public void run() {

                    Glide.get(getContext()).clearMemory();
                }
            }).start();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Glide.get(getContext()).clearMemory();

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
                super._onError(statusList);
                try {
                    String errorString = statusList.errorBody().string();
                    int index = errorString.indexOf("\"error_code\":");
//                    String code = errorString.substring(index + 12, index + 18);
//                    System.out.println("+++++++++++++++++++++++++" + code);
//                    ToastUtils.showShortToast(getContext(), CodeUtils.getChineseMsg(code));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private void setFooterView(RecyclerView recyclerView) {
        View footer = LayoutInflater.from(getContext()).inflate(R.layout.public_loadmore_footer, recyclerView, false);
        adapter.setFooterView(footer);
    }

}
