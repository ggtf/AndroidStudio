package com.ggtf.ttdtmusic.cache;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.ggtf.ttdtmusic.tools.Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ggtf at 2015/10/24
 * Author:ggtf
 * Time:2015/10/24
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class FileCache {
    private FileCache() {

    }

    public static boolean toSDCache(byte[] data, String fileName) {
        if (Tools.isHadSDCard() && data != null) {
            File sdDirectory = Environment.getExternalStorageDirectory();
            File cacheDirectory = new File(sdDirectory, "Cache");
            if (!cacheDirectory.exists()) {
               cacheDirectory.mkdirs();
            }
            if (cacheDirectory.isDirectory()){
                File cache = new File(cacheDirectory, fileName);
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(cache);
                    fos.write(data, 0, data.length);
                    return true;
                } catch (IOException e) {
                    Log.i("Info", "写入SD卡缓存失败");
                    e.printStackTrace();
                    return false;
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return false;
    }

    public static boolean toApplicationCache(Context context,byte[] data,String fileName){
        File cacheDir = context.getCacheDir();
        File cacheFile = new File(cacheDir,fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(cacheFile);
            fos.write(data,0,data.length);
            return true;
        } catch (IOException e) {
            Log.i("Info","写入内部存储卡缓存失败");
            e.printStackTrace();
            return false;
        }finally {
            if (fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
