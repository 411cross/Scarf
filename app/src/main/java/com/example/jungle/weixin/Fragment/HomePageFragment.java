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
import com.example.jungle.weixin.Bean.ParticularBean.StatusList;
import com.example.jungle.weixin.Bean.ResultBean;
import com.example.jungle.weixin.Bean.Weibo;
import com.example.jungle.weixin.Bean.WeiboImage;
import com.example.jungle.weixin.PublicUtils.CodeUtils;
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

    private List<Status> statusesList = new ArrayList<>();
    private String token;

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
        token = "2.007qpDNCCgNPqC8ed90a54ffK4zQ1D";
        NetRequestFactory.getInstance().createService(MyService.class).getHomeTimeline(token).compose(Transform.<Response<ResultBean<StatusList>>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<ResultBean<StatusList>>>() {
            @Override
            public void onSuccess(Response<ResultBean<StatusList>> statusList) {
                statusesList = statusList.body().data.getStatuses();
            }

            @Override
            public void _onError(Response<ResultBean<StatusList>> e) {
                String code = e.body().error_code;
                Toast.makeText(getContext(),CodeUtils.getChineseMsg(code),Toast.LENGTH_LONG).show();
            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.weibo_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
//        HomePageAdapter adapter = new HomePageAdapter((TotalActivity) getActivity(), weiboList);
        HomePageAdapter adapter = new HomePageAdapter((TotalActivity) getActivity(), statusesList);
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

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            NetRequestFactory.getInstance().createService(MyService.class).getHomeTimeline("2.008CRz6CCgNPqC0042daeb2eiqqbNC").compose(Transform.<ResultBean<StatusList>>defaultSchedulers()).subscribe(new HttpResultSubscriber<StatusList>() {
//                @Override
//                public void onSuccess(StatusList statusList) {
//                    Log.i("statusList😊😊😊", statusList);
////                    statusesList = statusList.getStatuses();
//                }
//
//                @Override
//                public void _onError(ResultBean<StatusList> e) {
//                    String code = e.error_code;
//                    Toast.makeText(getContext(),CodeUtils.getChineseMsg(code),Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//    }
//
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden) {
//            NetRequestFactory.getInstance().createService(MyService.class).getHomeTimeline("2.008CRz6CCgNPqC0042daeb2eiqqbNC").compose(Transform.<ResultBean<StatusList>>defaultSchedulers()).subscribe(new HttpResultSubscriber<StatusList>() {
//                @Override
//                public void onSuccess(StatusList statusList) {
//                    Log.i("statusList😊😊😊", statusList);
////                    statusesList = statusList.getStatuses();
//                }
//
//                @Override
//                public void _onError(ResultBean<StatusList> e) {
//                    String code = e.error_code;
//                    Toast.makeText(getContext(),CodeUtils.getChineseMsg(code),Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//    }
}
