package com.ggtf.toucheventdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private LinearLayout layout;
    private FrameLayout frameLayout;
    private ImageView imageView;
    private int oldY;
    private int newY;
    private int oldX;
    private int newX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        layout = (LinearLayout) findViewById(R.id.linear_layout);
        frameLayout = (FrameLayout) findViewById(R.id.fragment);
        imageView = (ImageView) findViewById(R.id.image_view);
        layout.setClickable(true);
        frameLayout.setClickable(true);
        imageView.setClickable(true);

        layout.setOnTouchListener(this);
        frameLayout.setOnTouchListener(this);
        imageView.setOnTouchListener(this);
//        imageView.getParent().requestDisallowInterceptTouchEvent(true);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                oldY = (int) event.getRawY();
                oldX = (int) event.getRawX();
                if (v == layout) {

                } else if (v == frameLayout) {

                } else if (v == imageView) {

                }
                return true;
            case MotionEvent.ACTION_MOVE:
                newY = (int) event.getRawY();
                newX = (int) event.getRawX();
                if (v == layout) {
                    if (newY - oldY > 20 || newY - oldY < -20) {
                        int distance = newY - oldY;
                        frameLayout.setTranslationY(distance / 2);
                    } else if (newX - oldX > 20 || newX - oldX < -20) {
                        int distance = newX - oldX;
                        frameLayout.setTranslationX(distance / 2);
                    }

                } else if (v == frameLayout) {
                    if (newY - oldY > 20 || newY - oldY < -20) {
                        int distance = newY - oldY;
                        imageView.setTranslationY(distance / 2);
                        if (distance>100){
                            frameLayout.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    } else if (newX - oldX > 20 || newX - oldX < -20) {
                        int distance = newX - oldX;
                        imageView.setTranslationX(distance / 2);
                    }

                } else if (v == imageView) {
                    if (newY - oldY > 20 || newY - oldY < -20) {
                        int distance = newY - oldY;
                        frameLayout.setTranslationY(distance / 2);
                    } else if (newX - oldX > 20 || newX - oldX < -20) {
                        int distance = newX - oldX;
                        frameLayout.setTranslationX(distance / 2);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (v == layout) {
                    frameLayout.setTranslationY(0);
                    frameLayout.setTranslationX(0);
                    Log.i("Info","layout--ACTION_UP");
                } else if (v == frameLayout) {
                    imageView.setTranslationX(0);
                    imageView.setTranslationY(0);
                    Log.i("Info", "frameLayout--ACTION_UP");
                } else if (v == imageView) {
                    frameLayout.setTranslationY(0);
                    frameLayout.setTranslationX(0);
                    Log.i("Info", "imageView--ACTION_UP");
                }
                break;
        }
        return false;
    }
}
