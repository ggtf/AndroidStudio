package com.ggtf.customflowlayout.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ggtf.customflowlayout.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggtf at 2015/10/8
 * Author:ggtf
 * Time:2015/10/8
 * Email:15170069952@163.com
 * ProjectName:CustomFlowLayout
 */
public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {

    private Context context;
    private List<String> lists;
    private LayoutInflater inflater;
    private List<Integer> mHeight;

    public StaggeredAdapter(Context context, List<String> lists) {
        this.context = context;
        this.lists = lists;
        inflater = LayoutInflater.from(context);
        mHeight = new LinkedList<>();
        for (int i = 0; i < lists.size(); i++) {
            mHeight.add((int) (100+Math.random()*300));
        }
    }

    @Override
    public StaggeredAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int pos) {

        View view = inflater.inflate(R.layout.recycle_view_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return lists != null ? lists.size() : 0;
    }

    @Override
    public void onBindViewHolder(StaggeredAdapter.MyViewHolder myViewHolder, int pos) {
        ViewGroup.LayoutParams layoutParams = myViewHolder.itemView.getLayoutParams();
        layoutParams.height = mHeight.get(pos);
        myViewHolder.itemView.setLayoutParams(layoutParams);
        myViewHolder.hotTag.setText(lists.get(pos));

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView hotTag;

        public MyViewHolder(View itemView) {
            super(itemView);
            hotTag = (TextView) itemView.findViewById(R.id.hot_tag);
        }
    }

    }
