package com.ggtf.androidtraining;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    /**
     * onCreate()方法,创建设置Activity组件的第一步;
     * 必须定义setContentView()方法,来指定布局layout;
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);
        /**
         * 设置TextView最为该Activity的layout
         */
        setContentView(textView);
        /**
         * 回到Manifest.xml中定义的自身的Activity指定的PARENT_ACTIVITY;
         * 需要让app icon图标作为Up button 可以被点击;
         * 因此需要调用setDisplayHomeAsUpEnabled()方法
         * 在清单文件中指定了该Activity的PARENT_ACTIVITY,点击Up button时,系统会自动导航到parent activity
         * 无需处理Up button 的点击事件;
         *
         * Android 4.1(API level 16) 或者 继承于ActionBarActivity 可以采用getActionBar()来获取ActionBar实例对象;
         *
         * 采用支持包来实现ActionBar的实现 使用getSupportActionBar()来获取ActionBar实例对象
         */
        ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        /**
         * 定义ActionBar样式;
         * 使用Action Theme来完成样式的定义(使用Android自带的ActionBar主题样式,或者继承于主题在自定义符合app的样式);
         * Theme.Holo 暗色主题
         * Theme.Holo.Light 浅色主题
         * 在清单文件中声明Theme,使用android:theme属性;
         * 在<applications>元素标签内定义,定义为整体app样式;
         * 在<activity>元素标签中定义,定义为该Activity自身样式;
         *
         * 使用系统Theme主题样式,也可以指定浅色主题-深色ActionBar样式主题;
         * Theme.Holo.Light.DarkActionBar
         *
         * 使用Support Library来实现ActionBar时;必须指定主题Theme为Theme.AppCompat;
         * Theme.AppCompat: 暗色主题
         * Theme.AppCompat.Light: 浅色主题
         * Theme.AppCompat.Light.DarkActionBar 浅色主题带暗色ActionBar样式
         */
    }


    /**
     * action bar
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
