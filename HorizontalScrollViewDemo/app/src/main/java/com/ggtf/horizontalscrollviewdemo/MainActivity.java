package com.ggtf.horizontalscrollviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        HorizontalScrollViewCustom horizontalScrollViewCustom = new HorizontalScrollViewCustom(this);
//        setContentView(horizontalScrollViewCustom);
//        setContentView(R.layout.sidling_menu);
//        setContentView(R.layout.text_input_layout);
//        initView();
    }

    private void initView() {
        HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.horizontal_scroll_view);
        scrollView.smoothScrollTo(100,0);

    }
}
