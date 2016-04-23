package com.ggtf.smartscale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ggtf at 2016/1/9
 * Author:ggtf
 * Time:2016/1/9
 * Email:15170069952@163.com
 * ProjectName:CustomView
 */
public class ListAdapter extends BaseAdapter {
    private Context context;
    private List<String> items;
    private LayoutInflater layoutInflater;

    public ListAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
        layoutInflater = LayoutInflater.from(context);
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
            view = layoutInflater.inflate(R.layout.item, parent, false);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.tvShow = (TextView) view.findViewById(R.id.item_name);
            view.setTag(holder);
        }

        String show = items.get(position);
        holder.tvShow.setText(show);
        return view;
    }

    private final class ViewHolder {
        public TextView tvShow;
    }


}
