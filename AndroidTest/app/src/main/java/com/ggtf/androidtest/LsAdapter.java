package com.ggtf.androidtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by ggtf at 2016/1/25
 * Author:ggtf
 * Time:2016/1/25
 * Email:15170069952@163.com
 * ProjectName:AndroidTest
 */
public class LsAdapter extends BaseAdapter {
    private Context context;
    private List<String> items;
    private LayoutInflater layoutInflater;

    public LsAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
        layoutInflater = LayoutInflater.from(context);
        Log.i("Info","getCount() = "+getCount());
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
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
            view = layoutInflater.inflate(R.layout.items, parent, false);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.iv = (ImageView) view.findViewById(R.id.iv);
            view.setTag(holder);
        }
        String url = items.get(position);
        ImageOptions.Builder builder = new ImageOptions.Builder();
        builder.setConfig(Bitmap.Config.RGB_565);
        builder.setFadeIn(true);
        builder.setUseMemCache(true);
        builder.setCrop(true);
        builder.setSize(200, 100);
        ImageOptions imageOptions = builder.build();
        Log.i("Info","position = "+position);
        x.image().bind(holder.iv, url, imageOptions);
//        holder.iv.setScaleType(ImageView.ScaleType.FIT_XY);
        return view;
    }

    private final class ViewHolder {
        public ImageView iv;
    }

}
