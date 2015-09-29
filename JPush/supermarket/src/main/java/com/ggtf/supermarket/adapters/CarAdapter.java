package com.ggtf.supermarket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ggtf.supermarket.R;
import com.ggtf.supermarket.models.CarItem;

import java.util.List;

/**
 * Created by ggtf at 2015/9/29
 * Author:ggtf
 * Time:2015/9/29
 * Email:15170069952@163.com
 * ProjectName:JPushDemo
 */

/**
 * 购物车列表的适配器
 */
public class CarAdapter extends BaseAdapter {
    private Context context;
    private List<CarItem> carItems;
    private View.OnClickListener onClickListener;

    private boolean editMode;

    public CarAdapter(Context context, List<CarItem> carItems) {
        this.context = context;
        this.carItems = carItems;
    }

    /**
     * 主线程调用，用于切换编辑模式
     */
    public void switchEditMode(){
        editMode = !editMode;
        notifyDataSetChanged();
    }

    /**
     * 用于数量调整与删除的事件处理
     * @param onClickListener
     */
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        return carItems!=null?carItems.size():0;
    }

    @Override
    public Object getItem(int position) {
        return carItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return carItems.get(position).getProductId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
//        1.复用
        if (convertView !=null){
            view = convertView;
        }else {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_car,parent,false);
        }
//        2.ViewHolder
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.checkBox = (CheckBox) view.findViewById(R.id.item_car_check);
            holder.checkBox.setOnClickListener(onClickListener);
            holder.imgIcon = (ImageView) view.findViewById(R.id.item_car_product_icon);
            holder.txtProductName = (TextView) view.findViewById(R.id.item_car_product_name);
            holder.txtProductPrice = (TextView) view.findViewById(R.id.item_car_product_price);
//            删除条目
            holder.imgDelete = (ImageView) view.findViewById(R.id.item_car_delete);
            holder.imgDelete.setOnClickListener(onClickListener);
//           商品数量加一操作
            holder.btnInCar = view.findViewById(R.id.item_in_car);
            holder.btnInCar.setOnClickListener(onClickListener);
//           商品数量减一操作
            holder.btnOurCar = view.findViewById(R.id.item_out_car);
            holder.btnOurCar.setOnClickListener(onClickListener);

            holder.txtCount = (TextView) view.findViewById(R.id.item_car_product_count);
            view.setTag(holder);
        }
//        3.刷新数据
//        当前ListView的显示的部分，更新；
        CarItem carItem = carItems.get(position);
        holder.txtProductName.setText(carItem.getProductName());
        holder.txtProductPrice.setText(Float.toString(carItem.getPrice()));
        holder.txtCount.setText(Integer.toString(carItem.getCount()));
        holder.checkBox.setChecked(carItem.isChecked());
//        3.1设置带有点击事件的按钮所属的Item所在的位置的Tag，用于告诉监听器，到底点击了哪一个；
        holder.btnInCar.setTag(position);
        holder.btnOurCar.setTag(position);
        holder.imgDelete.setTag(position);
        holder.checkBox.setTag(position);
//        3.2编辑模式的支持
        if (editMode){
            holder.imgDelete.setVisibility(View.VISIBLE);
        }else {
            holder.imgDelete.setVisibility(View.INVISIBLE);
        }


        return view;
    }
    private static class ViewHolder{
        public CheckBox checkBox;
        public ImageView imgIcon;
        public TextView txtProductName;
        public TextView txtProductPrice;
        public ImageView imgDelete;
        public View btnInCar;
        public View btnOurCar;
        public TextView txtCount;
    }
}
