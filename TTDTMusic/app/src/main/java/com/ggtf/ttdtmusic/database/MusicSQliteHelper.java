package com.ggtf.ttdtmusic.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ggtf at 2015/10/30
 * Author:ggtf
 * Time:2015/10/30
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class MusicSQLiteHelper extends SQLiteOpenHelper {
//    private final static String CREATE_TABLE = "CREATE TABLE MUSIC_PLAY (\n" +
//            "    id        INTEGER PRIMARY KEY\n" +
//            "                      UNIQUE\n" +
//            "                      NOT NULL,\n" +
//            "    name      TEXT    NOT NULL,\n" +
//            "    totalTime TEXT    NOT NULL,\n" +
//            "    playTime  TEXT    NOT NULL\n" +
//            "                      DEFAULT (0),\n" +
//            "    isLocal   BOOLEAN NOT NULL\n" +
//            "                      DEFAULT true,\n" +
//            "    path      TEXT    NOT NULL\n" +
//            ");";
    private final static String CREATE_TABLE="CREATE TABLE MUSIC_PLAY (\n" +
        "    id        INTEGER PRIMARY KEY AUTOINCREMENT\n" +
        "                      UNIQUE\n" +
        "                      NOT NULL,\n" +
        "    name      TEXT    NOT NULL\n" +
        "                      UNIQUE,\n" +
        "    totalTime TEXT    NOT NULL,\n" +
        "    playTime          NOT NULL\n" +
        "                      DEFAULT (0),\n" +
        "    isLocal   BOOLEAN NOT NULL\n" +
        "                      DEFAULT true,\n" +
        "    path              NOT NULL\n" +
        ");";
    private final static String DATABASE_NAME = "music.dp";
    private final static int VERSION = 2;

    public MusicSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MusicSQLiteHelper(Context context) {
        this(context,DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.i("Info","onCreate SQLiteDatabase");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion<newVersion){
            db.execSQL(CREATE_TABLE);
        }
        Log.i("Info","onUpgrade SQLiteDatabase");
    }
}
