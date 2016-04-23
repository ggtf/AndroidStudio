package com.ggtf.calendarview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
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
    private View.OnClickListener onClickListener;
    private GridView.LayoutParams layoutParams;
    private int itemWidth;
    private int today;

    public CalendarAdapter(Context context, List<String> days, View.OnClickListener onClickListener,int itemWidth,int today) {
        this.context = context;
        this.days = days;
        this.onClickListener = onClickListener;
        this.itemWidth = itemWidth;
        this.today = today;
        layoutParams = new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
            view = layoutInflater.inflate(R.layout.calendar_day, parent, false);
        }
        /**
         * 设置布局属性
         */
        layoutParams.width = itemWidth;
        layoutParams.height = layoutParams.width;
        view.setLayoutParams(layoutParams);
//        ViewHolder holder = (ViewHolder) view.getTag();
//        if (holder == null) {
//            holder = new ViewHolder();
//            view.setTag(holder);
//        }
        if (view instanceof Button) {
            Button day = (Button) view;
            if (position<7){
                day.setBackgroundResource(R.drawable.button_shape_week);
            }else {
//                day.setBackgroundResource(R.drawable.button_selector);
                day.setBackgroundResource(R.drawable.button_shape_normal);
            }
            if (position == today){
                day.setBackgroundResource(R.drawable.button_shape_selected);
                day.setTextColor(Color.WHITE);
            }
            day.setText(days.get(position));
            day.setTag(position);
            day.setOnClickListener(onClickListener);
        }
        return view;
    }

//    private final class ViewHolder {
//
//    }

}
