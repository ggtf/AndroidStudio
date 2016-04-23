package com.ggtf.activitysliding;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

public class SecondActivity extends Activity implements View.OnTouchListener {

    private RelativeLayout second;
    private int oldX;
    private int newX;
    private int oldY;
    private int newY;
    private MainActivity activity;
    private RelativeLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        second = (RelativeLayout) findViewById(R.id.activity_second);
        second.setOnTouchListener(this);
        MyApplication application = (MyApplication) getApplication();
        if (application.activities.size()>0){
            activity = (MainActivity) application.activities.get(0);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.activity_fade_out, R.anim.activity_fade_out);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                oldX = (int) event.getRawX();
                oldY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                newX = (int) event.getRawX();
                newY = (int) event.getRawY();
                if (activity!=null){
                    main = activity.getMain();
                    int distance = newX-oldX;
//                    main.setTranslationX(-main.getMeasuredWidth() + distance);
                }
                float scaleX = 1-(float) ((newX - oldX) * 1.0 / v.getMeasuredWidth());
                float scaleY = 1-(float) ((newY - oldY) * 1.0 / v.getMeasuredHeight());
//                float scaleY = (float) ((newY - oldY) * 1.0 / v.getMeasuredHeight());
//                second.setTranslationX(newX - oldX);
//                second.setTranslationY(newY - oldY);
                second.setScaleX(scaleX);
                second.setScaleY(scaleY);
                break;
            case MotionEvent.ACTION_UP:
                newX = (int) event.getRawX();
                second.setTranslationX(0);
                second.setTranslationY(0);
                second.setScaleX(1);
                second.setScaleY(1);
                if (newX-oldX>second.getMeasuredWidth()-200){
                    main.setTranslationX(0);
                    finish();
                }
                break;
        }
        return true;
    }
}
