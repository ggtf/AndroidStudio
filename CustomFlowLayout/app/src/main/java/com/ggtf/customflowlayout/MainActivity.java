package com.ggtf.customflowlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ggtf.customflowlayout.adapters.RecycleViewAdapter;
import com.ggtf.customflowlayout.adapters.StaggeredAdapter;
import com.ggtf.customflowlayout.customs.CustomFlowLayout;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomFlowLayout flowLayout;
    private ImageView addChildView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFlowLayoutView();
//        initRecycleView();
//        initRecycleViewStaggeredView();
    }

  /*  private void initRecycleViewStaggeredView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        List<String> hotTags = initHotTags();
        StaggeredAdapter adapter = new StaggeredAdapter(this,hotTags);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
    }

    private void initRecycleView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        List<String> hotTags = initHotTags();
        RecycleViewAdapter adapter = new RecycleViewAdapter(this,hotTags);
        recyclerView.setAdapter(adapter);
        *//**
     * 设置RecycleView的布局管理
     *//*
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        *//**
     * 设置RecycleView的分割线
     *//*
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        *//**
     * 变换成GridView
     *//*
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        *//**
     * 变换成水平ListView
     *//*
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        */

    /**
     * 变换成HorizontalGridVIew
     *//*
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL));
    }
*/
    private List<String> initHotTags() {
        List<String> hotTags = new LinkedList<>();
        for (int i = 'A'; i < 'z'; i++) {
            hotTags.add(String.valueOf((char) i));
        }
        return hotTags;

    }

    private void initFlowLayoutView() {
        flowLayout = (CustomFlowLayout) findViewById(R.id.flow_layout_view_group);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 8;
        lp.rightMargin = 8;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < 10; i++) {
            TextView tv = (TextView) inflater.inflate(R.layout.flow_item, flowLayout, false);
            if (i == 0) {
                tv.setText("ADD");
                tv.setClickable(true);
                tv.setOnClickListener(this);
            } else if (i % 2 == 0) {
                tv.setText("Android" + i);
            } else {
                tv.setText("JAVA" + i);
            }

            flowLayout.addView(tv, 0);
        }
    }

    @Override
    public void onClick(View v) {
        LayoutInflater inflater = LayoutInflater.from(this);
        TextView tv = (TextView) inflater.inflate(R.layout.flow_item, flowLayout, false);
        tv.setText("New Item");
        flowLayout.addView(tv, 0);

    }
}
