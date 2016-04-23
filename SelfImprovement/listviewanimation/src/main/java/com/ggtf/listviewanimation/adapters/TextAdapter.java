package com.ggtf.listviewanimation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ggtf.listviewanimation.R;
import com.ggtf.listviewanimation.models.TextShow;

import java.util.List;

/**
 * Created by ggtf at 2016/3/5
 * Author:ggtf
 * Time:2016/3/5
 * Email:15170069952@163.com
 * ProjectName:SelfImprovement
 */
public class TextAdapter extends BaseAdapter {
    private List<TextShow> items;
    private LayoutInflater layoutInflater;
    private View.OnClickListener clickListener;

    public TextAdapter(Context context, List<TextShow> items, View.OnClickListener clickListener) {
        this.items = items;
        this.clickListener = clickListener;
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
            view = layoutInflater.inflate(R.layout.list_item, parent, false);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.listItem = (TextView) view.findViewById(R.id.list_item);
            holder.listItem.setOnClickListener(clickListener);
            view.setTag(holder);
        }
        holder.listItem.setTag(position);
        TextShow textShow = items.get(position);
        String value = textShow.getShowText();
        holder.listItem.setText(value);
        return view;
    }

    public void remove(int position) {
        items.remove(position);
        this.notifyDataSetChanged();
    }

    public void add(int position, TextShow textShow) {
        items.add(position, textShow);
        this.notifyDataSetChanged();
    }

    private final class ViewHolder {
        public TextView listItem;
    }

}

