package com.ggtf.switchtabcustom;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

/**
 * Created by ggtf at 2015/11/9
 * Author:ggtf
 * Time:2015/11/9
 * Email:15170069952@163.com
 * ProjectName:CustomView
 */
public class CommandViewPagerAdapter extends PagerAdapter {
    private List<Integer> imageResId;
    private Context context;

    public CommandViewPagerAdapter(Context context, List<Integer> imageResId) {
        this.context = context;
        this.imageResId = imageResId;
    }

    @Override
    public int getCount() {
        return imageResId!=null?imageResId.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setImageResource(imageResId.get(position));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(layoutParams);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof ImageView){
            ImageView imageView = (ImageView) object;
            container.removeView(imageView);
        }
    }
}
