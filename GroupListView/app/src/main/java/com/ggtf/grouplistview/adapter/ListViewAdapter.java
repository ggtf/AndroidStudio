package com.ggtf.grouplistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.ggtf.grouplistview.R;
import com.ggtf.grouplistview.custom.CustomListView;

import java.util.List;

/**
 * Created by ggtf at 2015/10/9
 * Author:ggtf
 * Time:2015/10/9
 * Email:15170069952@163.com
 * ProjectName:GroupListView
 */
public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> listViewGroups;
    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;

    public ListViewAdapter(Context context, List<String> listViewGroups) {
        this.context = context;
        this.listViewGroups = listViewGroups;
        inflater = LayoutInflater.from(this.context);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        return listViewGroups!=null?listViewGroups.size():0;
    }

    @Override
    public Object getItem(int position) {
        return listViewGroups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView!=null){
            view = convertView;
        }else {
            view = inflater.inflate(R.layout.list_view_group,parent,false);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null){
            holder =  new ViewHolder();
            holder.groupCategory = (TextView) view.findViewById(R.id.group_category);
            view.setTag(holder);
        }
        holder.groupCategory.setText(listViewGroups.get(position));
        holder.groupCategory.setTag(position);
        holder.groupCategory.setOnClickListener(onClickListener);
        holder.groupCategory.setVisibility(View.VISIBLE);


        return view;
    }
    private static class ViewHolder{
        public TextView groupCategory;
    }
}
