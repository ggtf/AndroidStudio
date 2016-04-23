package com.ggtf.selfimprovement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.ggtf.selfimprovement.adapter.SelfRecyclerViewAdapter;
import com.ggtf.selfimprovement.custom.SelfItemDecoration;
import com.ggtf.selfimprovement.tools.Tools;

public class MainActivity extends AppCompatActivity {
    public static final int RECYCLER_VIEW = 100;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (FrameLayout) findViewById(R.id.container);
        initChildView();
//        initSubThread();
    }

    private void initSubThread() {
        Thread subThread = new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    /**
     * 初始化子视图
     */
    private void initChildView() {
        View childView = LayoutInflater.from(this).inflate(R.layout.recycler_view, container, false);
        childView.setTag(RECYCLER_VIEW);
        initView(childView);
        container.addView(childView);
        initFloatButton();
    }

    /**
     * 初始化floatButton
     */
    private void initFloatButton() {
        View floatButton = LayoutInflater.from(this).inflate(R.layout.float_button, container, false);
        int width = getResources().getDimensionPixelSize(R.dimen.float_button_size);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, width);
        params.gravity = Gravity.BOTTOM | Gravity.END;
        params.setMargins(10, 10, width, width);
        floatButton.setLayoutParams(params);

        container.addView(floatButton);

    }

    /**
     * 依据当前视图的tag来区分显示的试图
     *
     * @param childView 加载到主布局的视图
     */
    private void initView(View childView) {
        boolean isHadChildView = container.getChildCount() > 0;
        if (!isHadChildView) {
            int tag = (int) childView.getTag();
            switch (tag) {
                case RECYCLER_VIEW:
                    initRecyclerView(childView);
                    break;
            }
        } else {
            container.removeAllViews();
        }
    }

    /**
     * 展示RecyclerView
     *
     * @param childView 布局容器
     */
    private void initRecyclerView(View childView) {
        RecyclerView recyclerView = (RecyclerView) childView.findViewById(R.id.recycler_view);
        /*布局管理器 线性 垂直或者水平方向*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        /*分隔线*/
        SelfItemDecoration decoration = new SelfItemDecoration(this, SelfItemDecoration.VERTICAL);
        /*适配器*/
        SelfRecyclerViewAdapter adapter = new SelfRecyclerViewAdapter(this, Tools.getTextShowData(30));

        recyclerView.addItemDecoration(decoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
