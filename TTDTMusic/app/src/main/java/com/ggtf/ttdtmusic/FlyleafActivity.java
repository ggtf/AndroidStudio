package com.ggtf.ttdtmusic;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.view.menu.ListMenuItemView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.ggtf.ttdtmusic.database.MusicSQLiteHelper;

public class FlyleafActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flyleaf);
//        putDataInSQLite();
//        updateDataInSQLite();
//        deleteDataInSQLite();
//        getDataInSQLite();
        startMainActivity();

    }

    /**
     * 更新数据库中的数据
     */
    private void updateDataInSQLite() {
        /**
         * 更新数据库中的数据
         */
        MusicSQLiteHelper helper = new MusicSQLiteHelper(this);
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "Orange");
        int update = writableDatabase.update("MUSIC_PLAY", values, "id=?", new String[]{"2"});
        if (update!=0){
            Log.i("Info","更新成功! update = "+update);
        }
        else {
            Log.i("Info","更新失败!");
        }
        writableDatabase.close();

    }

    private void deleteDataInSQLite() {
        /**
         * 删除某一条记录
         */
        MusicSQLiteHelper helper = new MusicSQLiteHelper(this);
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
//        int delete = writableDatabase.delete("MUSIC_PLAY", "id>?", new String[]{"1"});
        int delete = writableDatabase.delete("MUSIC_PLAY", "1", null);
        if (delete != 0){
            Log.i("Info","删除成功!删除了"+delete+"条");
        }else {
            Log.i("Info", "删除失败!");
        }
            writableDatabase.close();
    }

    /**
     * 获取数据库中数据
     */
    private void getDataInSQLite() {
        /**
         * 从数据库中读取数据
         */
        MusicSQLiteHelper helper = new MusicSQLiteHelper(this);
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        Cursor cursor = readableDatabase.query("MUSIC_PLAY", new String[]{"id", "name"}, null, null, null, null, "id DESC");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getColumnIndex("id");
                if (id != -1) {
                    Log.i("Info", "id = " + cursor.getLong(id));
                }
                int name = cursor.getColumnIndex("name");
                if (name != -1) {
                    Log.i("Info", "name = " + cursor.getString(name));
                }
            }
            cursor.close();
        }

    }

    /**
     * 加载扉页时,想获取数据库数据(数据库不存在就先创建数据库)
     */
    private void putDataInSQLite() {
        /**
         * 获取或者打开数据库
         */
        MusicSQLiteHelper helper = new MusicSQLiteHelper(this);
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
            Log.i("Info", "插入数据成功!插入在第"+insert+"行");
        }else {
            Log.i("Info", "插入数据失败! ");
            writableDatabase.setTransactionSuccessful();
        }
        writableDatabase.endTransaction();
        writableDatabase.close();
    }

    private void startMainActivity() {
        final Intent intent = new Intent(this, MainActivity.class);
        new Thread() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
                finish();
            }
        }.start();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int position = info.position;

        super.onCreateContextMenu(menu, v, menuInfo);
    }

}
