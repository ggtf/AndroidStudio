package com.ggtf.specialmusicplayer.functions.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ggtf.specialmusicplayer.R;

import java.util.List;

/**
 * Created by ggtf at 2016/4/16
 * Author:ggtf
 * Time:2016/4/16
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */
public class MenuAdapter extends BaseAdapter {
    private Context context;
    private List<MenuItem> items;
    private LayoutInflater layoutInflater;
    private int typeCount;

    public MenuAdapter(Context context, List<MenuItem> items, int typeCount) {
        this.context = context;
        this.items = items;
        this.typeCount = typeCount > 0 ? typeCount : 1;
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
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return typeCount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        int type = items.get(position).getType();
        switch (type) {
            case MusicMenu.LIST_ITEM_TYPE_0:
                view = getViewByType0(position, convertView, parent);
                break;
            case MusicMenu.LIST_ITEM_TYPE_1:
                view = getViewByType0(position, convertView, parent);
                break;
            case MusicMenu.LIST_ITEM_TYPE_2:
                view = getViewByType0(position, convertView, parent);
                break;
            case MusicMenu.LIST_ITEM_TYPE_3:
                view = getViewByType0(position, convertView, parent);
                break;
        }
        return view;
    }

    private View getViewByType0(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
            view = layoutInflater.inflate(R.layout.menu_item_type1, parent, false);
        }
        ViewHolderType0 holder = (ViewHolderType0) view.getTag();
        if (holder == null) {
            holder = new ViewHolderType0();
            holder.tv = (TextView) view.findViewById(R.id.id_mm_list_item_type1_tv);
            view.setTag(holder);
        }
        MenuItem menuItem = items.get(position);
        String label = menuItem.getLabel();
        holder.tv.setText(label);
        return view;
    }

    private final class ViewHolderType0 {
        private TextView tv;
    }


}
