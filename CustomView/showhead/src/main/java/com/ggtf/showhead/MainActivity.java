package com.ggtf.showhead;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener,Runnable{

    private FrameLayout main;
    private ImageView headImage;
    private int oldY;
    private int newY;
    private boolean isRunning;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0){
                Bundle data = msg.getData();
                float alpha = data.getFloat("Alpha");
                headImage.setAlpha(alpha);
                if (alpha<0.05){
                    headImage.setVisibility(View.INVISIBLE);
                    headImage.setAlpha(1.0f);
                    headImage.setScaleY(1.0f);
                    headImage.setTranslationY(0);
                    isRunning = false;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = (FrameLayout) findViewById(R.id.frame_layout);
        headImage = (ImageView) findViewById(R.id.head_image);
        main.setOnTouchListener(this);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                oldY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                newY = (int) event.getRawY();
                if (!isRunning){
                    if (newY-oldY>100){
                        headImage.setVisibility(View.VISIBLE);
                        int distance = -headImage.getHeight()+(newY-oldY)/5;
                        if (distance<-(headImage.getHeight()/2)){
                            headImage.setTranslationY(distance);
                        }
                        float scaleTime = 1+(newY-oldY)/40*0.02f;
                        headImage.setScaleY(scaleTime);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!isRunning){
                    new Thread(this).start();
                }
                break;
        }
        return true;
    }

    @Override
    public void run() {
        float count = 100;
        while (count>0){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = handler.obtainMessage(0);
            float alpha = count/100;
            Bundle data = new Bundle();
            data.putFloat("Alpha",alpha);
            message.setData(data);
            handler.sendMessage(message);
            count = count-2;
        }
    }
}
