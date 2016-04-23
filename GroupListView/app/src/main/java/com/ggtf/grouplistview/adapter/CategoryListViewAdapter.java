package com.ggtf.grouplistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ggtf.grouplistview.R;
import com.ggtf.grouplistview.models.Groups;
import com.ggtf.grouplistview.models.ListViewData;
import com.ggtf.grouplistview.tools.ConstantViewType;

import java.util.List;

/**
 * Created by ggtf at 2015/10/17
 * Author:ggtf
 * Time:2015/10/17
 * Email:15170069952@163.com
 * ProjectName:GroupListView
 */
public class CategoryListViewAdapter extends BaseAdapter {
    private Context context;
    private List<ListViewData> groups;
    private LayoutInflater layoutInflater;
    private View.OnClickListener onClickListener;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;

    public CategoryListViewAdapter(Context context, List<ListViewData> groups,
                                   View.OnClickListener onClickListener,
                                   CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.context = context;
        this.groups = groups;
        this.onClickListener = onClickListener;
        this.onCheckedChangeListener = onCheckedChangeListener;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return groups != null ? groups.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return groups.get(position);
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
        return groups.get(position).getViewType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        int viewType = groups.get(position).getViewType();
        switch (viewType){
            /**
             * 组名视图加载
             */
            case ConstantViewType.GROUP_VIEW_TYPE:
               view =  initGroupView(position,convertView,parent);
                break;
            /**
             * 具体联系人视图加载
             */
            case ConstantViewType.CONTACT_VIEW_TYPE:
               view =  initConstantView(position,convertView,parent);
                break;
        }
        return view;
    }

    /**
     * ListView多套布局复用直接更加视图类型分别加载
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    private View initConstantView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
            view = layoutInflater.inflate(R.layout.contacts, parent, false);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.name= (TextView) view.findViewById(R.id.contacts);
            holder.checkedState = (CheckBox) view.findViewById(R.id.contacts_checked_state);
            view.setTag(holder);
        }

        holder.checkedState.setChecked(groups.get(position).isChecked());
        holder.checkedState.setVisibility(groups.get(position).isCheckable() ? View.VISIBLE : View.GONE);
        holder.checkedState.setTag(position);
        holder.checkedState.setOnCheckedChangeListener(onCheckedChangeListener);
        holder.name.setText(groups.get(position).getContent());
        holder.name.setTag(position);
        holder.name.setOnClickListener(onClickListener);

        return view;
    }

    private View initGroupView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
            view = layoutInflater.inflate(R.layout.category, parent, false);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.group_name);
            view.setTag(holder);
        }
        holder.name.setText(groups.get(position).getContent());
        holder.name.setTag(position);
        holder.name.setOnClickListener(onClickListener);
        return view;
    }


    private final class ViewHolder {
        public TextView name;
        public CheckBox checkedState;
    }

}
