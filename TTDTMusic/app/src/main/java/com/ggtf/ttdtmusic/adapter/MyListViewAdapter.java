package com.ggtf.ttdtmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ggtf.ttdtmusic.R;
import com.ggtf.ttdtmusic.entities.MyListViewItem;

import java.util.List;

/**
 * Created by ggtf at 2015/10/27
 * Author:ggtf
 * Time:2015/10/27
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class MyListViewAdapter extends BaseAdapter {
    private Context context;
    private List<MyListViewItem> items;
    private LayoutInflater layoutInflater;

    public MyListViewAdapter(Context context, List<MyListViewItem> items) {
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
            view = layoutInflater.inflate(R.layout.my_listview_item, parent, false);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.itemIcon = (ImageView) view.findViewById(R.id.item_icon);
            holder.itemTitle = (TextView) view.findViewById(R.id.item_title);
            holder.itemNum = (TextView) view.findViewById(R.id.item_content);
            view.setTag(holder);
        }
        holder.itemIcon.setImageResource(items.get(position).getIconRes());
        holder.itemTitle.setText(items.get(position).getMenuName());
        holder.itemNum.setText(items.get(position).getMusicNum());

        return view;
    }

    private final class ViewHolder {
        public ImageView itemIcon;
        public TextView itemTitle;
        public TextView itemNum;

    }

}
