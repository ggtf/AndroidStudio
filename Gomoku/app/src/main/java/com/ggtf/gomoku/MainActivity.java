package com.ggtf.gomoku;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();
    }

    private void initView() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        List<Integer> imageResId = new LinkedList<>();
        String[] titles = {"A","B","C"};
        imageResId.add(R.mipmap.ic_launcher);
        imageResId.add(R.mipmap.ic_launcher);
        imageResId.add(R.mipmap.ic_launcher);
        AdapterViewPager adapterViewPager = new AdapterViewPager(this,imageResId,titles);
        viewPager.setAdapter(adapterViewPager);
    }


}
