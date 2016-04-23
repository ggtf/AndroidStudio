package com.ggtf.ttdtmusic.thread;

import android.os.Handler;

import com.ggtf.ttdtmusic.cache.FileCache;
import com.ggtf.ttdtmusic.http.HttpUtils;
import com.ggtf.ttdtmusic.tools.Constant;
import com.ggtf.ttdtmusic.tools.MusicNetworkUrl;

import java.io.IOException;

/**
 * Created by ggtf at 2015/10/24
 * Author:ggtf
 * Time:2015/10/24
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class DownloadInSDCacheFile {
    private DownloadInSDCacheFile(){

    }
    public static void downloadMusic(final String musicUrl, final String musicName, final Handler mainHandler){
        new Thread() {
            @Override
            public void run() {
                    try {
                        byte[] data = HttpUtils.getNetworkData(musicUrl);
                        if (data != null) {
                            boolean isCache = FileCache.toSDCache(data, musicName);
                            if (isCache) {
                                mainHandler.sendEmptyMessage(Constant.CACHE_SUCCESSFUL);
                            } else {
                                mainHandler.sendEmptyMessage(Constant.CACHE_UNSUCCESSFUL);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }.start();
    }
}
