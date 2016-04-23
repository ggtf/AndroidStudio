package com.ggtf.ttdtmusic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ggtf at 2015/10/26
 * Author:ggtf
 * Time:2015/10/26
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
//    private String[] titles;
    public MainPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
//        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titles[position];
//    }
}
