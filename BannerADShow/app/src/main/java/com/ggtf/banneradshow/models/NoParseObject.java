package com.ggtf.banneradshow.models;

import android.support.v4.view.ViewPager;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by ggtf at 2015/10/10
 * Author:ggtf
 * Time:2015/10/10
 * Email:15170069952@163.com
 * ProjectName:BannerADShow
 */
public class NoParseObject {
    private GridView gridView;
    private ViewPager viewPager;

    public NoParseObject(GridView gridView, ViewPager viewPager) {
        this.gridView = gridView;
        this.viewPager = viewPager;
    }

    public GridView getGridView() {
        return gridView;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }
}
