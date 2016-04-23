package com.ggtf.specialmusicplayer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ggtf.specialmusicplayer.R;
import com.ggtf.specialmusicplayer.models.describe.SingerInfo;
import com.ggtf.specialmusicplayer.models.parse.Singer;
import com.ggtf.specialmusicplayer.network.HttpUtils;

import java.util.List;

/**
 * Created by ggtf at 2016/4/23
 * Author:ggtf
 * Time:2016/4/23
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */
public class SingerSetAdapter extends BaseAdapter {
    private Context context;
    private List<SingerInfo> items;
    private LayoutInflater layoutInflater;
    private int typeCount;

    public SingerSetAdapter(Context context, List<SingerInfo> items, int typeCount) {
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
        SingerInfo singerInfo = items.get(position);
        int type = singerInfo.getType();
        switch (type) {
            case 1:
                view = getViewByType1(position, convertView, parent);
                break;
        }

        return view;
    }

    @NonNull
    private View getViewByType1(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
            view = layoutInflater.inflate(R.layout.grid_view_type_layout1, parent, false);
        }
        ViewHolder1 holder = (ViewHolder1) view.getTag();
        if (holder == null) {
            holder = new ViewHolder1();
            holder.tvName = (TextView) view.findViewById(R.id.id_singer_name);
            holder.ivHeader = (ImageView) view.findViewById(R.id.id_singer_header);
            view.setTag(holder);
        }

        SingerInfo singerInfo = items.get(position);
        String headerUrl = singerInfo.getHeaderUrl();
        Singer singer = singerInfo.getSinger();
        String name = singer.getName();
        holder.tvName.setText(name + "");
//        TODO 获取图片前，先展示默认的歌手圆形头像图片
        holder.ivHeader.setImageResource(R.mipmap.ic_launcher);
        HttpUtils.setImage(context, headerUrl, holder.ivHeader);
        return view;
    }

    private final class ViewHolder1 {
        public TextView tvName;
        public ImageView ivHeader;
    }

}
