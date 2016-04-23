package com.ggtf.banneradshow.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by ggtf at 2015/10/9
 * Author:ggtf
 * Time:2015/10/9
 * Email:15170069952@163.com
 * ProjectName:BannerADShow
 */
public class CommonViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<Integer> imageResID;

    public CommonViewPagerAdapter(Context context, List<Integer> imageResID) {
        this.context = context;
        this.imageResID = imageResID;
    }

    @Override
    public int getCount() {
        return imageResID != null ? imageResID.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;

            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imageResID.get(position));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(imageView);
            view = imageView;

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View){
            View view = (View) object;
            container.removeView(view);
        }
    }
}
