package com.ggtf.grouplistview.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by ggtf at 2015/10/9
 * Author:ggtf
 * Time:2015/10/9
 * Email:15170069952@163.com
 * ProjectName:GroupListView
 */
public class CustomListView extends ListView {
    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
