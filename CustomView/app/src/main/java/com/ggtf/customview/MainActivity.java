package com.ggtf.customview;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;

import com.ggtf.customview.adapter.IndexAdapter;
import com.ggtf.customview.custom.MyListView;

import java.text.CollationElementIterator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {


    private List<String> data;
    private ArrayAdapter<String> adapter;
    private GridView gridViewList;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom);
//        setContentView(R.layout.widget_layout);
        /**
         * 自定义View
         * 1.自绘控件
         * 2.组合控件
         * 3.继承控件
         */
        final LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
        layout.post(new Runnable() {
            @Override
            public void run() {
                height = layout.getHeight();
                initView();
            }
        });


        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(20);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,256,3, TimeUnit.SECONDS,workQueue);
        Task task = new Task();
        task.executeOnExecutor(poolExecutor,"Apple");
//        RadioButton radioButton = new RadioButton(this);
//        radioButton.setCompoundDrawables(null,R.mipmap.ic_launcher,null,null);


    }

    private void initView() {
        gridViewList = (GridView) findViewById(R.id.grid_view_list);
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        List<String> words = new LinkedList<>();
        for (int i = 'A'; i <='Z' ; i++) {
            words.add(String.valueOf((char)i));
        }
        IndexAdapter indexAdapter = new IndexAdapter(this,words,this,this,height);
        data = new LinkedList<>();
        for (int i = 'A'; i <='Z' ; i++) {
            data.add(String.valueOf((char) i));
            data.add(String.valueOf((char) i));
        }
        Collections.sort(data);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, data);
        gridViewList.setAdapter(adapter);
        gridView.setAdapter(indexAdapter);
    }

    @Override
    public void onClick(View v) {
//        if (v instanceof CustomCounterView){
//            CustomCounterView counterView = (CustomCounterView) v;
//            String text = counterView.getText();
//            if (data.contains(text)){
//                int position = 0;
//                position = data.indexOf(text);
//                gridViewList.setSelection(position);
//            }
//
//        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v instanceof CustomCounterView){
            CustomCounterView counterView = (CustomCounterView) v;
            String text = counterView.getText();
            if (data.contains(text)){
                int position = 0;
                position = data.indexOf(text);
                int action = event.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        gridViewList.setSelection(position);
                        break;
                    case MotionEvent.ACTION_MOVE:
//                        gridViewList.setSelection(position);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
            }

        }

        return true;
    }

    public class Task extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }
}
