package com.ggtf.activitysliding;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggtf at 2015/11/18
 * Author:ggtf
 * Time:2015/11/18
 * Email:15170069952@163.com
 * ProjectName:CustomView
 */
public class MyApplication extends Application {
    public List<Activity> activities;
    @Override
    public void onCreate() {
        super.onCreate();
        activities = new LinkedList<>();
    }
}
