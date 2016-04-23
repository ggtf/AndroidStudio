package com.ggtf.horizontalscrollviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by ggtf at 2015/10/30
 * Author:ggtf
 * Time:2015/10/30
 * Email:15170069952@163.com
 * ProjectName:HorizontalScrollViewDemo
 */
public class HorizontalScrollViewCustom extends HorizontalScrollView {



    public HorizontalScrollViewCustom(Context context) {
        this(context,null);
    }
    public HorizontalScrollViewCustom(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalScrollViewCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 初始化数据
         */

        initSubView(context);
    }

    private void initSubView(Context context) {
        final LinearLayout child = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.child_layout,null);
        final View leftMenu = LayoutInflater.from(context).inflate(R.layout.menu_left,null);
        final View content = LayoutInflater.from(context).inflate(R.layout.content_layout,null);
        this.addView(child);
        child.addView(leftMenu);
        child.addView(content);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        this.post(new Runnable() {
            @Override
            public void run() {
                layoutParams.width = getWidth();
                leftMenu.setLayoutParams(layoutParams);
                content.setLayoutParams(layoutParams);
                Log.i("Info","width = "+getWidth());

            }
        });


        /**
         * 测量子控件宽高
         */
//        measureView(leftMenu);
//        measureView(content);
////        layoutParams.width = child.getWidth();
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        layoutParams.width = 500;
//        Log.i("Info", "leftMenu width = " + layoutParams.width);
//        leftMenu.setLayoutParams(layoutParams);
//
////        layoutParams.width = child.getWidth();
//        layoutParams.width = 500;
//        Log.i("Info", "content width = " + layoutParams.width);
//        content.setLayoutParams(layoutParams);
//
//
//        this.addView(child);
//
//        /**
//         * 将视图切换到第二个视图上
//         */
//        this.scrollTo(leftMenu.getWidth(),0);

    }

    /**
     * 通知父布局  占用的宽和高
     *
     * @param view
     */
    private void measureView(View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            // 三个参数  左右边距  内边距  宽度
            int width = ViewGroup.getChildMeasureSpec(0, 0, lp.width);
            int height;
            int tempHeight = lp.height;
            //高度不为空 填充布局
            if (tempHeight > 0) {
                height = MeasureSpec.makeMeasureSpec(tempHeight, MeasureSpec.EXACTLY);
            } else {
                height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            }
            view.measure(width, height);
        }

    }
}
