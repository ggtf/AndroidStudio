package com.ggtf.listviewtraining;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener, View.OnTouchListener {

    private boolean isTop;
    private boolean isBottom;
    private ListView listView;
    private float currentY;
    private float previousY;
    private View headerView;
    private View footerView;
    private boolean isAddHeadView;
    private LinearLayout.LayoutParams layoutParams;
    private ImageView headImage;
    private int headImageMarginTop;
    private boolean isOverFlowOut;
    private Thread flushDataThread;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg != null) {
                switch (msg.what) {
                    case 0:

                        if (layoutParams.topMargin<=0){
                            layoutParams.topMargin = headImageMarginTop+msg.arg1;
                        }else {
                            layoutParams.topMargin = 0;
                            Log.i("Info","图片已经完全滑出");
                            isOverFlowOut = true;
                        }
                        headImage.setLayoutParams(layoutParams);
                        break;
                    case 1:
                        /**
                         * 刷新结束后将headImage的TopMargin设置为原先的数值;isOverFlowOut恢复为false
                         */
                        Log.i("Info"," finished flushDataThread isAlive = "+flushDataThread.isAlive());
                        layoutParams.topMargin = headImageMarginTop;
                        headImage.setLayoutParams(layoutParams);
                        isOverFlowOut = false;
                        isRunning = false;
                        break;
                }
            }

        }
    };
    /**
     * 刷新数据线程是否在运行;
     */
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        headImage = (ImageView) findViewById(R.id.head_image);

        List<String> items = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            items.add("Android" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        headerView = LayoutInflater.from(this).inflate(R.layout.list_header_view, null);
        footerView = LayoutInflater.from(this).inflate(R.layout.list_footer_view, null);

        Drawable overScrollFooter = getResources().getDrawable(R.drawable.over_scroll_footer);
        Drawable overScrollHeader = getResources().getDrawable(R.drawable.over_scroll_header);

//        listView.setOverscrollFooter(overScrollFooter);
//        listView.setOverscrollHeader(overScrollHeader);


        listView.setAdapter(adapter);
        listView.setOnScrollListener(this);
        listView.setOnTouchListener(this);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
        if (!isOverFlowOut){
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) headImage.getLayoutParams();
            headImageMarginTop = params.topMargin;
            layoutParams.height = Math.abs(headImageMarginTop);
            Log.i("Info","headImageMarginTop = "+headImageMarginTop);
        }

        /**
         * 实现步骤
         * 1.首先获取作为下拉视图View的TopMargin;为负值;
         * 2.定义一个变量来记录下这个TopMargin;
         * 3.在OnTouch事件中ACTION_MOVE方法启动一个线程来提交数据来改变下拉视图View的TopMargin的值
         * 4.线程提交的数据为了显示出更加明显的下拉效果,将手指在屏幕滑动的距离取1/3作为下拉的数据传递给Handler,用于更新;
         * 5.当下拉视图的TopMargin为0时,如果不需要再继续下拉时,可以开启数据刷新;
         * 5.1如果下拉式图的TopMargin为非负数,并且手指还在滑动;那就依据手指离开屏幕,
         * 也就是在此基础上event的ACTION_UP事件执行出来;设置一个标记,来执行刷新;
         *
         */


    }


    /*
    ListView的onScrollListener()事件监听
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        /**
         * SCROLL_STATE_IDLE:停止了滚动
         * SCROLL_STATE_FLING:惯性滑动
         * SCROLL_STATE_TOUCH_SCROLL:手指在屏幕上滑动
         *
         */
        if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
            if (isTop) {
//                Toast.makeText(this, "滑倒顶部了", Toast.LENGTH_SHORT).show();
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.MATCH_PARENT);
//                layoutParams.topMargin = 50;
//                listView.setLayoutParams(layoutParams);


            } else if (isBottom) {
                Toast.makeText(this, "滑倒底部了", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        isTop = (firstVisibleItem == 0);
        isBottom = (firstVisibleItem + visibleItemCount == totalItemCount);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                currentY = y;
                previousY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                currentY = y;
                if (!isOverFlowOut){
                    new Thread() {
                        @Override
                        public void run() {
                            Message message = handler.obtainMessage();
                            message.arg1 = (int) (currentY-previousY)/3;
                            handler.sendMessage(message);
                        }
                    }.start();
                }
//                if (isBottom) {
//                    if (Math.abs(currentY - previousY) > 200) {
//                        Toast.makeText(this, "最底部往上滑", Toast.LENGTH_SHORT).show();
//                        listView.removeFooterView(footerView);
//                        listView.addFooterView(footerView);
//                    }
//                }

                break;
            case MotionEvent.ACTION_UP:
                if (layoutParams.topMargin<10 && !isOverFlowOut){
                    layoutParams.topMargin = headImageMarginTop;
                }
                /**
                 * 当headImage完全滑出时,手指离开屏幕时,进行数据的刷新
                 */
                if (isOverFlowOut && !isRunning){
                    flushDataThread = null;
                    flushDataThread = new Thread(){
                        @Override
                        public void run() {
                            /**
                             * 在Run方法中执行数据的更新操作
                             */
                            try {
                                Thread.sleep(1500);
                                /**
                                 * 刷新数据后通知headImage恢复到最初的状态
                                 */
                                handler.sendEmptyMessage(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    flushDataThread.start();
                    isRunning = true;
                    Log.i("Info"," flushDataThread isAlive = "+flushDataThread.isAlive());
                }
                previousY = currentY;
                break;
        }
        return false;
    }
}
