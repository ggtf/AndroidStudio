package com.ggtf.specialmusicplayer.functions.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ggtf at 2016/4/17
 * Author:ggtf
 * Time:2016/4/17
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */
public class AdapterFra extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public AdapterFra(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }
}
