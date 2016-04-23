package com.ggtf.customview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.ggtf.customview.CustomCounterView;
import com.ggtf.customview.R;

import java.util.List;

/**
 * Created by ggtf at 2015/10/16
 * Author:ggtf
 * Time:2015/10/16
 * Email:15170069952@163.com
 * ProjectName:CustomView
 */
public class IndexAdapter extends BaseAdapter {
    private Context context;
    private List<String> words;
    private LayoutInflater layoutInflater;
    private View.OnClickListener onClickLister;
    private View.OnTouchListener onTouchListener;
    private int allHeight;

    public IndexAdapter(Context context, List<String> words,View.OnClickListener onClickLister,View.OnTouchListener onTouchListener,int allHeight) {
        this.context = context;
        this.words = words;
        this.onClickLister = onClickLister;
        this.onTouchListener = onTouchListener;
        this.allHeight = allHeight;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return words != null ? words.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return words.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomCounterView view = null;
        if (convertView != null) {
            view = (CustomCounterView) convertView;
        } else {
            view = (CustomCounterView) layoutInflater.inflate(R.layout.index, parent, false);
        }
        GridView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.height=allHeight/words.size();
        layoutParams.width=allHeight/words.size();
        view.setLayoutParams(layoutParams);
        view.setText(words.get(position));
        view.setTag(position);
        view.setOnClickListener(onClickLister);
        view.setOnTouchListener(onTouchListener);
        return view;
    }



}
