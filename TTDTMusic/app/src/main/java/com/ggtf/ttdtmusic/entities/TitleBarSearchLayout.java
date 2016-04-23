package com.ggtf.ttdtmusic.entities;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ggtf.ttdtmusic.R;

/**
 * Created by ggtf at 2015/10/27
 * Author:ggtf
 * Time:2015/10/27
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class TitleBarSearchLayout extends BaseUI {
    private ImageView back;
    private EditText edit;
    private ImageView search;
    /**
     * UI界面设置视图和支持点击事件OnClick的控件
     *
     * @param viewGroup
     * @param onClickListener
     */
    public TitleBarSearchLayout(View viewGroup, View.OnClickListener onClickListener) {
        super(viewGroup, onClickListener);
        init();
    }

    private void init() {
        back = (ImageView) viewGroup.findViewById(R.id.search_back);
        edit = (EditText) viewGroup.findViewById(R.id.search_edit);
        search = (ImageView) viewGroup.findViewById(R.id.search_in);

        back.setClickable(true);
        search.setClickable(true);
        back.setOnClickListener(onClickListener);
        search.setOnClickListener(onClickListener);
    }
}
