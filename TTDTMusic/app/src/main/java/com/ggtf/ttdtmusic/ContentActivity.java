package com.ggtf.ttdtmusic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ggtf.ttdtmusic.entities.TitleBarSearchLayout;
import com.ggtf.ttdtmusic.tools.WeakReferenceSetting;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class ContentActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    private float oldX;
    private float newX;
    private View contentSearch;
    private FrameLayout contentFrameLayout;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        layout = (LinearLayout) findViewById(R.id.content_activity_layout);
        layout.setOnTouchListener(this);
        initView();


    }

    private void initView() {
        FrameLayout titleBar = (FrameLayout) findViewById(R.id.title_bar);
        contentFrameLayout = (FrameLayout) findViewById(R.id.content_fragment);
        View titleBarSearch = getInflateView(this, R.layout.content_title_bar_search);
        titleBar.addView(titleBarSearch);
        WeakReferenceSetting.addAllInWeakReference(new TitleBarSearchLayout(titleBarSearch, this));

        contentSearch = getInflateView(this, R.layout.search_content_layout);
        View content1Search = getInflateView(this, R.layout.search_content1_layout);
        View content2Search = getInflateView(this, R.layout.search_content2_layout);


        contentSearch.setOnTouchListener(this);
        content1Search.setOnTouchListener(this);
        content2Search.setOnTouchListener(this);
        contentFrameLayout.addView(contentSearch);
        contentFrameLayout.addView(content1Search);
        contentFrameLayout.addView(content2Search);

//        WeakReference<TitleBarSearchLayout> searchLayoutWeakReference = new WeakReference<TitleBarSearchLayout>(new TitleBarSearchLayout(titleBarSearch,this));
//        TitleBarSearchLayout titleBarSearchLayout = new TitleBarSearchLayout(titleBarSearch,this);

    }

    /**
     * 依据布局Id来获取视图
     *
     * @param context
     * @param resId
     * @return
     */
    private View getInflateView(Context context, int resId) {
        return LayoutInflater.from(context).inflate(resId, null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.side_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                oldX = event.getRawX();
                newX = event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                newX = event.getRawX();
                if (v != layout) {
                    v.setTranslationX((newX - oldX) * 1.075f);
                    if (v.getX() > v.getWidth() - 150 || v.getX() < -v.getWidth() + 150) {
                        contentFrameLayout.removeView(v);
                    }
                } else {
                    if (newX - oldX > 50) {
                        int distance = (int) (newX - oldX);
                        if (distance > v.getWidth() / 2) ;
                        {
                            finish();
                            overridePendingTransition(R.anim.side_in_left, R.anim.slide_out_right);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (v!=layout){
                    v.setTranslationX(0);
                }
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
//            搜索界面的回退按钮
            case R.id.search_back:
                backMainActivity();
                break;
//            搜索界面的搜索按钮
            case R.id.search_in:
                Toast.makeText(this, "search_in", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 回退到MainActivity界面
     */
    private void backMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.side_in_left, R.anim.slide_out_right);
    }
}
