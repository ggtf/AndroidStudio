package com.ggtf.xutilstoolsdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.ResType;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * XUtils标记View
     */
    @ViewInject(R.id.image01)
    private ImageView image01;
    @ViewInject(R.id.image03)
    private ImageView image03;
    @ViewInject(R.id.image02)
    private ImageView image02;
    /**
     * XUtils标记引用资源ID
     */
    @ResInject(id = R.string.app_name, type = ResType.String)
    private String appName = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 通过反射获取使用了@ViewInject指定Id的View视图对象
         * 实际上相当于执行了findViewById()操作;
         */
        ViewUtils.inject(this);
        image01.setClickable(true);
        image01.setOnClickListener(this);
        Log.i("Info", "appName = " + appName);

        /**
         * BitmapUtils
         */
        BitmapUtils bitmapUtils = new BitmapUtils(this);
        /**
         * configDefaultLoadingImage()设置加载网络图片时显示的默认图片
         */
        String url1="http://img.ivsky.com/img/tupian/pre/201508/09/white_eye.jpg";
        bitmapUtils.configDefaultLoadingImage(R.mipmap.ic_launcher).display(image01, url1);
//        bitmapUtils.display(image03, "http://img.ivsky.com/img/tupian/t/201508/09/white_eye-003.jpg");

//        bitmapUtils.display(image03, "assets/images/fish.jpg");
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){}
        File cacheDir = getCacheDir();
        /**
         * 将网络加载的图片缓存到指定目录下
         */
        String url = "http://img.ivsky.com/img/tupian/t/201508/09/taiyangniao-001.jpg";
        BitmapUtils bitmapUtilsInMyCache = new BitmapUtils(this, cacheDir.getPath(), 1024 * 1024 * 5, 1024 * 1024 * 10);
        bitmapUtilsInMyCache.display(image02, url);

//        File bitmapFileFromDiskCache = bitmapUtilsInMyCache.getBitmapFileFromDiskCache(url);
        File bitmapFileFromDiskCache = bitmapUtils.getBitmapFileFromDiskCache(url1);
        if (bitmapFileFromDiskCache != null) {
            long length = bitmapFileFromDiskCache.length();
            Bitmap bitmap = BitmapFactory.decodeFile(bitmapFileFromDiskCache.getPath());
            image03.setImageBitmap(bitmap);
            LogUtils.i(String.valueOf(length));
        }


    }

    /**
     * 实例对象绑定方法
     *
     * @param view
     */
    @OnClick(R.id.image03)
    public void onxUtisClick(View view) {
        if (view == image03) {
            Toast.makeText(this, "image03被点击", Toast.LENGTH_SHORT).show();
            /**
             * LogUtils;
             *前缀---可以指定到具体类的具体方法的某一行;
             */
//        指定自定义的前缀,便于过滤;
            LogUtils.customTagPrefix = "Info";
            LogUtils.i("image03");

            /**
             * DbUtils
             */
            DbUtils myda = DbUtils.create(this, "myda");
            UserData userData = new UserData();
            userData.name = "Apple";
            userData.password = "123456";
            userData.email = "1212323@163.com";
            /**
             * 将实体类写入数据库
             */
            try {
                myda.save(userData);
            } catch (DbException e) {
                e.printStackTrace();
            }

            /**
             * 删除该数据库
             */
//        myda.delete(userData);
        }
    }


    public void imageClicked(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.image01:
                break;
            case R.id.image02:
                break;
            case R.id.image03:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == image01) {
            Toast.makeText(this, "image01被点击", Toast.LENGTH_SHORT).show();
        }
    }

    public class UserData {
        /**
         * 声明该变量为主键
         */
        @com.lidroid.xutils.db.annotation.Id
        private int Id = 0;
        private String name;
        private String password;
        private String email;
    }
}
