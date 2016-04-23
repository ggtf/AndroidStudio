package com.ggtf.ttdtmusic.tools;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by ggtf at 2015/10/24
 * Author:ggtf
 * Time:2015/10/24
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class Tools {
    /**
     * 私有构造方法,外部类无法创建该类的实例
     */
    private Tools() {

    }

    /**
     * 判断是否有SD卡
     * @return boolean, false表示无法访问SD卡, true表示可以访问SD卡
     */
    public static boolean isHadSDCard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    /**
     * 获取某个文件夹下以postfix为后缀的文件个数
     * @param directory 文件夹
     * @param postfix 后缀名
     * @return 文件个数
     */
    public static int hasFilesNum(File directory, String postfix) {
        int fileNum = 0;
        if (directory.isDirectory()) {
            String[] list = directory.list();
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    if (list[i].endsWith(postfix)) {
                        fileNum++;
                    }
                }
            }
        }
        return fileNum;
    }

    /**
     * 获取某个文件夹下以postfix为后缀的文件名数组
     * @param directory 文件夹
     * @param postfix 文件后缀名
     * @return 文件名数组
     */
    public static String[] hasFilesNameList(File directory, final String postfix) {
        String[] fileNames = null;
        if (hasFilesNum(directory, postfix) > 0) {
            fileNames = directory.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String filename) {
                    return filename.endsWith(postfix);
                }
            });
        }
        return fileNames;
    }

    /**
     * 获取某个文件夹下以postfix为后缀的文件的绝对路径数组
     * @param directory 文件夹
     * @param postfix   文件后缀名
     * @return 文件绝对路径名数组
     */
    public static String[] hasFilesPathList(File directory, final String postfix){
        String[] filePaths = null;
        if (hasFilesNum(directory,postfix)>0){
            File[] files = directory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String filename) {
                    return filename.endsWith(postfix);
                }
            });
            if (files!=null && files.length>0){
                filePaths = new String [files.length];
                for (int i = 0; i < files.length; i++) {
                    filePaths[i] = files[i].getAbsolutePath();
                }
            }
        }

        return filePaths;
    }

    /**
     * 依据给定的Uri和检索条件来获取数据库中的数据,返回Cursor对象
     * @param context 上下文对象
     * @param uri uri
     * @param projection 查询哪几列
     * @param selection 查询条件?--
     * @param selectionArg 查询参数
     * @param sortOrder 指定查询结果的排序;
     * @return 返回包含查询数据的cursor对象
     */
    private static Cursor getSQLiteData(Context context,Uri uri,String[] projection,String selection,String[] selectionArg,String sortOrder){
        ContentResolver resolver = context.getContentResolver();

        return resolver.query(uri,projection,selection,selectionArg,sortOrder);

    }

    /**
     * 获取SDCard的视频文件
     * @param context
     * @param selection
     * @param selectionArg
     * @return
     */
    public static String[] getSDCardVideoPath(Context context,String selection,String[] selectionArg){
        String [] videoPaths=null;
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String sortOrder = MediaStore.Video.Media.DEFAULT_SORT_ORDER;
        String[] projection = new String[]{MediaStore.Video.Media.DATA};
        Cursor cursor = getSQLiteData(context, uri, projection, selection, selectionArg, sortOrder);
        if (cursor!=null){
            cursor.moveToFirst();
            int fileNum = cursor.getCount();
            videoPaths = new String [fileNum];
            for (int i = 0; i < fileNum; i++) {
//                videoPaths[i] = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE));
                videoPaths[i] = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return videoPaths;
    }

    /**
     * 获取SDCard的音频文件
     * @param context
     * @param selection
     * @param selectionArg
     * @return
     */
    public static String [] getSDCardAudioPath(Context context,String selection,String[] selectionArg){
        String[] audioPaths = null;
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String sortOrder = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;
        String[] projection = new String[]{MediaStore.Video.Media.DATA};
        Cursor cursor = getSQLiteData(context, uri, projection, selection, selectionArg, sortOrder);
        if (cursor!=null){
            cursor.moveToFirst();
            int fileNum = cursor.getCount();
            audioPaths = new String [fileNum];
            for (int i = 0; i < fileNum; i++) {
                audioPaths[i] = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return audioPaths;
    }

    /**
     * 获取SDCard中的图像文件
     * @param context
     * @param selection
     * @param selectionArg
     * @return
     */
    public static String[] getSDCardImagePath(Context context,String selection,String[] selectionArg){
        String[] imagePaths = null;
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String sortOrder = MediaStore.Images.Media.DEFAULT_SORT_ORDER;
        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = getSQLiteData(context, uri, projection, selection, selectionArg, sortOrder);
        if (cursor!=null){
            cursor.moveToFirst();
            int fileNum = cursor.getCount();
            imagePaths = new String [fileNum];
            for (int i = 0; i < fileNum; i++) {
                imagePaths[i] = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return imagePaths;
    }

}
