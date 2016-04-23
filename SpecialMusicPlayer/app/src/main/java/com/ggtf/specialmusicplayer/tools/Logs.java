package com.ggtf.specialmusicplayer.tools;

/**
 * Created by ggtf at 2016/4/21
 * Author:ggtf
 * Time:2016/4/21
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */

import android.content.Context;
import android.util.Log;

import com.ggtf.specialmusicplayer.storages.FileManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日志打印和记录
 */
public class Logs {
    private static boolean isShowLog = true;/*不开启打印Log*/

    private Logs() {

    }

    private static final String TAG = "Info";

    private static File logDir;

    public static void launchLogs(Context context) {
        if (!isShowLog) return;
        logDir = FileManager.createLogsFile(context);
    }

    public static void write2Logfile(String log) {
        if (!isShowLog) return;
        if (logDir != null && log != null) {
            String logs = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis())) + "\n" + log + "\n";
            byte[] bytes = logs.getBytes();
            FileManager.write2File("Logfile", logDir, bytes);
        }
    }

    public static void i(String params) {
        if (!isShowLog) return;
        Log.i(TAG, params);
    }

    public static void e(String params) {
        if (!isShowLog) return;
        Log.e(TAG, params);
    }

    public static void w(String params) {
        if (!isShowLog) return;
        Log.w(TAG, params);
    }

    public static void d(String params) {
        if (!isShowLog) return;
        Log.d(TAG, params);
    }

    public static void i(Throwable throwable) {
        if (!isShowLog) return;
        Log.i(TAG, "Exception----\n ", throwable);
    }

    public static void write2Logfile(Throwable ex) {
        if (!isShowLog) return;
        if (logDir != null && ex != null) {
            String logs = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis())) + "\n" + ex.getCause().getMessage() + "\n";
            byte[] bytes = logs.getBytes();
            FileManager.write2File("Logfile", logDir, bytes);
        }
    }


}
