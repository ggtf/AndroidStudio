package com.ggtf.activitysliding;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnTouchListener {

    private int oldX;
    private int newX;
    private int oldY;
    private int newY;
    private RelativeLayout main;
    private TextView left;
    private TextView top;
    private TextView right;
    private TextView bottom;
    private RelativeLayout.LayoutParams layoutParams;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = (RelativeLayout) findViewById(R.id.activity_main);
        main.setOnTouchListener(this);
        Window window = getWindow();
        Drawable drawable = getDrawable(R.drawable.activity_bg);
        window.setBackgroundDrawable(drawable);
        MyApplication application = (MyApplication) getApplication();
        application.activities.add(this);

        animation = AnimationUtils.loadAnimation(this, R.anim.render);
        main.startAnimation(animation);

        left = (TextView) findViewById(R.id.left);
        top = (TextView) findViewById(R.id.top);
        right = (TextView) findViewById(R.id.right);
        bottom = (TextView) findViewById(R.id.bottom);
        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    public RelativeLayout getMain() {
        return main;
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
                main.setTranslationX(newX - oldX);
                if (newY - oldY > 0) {
                    main.setPadding(0, newY - oldY, 0, 0);
                } else {
                    main.setPadding(0, 0, 0, oldY - newY);
                }
                break;
            case MotionEvent.ACTION_UP:

                newX = (int) event.getRawX();
                main.setPadding(0, 0, 0, 0);
                if (newX - oldX > 500) {
                    Intent intent = new Intent(this, SecondActivity.class);
                    startActivity(intent);
                    /**
                     * Activity切换动画
                     * 参数1，第二个activity进入的动画
                     * 参数2，当前activity退出的动画
                     */
//                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
                }
                main.setTranslationX(0);
                break;
        }
        return true;
    }
}
