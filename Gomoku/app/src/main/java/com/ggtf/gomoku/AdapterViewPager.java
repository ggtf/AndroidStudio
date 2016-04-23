package com.ggtf.gomoku;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by ggtf at 2015/11/6
 * Author:ggtf
 * Time:2015/11/6
 * Email:15170069952@163.com
 * ProjectName:Gomoku
 */
public class AdapterViewPager extends PagerAdapter {

    private List<Integer> imageResId;
    private String[] titles;
    private Context context;

    public AdapterViewPager(Context context,List<Integer> imageResId,String[] titles) {
        this.imageResId = imageResId;
        this.context = context;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return imageResId!=null?imageResId.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object==view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof ImageView){
            ImageView imageView = (ImageView) object;
            container.removeView(imageView);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(imageResId.get(position));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(layoutParams);
        ScaleAnimation animation = new ScaleAnimation(0.5f, 1, 0.5f, 1);
        animation.setDuration(1500);
        imageView.startAnimation(animation);
        container.addView(imageView);
        return imageView;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
