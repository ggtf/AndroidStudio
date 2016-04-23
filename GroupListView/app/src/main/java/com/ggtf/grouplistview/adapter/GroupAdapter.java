package com.ggtf.grouplistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ggtf.grouplistview.R;
import com.ggtf.grouplistview.models.GroupsInListView;

import java.util.List;

/**
 * Created by ggtf at 2015/10/9
 * Author:ggtf
 * Time:2015/10/9
 * Email:15170069952@163.com
 * ProjectName:GroupListView
 */
public class GroupAdapter extends BaseAdapter {

    private Context context;
    private List<GroupsInListView> groupsInListViewList;
    private LayoutInflater layoutInflater;
    private View.OnClickListener onClickListener;

    public GroupAdapter(Context context, List<GroupsInListView> groupsInListViewList) {
        this.context = context;
        this.groupsInListViewList = groupsInListViewList;
        layoutInflater = LayoutInflater.from(this.context);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        return groupsInListViewList!=null?groupsInListViewList.size():0;
    }

    @Override
    public Object getItem(int position) {
        return groupsInListViewList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return groupsInListViewList.get(position).getViewType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView!=null){
            view = convertView;
        }else {
            if (groupsInListViewList.get(position).getViewType() ==0){
                view = layoutInflater.inflate(R.layout.list_view_details,parent,false);
            }else {
                view = layoutInflater.inflate(R.layout.list_view_details_type_two,parent,false);
            }

        }

        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null){
            holder = new ViewHolder();
            holder.groupName = (TextView) view.findViewById(R.id.group_item_show);
            view.setTag(holder);
        }
        holder.groupName.setText(groupsInListViewList.get(position).getGroupName());
        groupsInListViewList.get(position).setGroupId(position);
        groupsInListViewList.get(position).setIsGrouped(true);
//        点击分组,显示子列表
        holder.groupName.setTag(position);
        holder.groupName.setOnClickListener(onClickListener);
        return view;
    }
    private final class ViewHolder{
        public TextView groupName;
    }
}
