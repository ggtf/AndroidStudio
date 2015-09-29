package com.ggtf.supermarket;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.ggtf.supermarket.adapters.CarAdapter;
import com.ggtf.supermarket.models.CarItem;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CarAdapter adapter;
    private List<CarItem> carItems;
    private DataSetObserver observer;
    private TextView carTotalMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.car_list);
        carTotalMoney = (TextView) findViewById(R.id.car_total_fee);
        carItems = new LinkedList<>();
        for (int i = 0; i <30; i++) {
            CarItem carItem = new CarItem();
            carItem.setProductId(i);
            carItem.setCount(1);
            carItem.setProductName("轻松学Android" + i);
            carItem.setPrice(100.0f);
            carItems.add(carItem);

        }
        adapter = new CarAdapter(this, carItems);
        adapter.setOnClickListener(this);
//        利用Adapter的观察者，可以实现每次adapter notifyDataSetChange()都发触发 观察者
        observer = new DataSetObserver() {
            @Override
            public void onChanged() {
                float sum = 0;
//               TODO 算总价钱
                for (CarItem item : carItems) {
                    if (item.isChecked()){
                        float money = item.getCount() * item.getPrice();
                        sum += money;
                    }
                }
//              TODO 显示总价钱
                carTotalMoney.setText("￥"+sum);

            }

            @Override
            public void onInvalidated() {
                super.onInvalidated();
            }
        };
        adapter.registerDataSetObserver(observer);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        adapter.unregisterDataSetObserver(observer);
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Object tag = v.getTag();
        Integer position;

        switch (id){
            case R.id.item_car_delete:
                if (tag != null && tag instanceof Integer){
                    position = (Integer) tag;
                    int pos = position;
                    carItems.remove(pos);
                }

                break;
            case R.id.item_in_car:
                if (tag != null && tag instanceof Integer){
                    position = (Integer) tag;
                    CarItem carItem = carItems.get(position);
                    int count = carItem.getCount();
                    count++;
                    carItem.setCount(count);
                }

                break;
            case R.id.item_out_car:
                if (tag != null && tag instanceof Integer){
                    position = (Integer) tag;
                    CarItem carItem = carItems.get(position);
                    int count = carItem.getCount();
                    count--;
                    if (count>0){
                        carItem.setCount(count);
                    }
                }
                break;
            case R.id.item_car_check:
                if (tag != null && tag instanceof Integer){
                    position = (Integer) tag;
                    CarItem carItem = carItems.get(position);
                    if (v instanceof CheckBox){
                        CheckBox checkBox = (CheckBox) v;
                        carItem.setIsChecked(checkBox.isChecked());
                    }
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    public void btnEdit(View view) {
        adapter.switchEditMode();
    }
}
