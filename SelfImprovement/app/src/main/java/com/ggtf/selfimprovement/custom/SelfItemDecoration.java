package com.ggtf.selfimprovement.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by ggtf at 2016/3/4
 * Author:ggtf
 * Time:2016/3/4
 * Email:15170069952@163.com
 * ProjectName:SelfImprovement
 */
public class SelfItemDecoration extends RecyclerView.ItemDecoration {

    private final static int[] ATTRS = {android.R.attr.listDivider};
    public final static int HORIZONTAL = 100;
    public final static int VERTICAL = 101;
    private Drawable decoration;
    private Context context;
    private int type;

    public SelfItemDecoration(Context context, int type) {
        this.context = context;
        this.type = type;
        TypedArray array = context.obtainStyledAttributes(ATTRS);
        decoration = array.getDrawable(0);
        array.recycle();
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        switch (type) {
            case VERTICAL:
                initDecorationByVertical(c, parent, state);
                break;
            case HORIZONTAL:
                initDecorationByHorizontal(c, parent, state);
                break;
        }

    }

    /**
     * 绘制垂直方向的分隔线
     *
     * @param c      画布
     * @param parent recyclerView
     * @param state  state对象
     */
    private void initDecorationByVertical(Canvas c, RecyclerView parent, RecyclerView.State state) {
        /*将要绘制的分割线当成一个矩形*/
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int top = childAt.getBottom() + params.bottomMargin;
            int bottom = top + decoration.getIntrinsicHeight();
            decoration.setBounds(left, top, right, bottom);
            decoration.draw(c);
        }
    }

    /**
     * 绘制水平方向的分隔线
     *
     * @param c      画布
     * @param parent recyclerView
     * @param state  state对象
     */
    private void initDecorationByHorizontal(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int left = childAt.getRight() - childAt.getPaddingRight();
            int top = childAt.getPaddingTop() - params.topMargin;
            int right = left + decoration.getIntrinsicHeight();
            int bottom = childAt.getHeight() - childAt.getPaddingBottom() - params.bottomMargin;
            decoration.setBounds(left, top, right, bottom);
            decoration.draw(c);
        }
    }
}
