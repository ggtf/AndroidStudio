package com.ggtf.ttdtmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ggtf.ttdtmusic.R;

import java.util.List;

/**
 * Created by ggtf at 2015/10/26
 * Author:ggtf
 * Time:2015/10/26
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class SideMenuAdapter extends BaseAdapter {
        private Context context;
            private List<Integer> items;
            private LayoutInflater layoutInflater;

            public SideMenuAdapter(Context context, List<Integer> items) {
                this.context = context;
                this.items = items;
                layoutInflater = LayoutInflater.from(context);
            }

            @Override
            public int getCount() {
                return items!=null?items.size():0;
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
                if(convertView!=null){
                    view = convertView;
                }else{
                    view = layoutInflater.inflate(R.layout.side_menu_item,parent,false);
                }
                ViewHolder holder = (ViewHolder)view.getTag();
                if(holder == null){
                    holder = new ViewHolder();
                    holder.itemImage = (ImageView) view.findViewById(R.id.item_image);
                    view.setTag(holder);
                }
                holder.itemImage.setImageResource(items.get(position));

                return view;
            }
            private final class ViewHolder{
                public ImageView itemImage;
            }

}
