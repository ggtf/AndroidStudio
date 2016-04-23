package com.ggtf.mytime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class DetailActivity extends AppCompatActivity implements View.OnTouchListener {
    private float oldX;
    private float newX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_fragment);
        FrameLayout detailActivity = (FrameLayout) findViewById(R.id.detail_activity_fragment);
        detailActivity.setOnTouchListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_stay, R.anim.activity_out_right);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                oldX = event.getRawX();
                newX = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                newX = event.getRawX();
                break;
            case MotionEvent.ACTION_UP:
                newX = event.getRawX();
                int distance = (int) (newX - oldX);
                if (distance>300){
                    finish();
                    overridePendingTransition(R.anim.activity_stay,R.anim.activity_out_right);
                }else if (distance<-300){
                    finish();
                    overridePendingTransition(R.anim.activity_stay,R.anim.activity_out_left);
                }
//                Log.i("Info", "" + getWindowManager().getDefaultDisplay().getWidth());
                break;
        }
        return true;
    }
}
