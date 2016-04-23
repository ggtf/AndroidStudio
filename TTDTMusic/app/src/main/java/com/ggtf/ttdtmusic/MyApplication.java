package com.ggtf.ttdtmusic;

import android.app.Application;
import android.content.Intent;
import android.os.*;
import android.os.Process;
import android.util.Log;
import android.widget.HorizontalScrollView;

import com.ggtf.ttdtmusic.service.MusicService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by ggtf at 2015/10/29
 * Author:ggtf
 * Time:2015/10/29
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class MyApplication extends Application {
    public MyApplication() {
//        Log.i("Info","MyApplication ");
    }





    public  void test(){
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES);
        File image = getDir("Image", MODE_PRIVATE);
        File filesDir = getFilesDir();
//        FileInputStream app = openFileInput("app");
//        FileOutputStream fileOutputStream = openFileOutput("App", MODE_PRIVATE);
        getSharedPreferences("app.config", MODE_PRIVATE);
//        int myPid = Process.myPid();
//        int myTid = Process.myTid();
//        int myUid = Process.myUid();
//        HorizontalScrollView
    }
}
