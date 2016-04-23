package com.ggtf.gridviewtraining;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

/**
 * Created by ggtf at 2015/10/18
 * Author:ggtf
 * Time:2015/10/18
 * Email:15170069952@163.com
 * ProjectName:GridViewTraining
 */
public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> arrs;
    private LayoutInflater layoutInflater;

    public GridViewAdapter(Context context, List<String> arrs) {
        this.context = context;
        this.arrs = arrs;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrs != null ? arrs.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return arrs.get(position);
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
            view = layoutInflater.inflate(R.layout.grid_view_child, parent, false);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            view.setTag(holder);
        }
        if (view instanceof Button){
            Button number = (Button) view;
            number.setTag(R.id.grid_view,position);
            number.setText(arrs.get(position));
        }
        return view;
    }

    private final class ViewHolder {

    }

}
