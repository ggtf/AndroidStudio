package com.ggtf.androidtraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    /**
     * 使用包名指定常量值,可以确保常量值引用唯一
     */
    public static final String EXTRA_MESSAGE = "com.ggtf.androidtraining.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Action bar;支持与Android 3.0/以上;
         * 默认主题Theme:Theme.Holo(获取其子类)
         * targetSdkVersion / minSdkVersion 属性默认11(或者更高);
         *
         * Android2.1版本/以上如果想要支持Action Bar 需要使用支持包 v7 appcompat;
         * Activity继承于ActionBarActivity;
         * Theme主题实现Theme.AppCompat主题;
         *
         * ActionBar布局定义在res/menu文件目录下;
         *
         *
         *
         */
    }

    /**
     * 按钮点击事件
     * @param view
     */
    public void sendMessage(View view) {
        /**
         * 参数1:Context 上下文;
         * 参数2:Class 类对象;
         */
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        /**
         * findViewById();依据资源Id获取对应对象实例;
         */
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        /**
         * Intent 携带数据,键值对;
         */
        intent.putExtra(EXTRA_MESSAGE, message);
        /**
         * 传递Intent实例对象
         */
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * 获取MenuInflater对象,来用于加载
         */
        MenuInflater inflater = getMenuInflater();
        /**
         * 使用inflate()方法,来从资源文件中加载menu布局到指定的menu对象中;
         */
        inflater.inflate(R.menu.main_activity_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * ActionBar 的按钮被点击将会触发onOptionItemSelected()回调;
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**
         * 获取被点击的MenuItem子选项的ID值;
         */
        int itemId = item.getItemId();
        /**
         * 根据ID值的匹配,来判断是哪一个MenuItem被点击;需要处理点击事件;
         */
        switch (itemId){
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_settings:
                openSetting();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSetting() {

    }

    private void openSearch() {

    }
}
