package com.ggtf.mytime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.ggtf.mytime.adapter.TimeAdapter;
import com.ggtf.mytime.entities.Reminds;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private int[] colors = {0xff1693a5, 0xfffbb829, 0xff800f25, 0xff6cdfea, 0xffff0066, 0xff025d8c};
    private float oldX;
    private float newX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        ListView listView = (ListView) findViewById(R.id.time_menu);
        List<Reminds> reminds = new LinkedList<>();
        initReminds(reminds);
        TimeAdapter adapter = new TimeAdapter(this, reminds);
        listView.setAdapter(adapter);
        listView.setOnTouchListener(this);
    }

    private void initReminds(List<Reminds> reminds) {
        for (int i = 0; i < 4; i++) {
            Reminds remind = new Reminds();
            remind.setDay(100 + " + " + i);
            remind.setDayBg(0x00ffffff);
            remind.setDayTextColor(0xffffffff);

            remind.setPreviousBg(colors[((int) (Math.random() * colors.length))]);

            remind.setThing("生日 " + i);
            remind.setThingBg(0xffffffff);
            remind.setThingTextColor(0xFFA4A4A4);

            reminds.add(remind);

        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                oldX = event.getRawX();
                newX = event.getRawY();
                return  true;
            case MotionEvent.ACTION_MOVE:
                newX = event.getRawX();
                break;
            case MotionEvent.ACTION_UP:
                newX = event.getRawX();
                int distance = (int) (newX - oldX);
                if (distance>300){
                    Intent intent = new Intent(this,DetailActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_in_left_to_right, R.anim.activity_stay);
                }else if (distance<-300){
                    Intent intent = new Intent(this,DetailActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_in_right_to_left, R.anim.activity_stay);
                }
                /**
                 * 获取设备的宽度
                 */
//                Log.i("Info", "" + getWindowManager().getDefaultDisplay().getWidth());
                break;
        }
        return false;
    }
}
