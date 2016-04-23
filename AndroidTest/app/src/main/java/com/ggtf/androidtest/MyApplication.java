package com.ggtf.androidtest;

import android.app.Application;

import org.xutils.x;

/**
 * Created by ggtf at 2016/1/25
 * Author:ggtf
 * Time:2016/1/25
 * Email:15170069952@163.com
 * ProjectName:AndroidTest
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        x.Ext.init(this);
        super.onCreate();
    }
}
