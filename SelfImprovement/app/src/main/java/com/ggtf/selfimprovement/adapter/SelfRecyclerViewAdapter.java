package com.ggtf.selfimprovement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ggtf.selfimprovement.R;
import com.ggtf.selfimprovement.models.TextShow;

import java.util.List;

/**
 * Created by ggtf at 2016/3/4
 * Author:ggtf
 * Time:2016/3/4
 * Email:15170069952@163.com
 * ProjectName:SelfImprovement
 */
public class SelfRecyclerViewAdapter extends RecyclerView.Adapter<SelfRecyclerViewAdapter.MyViewHolder> {
    private List<TextShow> data;
    private Context context;

    public SelfRecyclerViewAdapter(Context context, List<TextShow> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {

        TextShow textShow = data.get(i);
        String value = textShow.getTvShow();
        myViewHolder.tv.setText(value);
        myViewHolder.tv.setTag(textShow);


    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public void removeItem(int removePosition) {
        if (removePosition < data.size()) {
            data.remove(removePosition);
            notifyItemRemoved(removePosition);
        }
    }

    public void addItem(int addPosition, TextShow tv) {
        if (addPosition >= 0 && addPosition <= data.size()) {
            data.add(addPosition, tv);
            notifyItemInserted(addPosition);
        }
    }

    /**
     * ViewHolder对象
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.recycler_view_item);
        }
    }
}

