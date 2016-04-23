package com.ggtf.listviewtraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class FlushActivity extends AppCompatActivity implements View.OnTouchListener {

    private View headView;
    private int newY;
    private int oldY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flush);
        final ListView flushListView = (ListView) findViewById(R.id.flush_list_view);
//        headView = LayoutInflater.from(this).inflate(R.layout.list_header_view, null);
        headView = LayoutInflater.from(this).inflate(R.layout.flush_head_view, null);
        ListView.LayoutParams layoutParams = new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        headView.setLayoutParams(layoutParams);
        List<String> items = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            items.add("Android" + i);
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        flushListView.addHeaderView(headView);
        flushListView.setAdapter(adapter);
        headView.setPadding(0, -300, 0, 0);
        flushListView.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                oldY = (int) event.getY();
                newY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                newY = (int) event.getY();
                int distance = newY - oldY;
                if (distance >100){
                    Log.i("Info", "newY-oldY = " + distance);
                    headView.setPadding(0, -300+(distance/3), 0, 0);
                }

                break;
            case MotionEvent.ACTION_UP:
                oldY = newY;
                if (headView.getPaddingTop()>0){
                    headView.setPadding(0, 0, 0, 0);
                    Toast.makeText(FlushActivity.this, "flush is start", Toast.LENGTH_SHORT).show();
                    FlushDataAsyncTask asyncTask = new FlushDataAsyncTask();
                    asyncTask.execute(headView);
                }else if (headView.getPaddingTop()>-300){
                    headView.setPadding(0,-300,0,0);
                }
                break;
        }
        /**
         * return false 这样就不会影响ListView的滑动事件;
         * 或者调用父类的返回,return super.onTouchEvent(event);
         */
        return false;
    }
}
