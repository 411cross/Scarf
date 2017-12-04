package com.example.jungle.weixin.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by jungle on 2017/11/8.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> viewList;
    private List<String> titleList;


    public ViewPagerAdapter(FragmentManager fm, List<Fragment>viewList,List<String> titleList) {
        super(fm);
        this.viewList=viewList;
        this.titleList=titleList;

    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titleList.get(position);
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return viewList.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return viewList.size();
    }
}
