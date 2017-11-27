package com.example.jungle.weixin.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jungle.weixin.Adapter.HomePageAdapter;
import com.example.jungle.weixin.Bean.BaseBean.Status;
import com.example.jungle.weixin.Bean.ParticularBean.StatusList;
import com.example.jungle.weixin.Bean.XHRBase.XHRBaseBean;
import com.example.jungle.weixin.Bean.XHRBase.XHRCard;
import com.example.jungle.weixin.Bean.XHRBase.XHRHotStatus;
import com.example.jungle.weixin.PublicUtils.CodeUtils;
import com.example.jungle.weixin.PublicUtils.EndlessRecyclerOnScrollListener;
import com.example.jungle.weixin.PublicUtils.ManagerUtils;
import com.example.jungle.weixin.PublicUtils.ToastUtils;
import com.example.jungle.weixin.R;
import com.example.jungle.weixin.RetrofitUtil.H5Service;
import com.example.jungle.weixin.RetrofitUtil.HttpResultSubscriber;
import com.example.jungle.weixin.RetrofitUtil.MyService;
import com.example.jungle.weixin.RetrofitUtil.NetRequestFactory;
import com.example.jungle.weixin.RetrofitUtil.Transform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

import static io.vov.vitamio.utils.Log.TAG;

public class FindFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomePageAdapter adapter;
    private List<Status> statusesList = new ArrayList<>();
    private int page;

    public FindFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FindFragment newInstance(String param1, String param2) {
        FindFragment fragment = new FindFragment();
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

        NetRequestFactory.getInstance().createService(H5Service.class).getHotStatus("test",page).compose(Transform.<Response<XHRBaseBean<XHRHotStatus>>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<XHRBaseBean<XHRHotStatus>>>() {
            @Override
            public void onSuccess(Response<XHRBaseBean<XHRHotStatus>> statusList) {
                Log.i(TAG, "=============loadStatus: " + statusList.body().getData().getCards().get(0).getMblog().getText());
                for (XHRCard card : statusList.body().getData().getCards()) {
                    statusesList.add(card.getMblog());
                }
                adapter.notifyDataSetChanged();
                if (page == 1) {
                    setFooterView(recyclerView);
                }
            }

            @Override
            public void _onError(Response<XHRBaseBean<XHRHotStatus>> statusList) {
                super._onError(statusList);
                try {
                    ManagerUtils.jumpToAuthorize(getContext());
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
