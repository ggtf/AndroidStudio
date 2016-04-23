package com.ggtf.customflowlayout.customs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggtf at 2015/10/8
 * Author:ggtf
 * Time:2015/10/8
 * Email:15170069952@163.com
 * ProjectName:CustomFlowLayout
 */
public class CustomFlowLayout extends ViewGroup {
    /**
     * 存储所有的子View的集合
     */
    private List<List<View>> mAllChildViews = new LinkedList<>();
    /**
     * 每一行的高度
     */
    private List<Integer> mLineHeight = new LinkedList<>();
    private List<View> lineViews = new LinkedList<>();

    public CustomFlowLayout(Context context) {
        super(context);
    }

    public CustomFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllChildViews.clear();
        mLineHeight.clear();
//        获取当前ViewGroup的宽度
        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;
//        记录当前行的View
        lineViews.clear();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            /**
             * 需要换行
             */
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width) {
//                记录LineHeight
                mLineHeight.add(lineHeight);
//                记录当前行的Views
                mAllChildViews.add(lineViews);
//                重置行的宽高
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
//                重置View的集合
                lineViews = new LinkedList<>();
            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin);
            lineViews.add(child);
        }
//        处理最后一行
        mLineHeight.add(lineHeight);
        mAllChildViews.add(lineViews);

//        设置子View的位置
        int left = 0;
        int top = 0;
//        获取行数
        int lineCount = mAllChildViews.size();
        for (int i = 0; i < lineCount; i++) {
//            当前行的views和高度
            lineViews = mAllChildViews.get(i);
            lineHeight = mLineHeight.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
//                判断是否显示
                if (child.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int cLeft = left + lp.leftMargin;
                int cTop = top + lp.topMargin;
                int cRight = cLeft + child.getMeasuredWidth();
                int cBottom = cTop + child.getMeasuredHeight();

//                进行子View布局
                child.layout(cLeft, cTop, cRight, cBottom);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            left = 0;
            top += lineHeight;
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /**
         * 父控件传进来的宽度/高度以及对应的测量模式
         */
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        /**
         * 如果当前ViewGroup的宽高为wrap_content的情况
         */
//        自己测量的宽高
        int width = 0;
        int height = 0;
//        记录每一行的宽度高度
        int lineWidth = 0;
        int lineHeight = 0;
//        获取子View的个数
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
//            测量子View的宽高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
//            得到LayoutParams(采用的是MarginLayoutParams,因为子控件标签主要是位置的偏移,margin位置)
            MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
            /**
             * 获取子控件占据的宽度和高度
             */
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            /**
             * 换行时候
             */
            if (lineWidth + childWidth > sizeWidth) {
//                对比得到最大宽度
                width = Math.max(width, lineWidth);
//                重置lineWidth
                lineWidth = childWidth;
//                记录行高
                height += lineHeight;
                lineHeight = childHeight;
            } else {
                /**
                 * 不换行的时候
                 */
//                叠加行宽
                lineWidth += childWidth;
//                得到最大行高
                lineHeight = Math.max(lineHeight, childHeight);
            }
//            最后一个子View的情况
            if (i == childCount - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }
        setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width,
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 与当前ViewGroup对应的LayoutParams
     *
     * @param attrs
     * @return
     */
//    @Override
//    public LayoutParams generateLayoutParams(AttributeSet attrs) {
//
//        return new MarginLayoutParams(getContext(), attrs);
//    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }



}
