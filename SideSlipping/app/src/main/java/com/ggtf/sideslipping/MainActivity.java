package com.ggtf.sideslipping;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawerLayout;
    private int oldX;
    private int newX;
    private int widthLeft;
    private int widthRight;
    private Handler mainHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
        if (msg != null){
            int what = msg.what;
            switch (what){
                case 0:
                    int widthRight = msg.arg1;
                    setWidthRight(widthRight);
                    break;
                case 1:
                    int widthLeft = msg.arg1;
                    setWidthLeft(widthLeft);
                    break;
            }
        }

        }
    };

    public void setWidthLeft(int widthLeft){
        this.widthLeft = widthLeft;
    }
    public void setWidthRight(int widthRight){
        this.widthRight = widthRight;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        ListView listView = (ListView) findViewById(R.id.list_view);
        String[] data = {"Apple", "Orange", "Banana"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        /**
         * 获取右侧菜单对象,并获取到对应的宽高
         */
        final LinearLayout drawerRight = (LinearLayout) findViewById(R.id.drawer_right);
        drawerRight.post(new Runnable() {
            @Override
            public void run() {
                int widthRight = drawerRight.getWidth();
                Message message = mainHandler.obtainMessage(0);
                message.arg1 = widthRight;
                mainHandler.sendMessage(message);

            }
        });
//        DrawerLayout.LayoutParams layoutParamsRight = (DrawerLayout.LayoutParams) new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        drawerRight.setLayoutParams(layoutParamsRight);
//        Log.i("Info", "RightWidth = " + drawerRight.getLayoutParams().width);
//        Log.i("Info","RightHeight = "+drawerRight.getLayoutParams().height);
        /**
         * 为DrawerLayout设置监听事件
         * DrawerLayout.DrawerListener
         */
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        /**
         * 主体界面的布局
         */
        final FrameLayout frameLayout = (FrameLayout) findViewById(R.id.content_layout);
        /**
         * 为主体界面添加OnTouch事件;来设置如何滑动来滑出侧滑菜单;
         */
        frameLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        oldX = (int) event.getX();
                        newX = (int) event.getX();
//                        Log.i("Info","ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
//                        Log.i("Info","ACTION_MOVE");
                        newX = (int) event.getX();
                        if (newX - oldX > 100) {
                            drawerLayout.openDrawer(Gravity.LEFT);
                        } else if (newX - oldX < -100) {
                            drawerLayout.openDrawer(Gravity.RIGHT);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
//                        Log.i("Info","ACTION_UP");
                        oldX = newX;
                        break;
                }
                /**
                 * return false时,move_up这个事件会被父容器所处理;将不会执行这个OnTouch中的对应事件的方法;
                 */
                return true;
            }
        });


         final LinearLayout drawerLeft = (LinearLayout) findViewById(R.id.drawer_left);
//        layout.measure(100,100);
        drawerLeft.post(new Runnable() {
            @Override
            public void run() {
                 int widthLeft = drawerLeft.getWidth();
                Message message = mainHandler.obtainMessage(1);
                message.arg1 = widthLeft;
                mainHandler.sendMessage(message);


            }
        });
        listView.setOnItemClickListener(this);

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                Log.i("Info", "drawer正在滑动，滑动距离 = " + slideOffset);
                if (drawerView ==drawerLeft){
                    frameLayout.setTranslationX(slideOffset * widthLeft);
                }else if (drawerView == drawerRight){
                    frameLayout.setTranslationX(-slideOffset * widthRight);
                }

                frameLayout.setScaleY(1 - 0.08f * slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Log.i("Info", "drawer开启");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Log.i("Info", "drawer关闭");
//                frameLayout.setScaleY(1f);
            }

            /**
             * drawer的状态
             * 0    关闭
             * 1    滑动
             * 2    开启
             * @param newState
             */
            @Override
            public void onDrawerStateChanged(int newState) {
                Log.i("Info", "drawer的状态 = " + newState);
            }
        });

//        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
//
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                Log.i("Info", "drawer正在滑动，滑动距离 = " + slideOffset);
//                frameLayout.setTranslationX(slideOffset * 200);
//                frameLayout.setScaleY(1-0.2f*slideOffset);
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                Log.i("Info", "drawer开启");
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                Log.i("Info", "drawer关闭");
////                frameLayout.setScaleY(1f);
//            }
//
//            /**
//             * drawer的状态
//             * 0    关闭
//             * 1    滑动
//             * 2    开启
//             * @param newState
//             */
//            @Override
//            public void onDrawerStateChanged(int newState) {
//                Log.i("Info", "drawer的状态 = " + newState);
//            }
//        });
        /**
         * 回调方法可以自由选择，不必全部实现；可以只实现自己想要实现的回调方法
         */
//        drawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                super.onDrawerSlide(drawerView, slideOffset);
//            }
//        });
        /*
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                Log.i("Info","onDrawerOpened");
                if (getActionBar() !=null){
                    Log.i("Info","getActionBar != null");
                    getActionBar().setTitle("open");
                }
                invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Log.i("Info","onDrawerClosed");
                if (getActionBar() !=null){
                    getActionBar().setTitle("close");
                }
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        */
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(MainActivity.this, "位置position = " + position, Toast.LENGTH_SHORT).show();
    }

    public void showSideSilppingMenu(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_left:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.button_right:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
        }

    }
}
