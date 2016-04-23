package com.ggtf.horizontalscrollviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by ggtf at 2015/10/31
 * Author:ggtf
 * Time:2015/10/31
 * Email:15170069952@163.com
 * ProjectName:HorizontalScrollViewDemo
 */
public class HorizontalScrollViewMenu extends HorizontalScrollView {

    private LinearLayout mWapper;
    private ViewGroup menu;
    private ViewGroup content;
    private int screenWidth;
    private int menuPadding = 100;
    private boolean once = false;
    private int menuWidth;


    public HorizontalScrollViewMenu(Context context) {
        this(context, null);
    }

    public HorizontalScrollViewMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalScrollViewMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 初始化数据;
         */
//        获取屏幕的宽度;
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;
        /**
         * dp转换成px值--->50dp---对应的像素值
         */
        menuPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, context.getResources().getDisplayMetrics());

    }

    /**
     * 设置子View的宽和高,设置自己的宽和高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!once) {

            mWapper = (LinearLayout) getChildAt(0);
            menu = (ViewGroup) mWapper.getChildAt(0);
            content = (ViewGroup) mWapper.getChildAt(1);

            menu.getLayoutParams().width = screenWidth - menuPadding;
            menuWidth = menu.getLayoutParams().width;
            content.getLayoutParams().width = screenWidth;
            once = true;
        }


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 通过设置偏移量,将menu隐藏
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(menuWidth, 0);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                /**
                 * 监听滑动事件,来判断菜单是否应该显示,
                 * getScrollX()隐藏在左边的宽度
                 */
                if (this.getScrollX() >= menuWidth / 2) {
                    this.smoothScrollTo(menuWidth,0);
                }else {
                    this.smoothScrollTo(0,0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }
}
