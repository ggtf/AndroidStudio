package com.ggtf.widgettrainning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ggtf at 2015/10/22
 * Author:ggtf
 * Time:2015/10/22
 * Email:15170069952@163.com
 * ProjectName:WidgetTrainning
 */
public class CalendarAdapter extends BaseAdapter {
    private Context context;
    private List<String> days;
    private LayoutInflater layoutInflater;

    public CalendarAdapter(Context context, List<String> days) {
        this.context = context;
        this.days = days;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return days != null ? days.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return days.get(position);
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
            view = layoutInflater.inflate(R.layout.grid_view_item, parent, false);
        }
        if (view instanceof TextView) {
            TextView day = (TextView) view;
            if (position<7){
                day.setBackgroundResource(R.drawable.button_shape_week);
            }
            day.setText(days.get(position));
        }
        return view;
    }
}
