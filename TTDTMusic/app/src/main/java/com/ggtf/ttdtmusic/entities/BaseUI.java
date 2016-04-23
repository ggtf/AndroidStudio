package com.ggtf.ttdtmusic.entities;

import android.view.View;
import android.widget.AdapterView;

import java.lang.ref.WeakReference;

/**
 * Created by ggtf at 2015/10/27
 * Author:ggtf
 * Time:2015/10/27
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class BaseUI{

    public View viewGroup;
    public View.OnClickListener onClickListener;

    /**
     * UI界面设置视图和支持点击事件OnClick的控件
     *
     * @param viewGroup
     * @param onClickListener
     */
    public BaseUI(View viewGroup, View.OnClickListener onClickListener) {
        this.viewGroup = viewGroup;
        this.onClickListener = onClickListener;
    }
}
