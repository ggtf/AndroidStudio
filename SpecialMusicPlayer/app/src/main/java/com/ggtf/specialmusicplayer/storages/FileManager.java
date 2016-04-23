package com.ggtf.specialmusicplayer.storages;

/**
 * Created by ggtf at 2016/4/21
 * Author:ggtf
 * Time:2016/4/21
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件管理器
 */
public class FileManager {
    private FileManager() {

    }

    /**
     * 创建ImageCache的缓存目录
     *
     * @param context 上下文
     * @return ImageCacheDir
     */
    public static File createImageCacheDir(Context context) {
        File file = null;
        File filesDir = context.getFilesDir();
        if (filesDir != null && filesDir.isDirectory()) {
            file = new File(filesDir, "imageCache");
            if (file.mkdir() || file.isDirectory()) {
                return file;
            }
        }
        return file;
    }


    /**
     * 创建日志文件
     *
     * @param context 上下文
     * @return logDir
     */
    public static File createLogsFile(Context context) {
        File file = null;
        File dir = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dir = Environment.getExternalStorageDirectory();
        } else {
            dir = context.getFilesDir();
        }
        file = new File(dir, "logs");
        if (file.mkdir() || file.isDirectory()) return file;
        return file;
    }

    /**
     * 在某个目录下创建文件
     *
     * @param fileName 文件名
     * @param dir      文件所在目录
     */
    public static File createFile(String fileName, File dir) {
        File file = null;
        if (dir != null && dir.isDirectory() && fileName != null) {
            file = new File(dir, fileName);
        }
        return file;
    }

    /**
     * 从文件中读取缓存的图片
     *
     * @param fileName 文件名
     * @param dir      所在文件夹
     * @return bitmap
     */
    public static Bitmap getImageFromFile(String fileName, File dir) {
        Bitmap bitmap = null;
        if (dir != null && dir.isDirectory() && fileName != null) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.getName().equals(fileName)) {
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    break;
                }
            }
        }
        return bitmap;
    }

    /**
     * 将数据写入到文件中
     *
     * @param fileName 文件名
     * @param dir      文件所在目录
     * @param bytes    数据字节数组
     */
    public static void write2File(String fileName, File dir, byte[] bytes) {
        File file = createFile(fileName, dir);
        if (file != null) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bytes);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 清除某个文件夹下的缓存文件
     *
     * @param dir 文件夹
     */
    public synchronized static void clearCache(File dir) {
        if (dir != null && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files.length == 0) {
                boolean delete = dir.delete();
                return;
            }
            for (File file : files) {
                if (file.isFile()) {
                    boolean delete = file.delete();
                } else if (file.isDirectory()) {
                    clearCache(file);
                }
            }
        }
    }
}
