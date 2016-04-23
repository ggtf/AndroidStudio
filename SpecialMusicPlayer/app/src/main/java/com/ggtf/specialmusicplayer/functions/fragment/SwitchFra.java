package com.ggtf.specialmusicplayer.functions.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.ggtf.specialmusicplayer.functions.actionbar.SwitchFuc;

import java.util.List;

/**
 * Created by ggtf at 2016/4/17
 * Author:ggtf
 * Time:2016/4/17
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 * 切换fragment
 */
public class SwitchFra implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private Context context;
    private FragmentManager manager;
    private AdapterFra adapterFra;
    private SwitchFuc switchFuc;

    public SwitchFra(Context context, FragmentManager manager, ViewPager viewPager, List<Fragment> fragments) {
        this.viewPager = viewPager;
        this.fragments = fragments;
        this.manager = manager;
        initFra();
    }

    private void initFra() {
        adapterFra = new AdapterFra(manager, fragments);
        viewPager.setAdapter(adapterFra);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(1);
    }

    public void boundSwitchFuc(SwitchFuc fuc) {
        switchFuc = fuc;
        switchFuc.boundSwitchFra(this);
    }

    public void setFragmentPos(int pos) {
        viewPager.setCurrentItem(pos, true);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (switchFuc != null)
            switchFuc.setSort(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
