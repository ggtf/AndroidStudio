package com.ggtf.mytime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ggtf.mytime.R;
import com.ggtf.mytime.entities.Reminds;

import java.util.List;

/**
 * Created by ggtf at 2015/10/29
 * Author:ggtf
 * Time:2015/10/29
 * Email:15170069952@163.com
 * ProjectName:MyTime
 */
public class TimeAdapter extends BaseAdapter {
    private Context context;
    private List<Reminds> items;
    private LayoutInflater layoutInflater;

    public TimeAdapter(Context context, List<Reminds> items) {
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
            view = layoutInflater.inflate(R.layout.time_item, parent, false);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.days= (TextView) view.findViewById(R.id.days);
            holder.things = (TextView) view.findViewById(R.id.things);
            holder.previousBg = (TextView) view.findViewById(R.id.background);
            view.setTag(holder);
        }
        holder.days.setText(items.get(position).getDay());
        holder.days.setTextColor(items.get(position).getDayTextColor());
        holder.days.setBackgroundColor(items.get(position).getDayBg());
        holder.things.setText(items.get(position).getThing());
        holder.things.setTextColor(items.get(position).getThingTextColor());
        holder.things.setBackgroundColor(items.get(position).getThingBg());

        final FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        layoutParams.width= (int) (200*Math.random()+500);
//        holder.previousBg.setLayoutParams(layoutParams);
        holder.previousBg.setBackgroundColor(items.get(position).getPreviousBg());
        final ViewHolder finalHolder = holder;
        holder.previousBg.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) finalHolder.days.getLayoutParams();
//                params.width = params.width+finalHolder.days.getCompoundPaddingLeft()+finalHolder.days.getPaddingRight();

                int translationX = (int) (finalHolder.days.getWidth()+150+Math.random()*150);
                params.width = translationX;
                layoutParams.width=translationX;
                finalHolder.days.setLayoutParams(params);
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.translate);
                animation.setInterpolator(new BounceInterpolator());
//                animation.setInterpolator(new OvershootInterpolator());
//                TranslateAnimation translateAnimation = new TranslateAnimation(-translationX/2, 0, 0, 0);
//                translateAnimation.setDuration(1500);
//                translateAnimation.setInterpolator(new BounceInterpolator());
//                finalHolder.previousBg.startAnimation(translateAnimation);
                finalHolder.previousBg.startAnimation(animation);
                finalHolder.previousBg.setLayoutParams(layoutParams);
            }
        });


//        TranslateAnimation translateAnimation = new TranslateAnimation(-holder.previousBg.getWidth(),0,0,0);

        return view;
    }

    private class ViewHolder {
        public TextView days;
        public TextView things;
        public TextView previousBg;
    }

}
