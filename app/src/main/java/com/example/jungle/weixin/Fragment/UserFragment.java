package com.example.jungle.weixin.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jungle.weixin.Adapter.HomePageAdapter;
import com.example.jungle.weixin.Adapter.UserAdapter;
import com.example.jungle.weixin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        List userList = new ArrayList<String>();
        for(int i=0;i<8;i++){
            userList.add("你的名字");
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.userRecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        UserAdapter adapter = new UserAdapter(view.getContext(), userList);
        recyclerView.setAdapter(adapter);
//        return inflater.inflate(R.layout.fragment_home_page, container, false);
        return view;
    }

}
