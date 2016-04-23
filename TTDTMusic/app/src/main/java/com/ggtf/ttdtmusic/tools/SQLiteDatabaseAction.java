package com.ggtf.ttdtmusic.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ggtf.ttdtmusic.database.MusicSQLiteHelper;

/**
 * Created by ggtf at 2015/10/30
 * Author:ggtf
 * Time:2015/10/30
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class SQLiteDatabaseAction {
    private SQLiteDatabaseAction(){
    }

    public static boolean insert(Context context){
        boolean result = false;
        /**
         * 获取或者打开数据库
         */
        MusicSQLiteHelper helper = new MusicSQLiteHelper(context);
        /**
         * 向数据库中写入数据
         */
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "Orange");
        values.put("totalTime", 10000);
        values.put("playTime", 5000);
        values.put("isLocal", true);
        values.put("path", "Cache/music/yueguang.mp3");
        writableDatabase.beginTransaction();
        long insert = writableDatabase.insert("MUSIC_PLAY", null, values);
        if (insert!=-1){
            Log.i("Info", "插入数据成功!插入在第" + insert + "行");
            writableDatabase.setTransactionSuccessful();
            result = true;
        }else {
            Log.i("Info", "插入数据失败! ");

        }
        writableDatabase.endTransaction();
        writableDatabase.close();

        return result;
    }
}
