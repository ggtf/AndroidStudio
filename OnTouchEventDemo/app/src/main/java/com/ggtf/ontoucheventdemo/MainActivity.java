package com.ggtf.ontoucheventdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.Properties;

public class MainActivity extends AppCompatActivity implements CallBack {

    private Linear linear;
    private Frame frame;
    private Image image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linear = (Linear) findViewById(R.id.linear);
        frame = (Frame) findViewById(R.id.frame);
        image = (Image) findViewById(R.id.image);
//        Log.i("Info","Linear.class"+linear.toString());
//        Log.i("Info","frame.class"+frame.toString());
//        Log.i("Info","image.class"+image.toString());
        /**
         * 事件冲突时(焦点在子View上);
         * Linear自身实现Touch事件,不分发;Linear拦截事件分发
         */
        linear.setSubViewAction(false);
        /**
         * 事件冲突时(焦点在子View上);
         * Linar自身不处理Touch事件,分发给子View-Frame处理,Linear不拦截事件分发
         * Frame自己处理,不分发给Image,Frame拦截事件分发;
         */
        linear.setSubViewAction(true);
        frame.setSubViewAction(false);
        /**
         * ViewGroup都不处理Touch事件,交给子View =Image处理
         */
        linear.setSubViewAction(true);
        frame.setSubViewAction(true);
        image.setCallBack(this);
        linear.setCallBack(this);
        frame.setCallBack(this);
    }

    @Override
    public void click(View view) {
        if (view == image){
            if (Tools.newY-Tools.oldY>100){
                Toast.makeText(MainActivity.this, "执行下拉事件", Toast.LENGTH_SHORT).show();
            }else if(Tools.newY-Tools.oldY<-100){
                Log.i("Info","Tools.newY = "+Tools.newY);
                Log.i("Info","Tools.oldY = "+Tools.oldY);
                Toast.makeText(MainActivity.this, "执行上拉事件", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this, "我是图片点击事件", Toast.LENGTH_SHORT).show();
            }

        }
        if (view == frame){
            if (Tools.newY-Tools.oldY>100){
                Toast.makeText(MainActivity.this, "执行下拉事件", Toast.LENGTH_SHORT).show();
            }else if(Tools.newY-Tools.oldY<-100){
                Toast.makeText(MainActivity.this, "执行上拉事件", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this, "我是frame点击事件", Toast.LENGTH_SHORT).show();
            }
        }
        if (view == linear){
            if (Tools.newY-Tools.oldY>100){
                Toast.makeText(MainActivity.this, "执行下拉事件", Toast.LENGTH_SHORT).show();
            }else if(Tools.newY-Tools.oldY<-100){
                Toast.makeText(MainActivity.this, "执行上拉事件", Toast.LENGTH_SHORT).show();
            }
        }
        Tools.newX=0;
        Tools.newY=0;
    }

    @Override
    public void move(View view) {

    }

    /**
     * 测试反射
     */
    private void test(){
        Class<?> claz = getClass();
        try {
            Method a = claz.getMethod("A", null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Properties properties = new Properties();

    }
}
