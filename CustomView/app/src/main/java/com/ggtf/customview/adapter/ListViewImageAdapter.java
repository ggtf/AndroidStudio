package com.ggtf.customview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.ggtf.customview.R;

import java.util.List;

/**
 * Created by ggtf at 2015/11/7
 * Author:ggtf
 * Time:2015/11/7
 * Email:15170069952@163.com
 * ProjectName:CustomView
 */
public class ListViewImageAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> imageResId;
    private int height;
    private LayoutInflater layoutInflater;

    public ListViewImageAdapter(Context context, List<Integer> imageResId,int height) {
        this.context = context;
        this.imageResId = imageResId;
        this.height = height;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imageResId != null ? imageResId.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return imageResId.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
            view = layoutInflater.inflate(R.layout.image_layout, parent, false);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null){
            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.image_view);
            view.setTag(holder);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.height = height;
        holder.imageView.setImageResource(imageResId.get(position));
        holder.imageView.setLayoutParams(layoutParams);
        return view;
    }

    private final class ViewHolder{
        public  ImageView imageView;
    }



}
