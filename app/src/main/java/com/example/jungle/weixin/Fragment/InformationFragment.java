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
import com.example.jungle.weixin.Adapter.InformationAdapter;
import com.example.jungle.weixin.Bean.BaseBean.Comment;
import com.example.jungle.weixin.Bean.ParticularBean.ReadCommentsData;
import com.example.jungle.weixin.PublicUtils.CodeUtils;
import com.example.jungle.weixin.R;
import com.example.jungle.weixin.RetrofitUtil.HttpResultSubscriber;
import com.example.jungle.weixin.RetrofitUtil.MyService;
import com.example.jungle.weixin.RetrofitUtil.NetRequestFactory;
import com.example.jungle.weixin.RetrofitUtil.Transform;

import java.util.Arrays;
import java.util.List;

import retrofit2.Response;


public class InformationFragment extends Fragment {
    private String token;
    private Comment[] comments;
    private List<Comment> commentList;


    private InformationAdapter adapter;
    private View view;
    private RecyclerView recyclerView;


    public InformationFragment() {

    }


    // TODO: Rename and change types and number of parameters
    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
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
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_information, container, false);
        NetRequestFactory.getInstance().createService(MyService.class).commentsToMe(CodeUtils.getmToken()).compose(Transform.<Response<ReadCommentsData>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<ReadCommentsData>>() {
            @Override
            public void onSuccess(Response<ReadCommentsData> ReadCommentsData) {

                comments = ReadCommentsData.body().getComments();
                recyclerView = (RecyclerView) view.findViewById(R.id.weibo_list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                recyclerView.setLayoutManager(layoutManager);
                commentList = Arrays.asList(comments);
                adapter = new InformationAdapter((TotalActivity) getActivity(), commentList);
                recyclerView.setAdapter(adapter);
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
                setHeaderView(recyclerView);
                setFooterView(recyclerView);
            }

            @Override
            public void _onError(Response<ReadCommentsData> ReadCommentsData) {

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




    private void setHeaderView(RecyclerView view) {
        View header = LayoutInflater.from(this.getContext()).inflate(R.layout.recyclerview_header, view, false);
        adapter.setmHeaderView(header);
    }

    private void setFooterView(RecyclerView view) {
        View footer = LayoutInflater.from(this.getContext()).inflate(R.layout.recyclerview_footer, view, false);
        adapter.setmFooterView(footer);
    }


}
