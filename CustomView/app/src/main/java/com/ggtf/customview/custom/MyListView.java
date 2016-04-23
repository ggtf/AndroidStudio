package com.ggtf.customview.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by ggtf at 2015/10/16
 * Author:ggtf
 * Time:2015/10/16
 * Email:15170069952@163.com
 * ProjectName:CustomView
 */
public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
