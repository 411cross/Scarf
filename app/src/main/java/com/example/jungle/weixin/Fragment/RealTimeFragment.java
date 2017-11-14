package com.example.jungle.weixin.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jungle.weixin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RealTimeFragment extends Fragment {


    public RealTimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_real_time, container, false);
    }

}
