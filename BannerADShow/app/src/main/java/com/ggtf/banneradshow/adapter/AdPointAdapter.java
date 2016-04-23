package com.ggtf.banneradshow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ggtf.banneradshow.R;

import java.util.List;

/**
 * Created by ggtf at 2015/10/10
 * Author:ggtf
 * Time:2015/10/10
 * Email:15170069952@163.com
 * ProjectName:BannerADShow
 */
public class AdPointAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> adPosition;
    private LayoutInflater layoutInflater;

    public AdPointAdapter(Context context, List<Integer> adPosition) {
        this.context = context;
        this.adPosition = adPosition;
        layoutInflater =  LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return adPosition!=null?adPosition.size():0;
    }

    @Override
    public Object getItem(int position) {
        return adPosition.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView != null){
            view = convertView;
        }else {
            view = layoutInflater.inflate(R.layout.ad_point,parent,false);
        } 
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder==null){
            holder = new ViewHolder();
            holder.adPoint = (ImageView) view.findViewById(R.id.ad_point_position);
            view.setTag(holder);
        }
        
        return view;
    }
    private final class ViewHolder{
        public ImageView adPoint;
    }
    
}
