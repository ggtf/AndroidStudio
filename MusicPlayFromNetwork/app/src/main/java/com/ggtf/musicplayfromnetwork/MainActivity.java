package com.ggtf.musicplayfromnetwork;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnBufferingUpdateListener {

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = new MediaPlayer();
//        String url ="http://lx.cdn.baidupcs.com/file/988eea1b935346d989c190aaea897d3e?bkt=p2-qd-296&xcode=8ccef7fa49ee08108a13b5f58388fcfeecf1544edc4e7a088bb5ee938cac2427&fid=961744723-250528-339200881633240&time=1445696918&sign=FDTAXGERLBH-DCb740ccc5511e5e8fedcff06b081203-T3%2BwYeOmni2U9xA5OvlDy8unwaU%3D&to=lc&fm=Nan,B,T,ny&sta_dx=3&sta_cs=5550&sta_ft=mp3&sta_ct=5&fm2=Nanjing02,B,T,ny&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=1400988eea1b935346d989c190aaea897d3e314d3047000000322e03&sl=75432014&expires=8h&rt=sh&r=763692880&mlogid=6886797006637619998&vuk=-&vbdid=3899111302&fin=%E5%8D%81%E5%B9%B4.mp3&fn=%E5%8D%81%E5%B9%B4.mp3&slt=pm&uta=0&rtype=1&iv=0&isw=0&dp-logid=6886797006637619998&dp-callid=0.1.1";
        String url = "http://lx.cdn.baidupcs.com/file/7d749c9333c2019bdc45166bd2c1856f?bkt=p2-qd-233&xcode=e1bfceaff8ca69587b35eeb029744bf823882305a9b0f501ed03e924080ece4b&fid=4233346133-250528-1109136089209162&time=1445786417&sign=FDTAXGERLBH-DCb740ccc5511e5e8fedcff06b081203-qLiZYe3McsLO7ZoFUDyC5zqx9Xs%3D&to=lc&fm=Nan,B,T,ny&sta_dx=4&sta_cs=285&sta_ft=mp3&sta_ct=6&fm2=Nanjing,B,T,ny&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=14007d749c9333c2019bdc45166bd2c1856f98d41c4300000040eae4&sl=74186830&expires=8h&rt=sh&r=523286223&mlogid=6910821871198825255&vuk=-&vbdid=880235993&fin=%E5%9B%A0%E4%BD%A0%E8%80%8C%E5%9C%A8.mp3&fn=%E5%9B%A0%E4%BD%A0%E8%80%8C%E5%9C%A8.mp3&slt=pm&uta=0&rtype=1&iv=0&isw=0&dp-logid=6910821871198825255&dp-callid=0.1.1";
        try {
//            设置从网络播放的数据源,直接传递网络地址
            player.setDataSource(url);
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.setOnBufferingUpdateListener(this);


    }


    /**
     * 设置加载网络数据的音频文件时,从setDataSource()之后的prepare()之后就开始缓冲更新;
     * @param mp
     * @param percent
     */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
//        player.start();
//        发送更新进度的消息
        Log.i("Info","percent = "+percent);
    }

    /**
     * 播放完成的回调方法
     *
     * @param mp
     */
//    @Override
   /* public void onCompletion(MediaPlayer mp) {
        if (manager.downloadPos == manager.lastEnddPos) {

        } else {
//            接着后面开始播放
            playMusic(manager.lastEnddPos, manager.downloadPos - manager.lastEnddPos);
        }
    }*/

    /**
     * 设置播放器从某个点开始播放,播放指定长度
     *
     * @param startPos
     * @param length
     */
   /* public void playMusic(long startPos, long length) {
        player.release();
        player.reset();
        try {
//            设置从文件的某个位置开始播放,播放多长时间
            player.setDataSource(manager.file.getFD(), startPos, length);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    /**
     * 点击按钮播放音乐
     * @param view
     */
    public void playMusic(View view) {
        if (view instanceof Button){
            Button musicPlay= (Button) view;
            if (player.isPlaying()){
                musicPlay.setText("播放");
                player.pause();
            }else {
                musicPlay.setText("暂停");
                player.start();
            }
        }

    }


    /**
     * 下载管理器
     */
    /*public class SoundDownloadManager {
        public static final long BLOCK_SIZE = 3 * 1024;
        public String url;
        //        进行文件的存储
        public RandomAccessFile file;
        //        通知有新的数据下载下来
        public Handler handler;
        //        是否下载完成
        public boolean finished = false;
        //        当前正在使用的下载线程
        public Downloader thread;
        //        当前下载完的数据的位置指针
        public long downloadPos = 0;
        //        上次播放截至的时间位置
        public long lastEnddPos = 0;

        public void download() {
//            判断是否全部下载
            try {
                if (downloadPos >= file.length()) {
//                    通知下载完毕
                    return;
                } else {
                    thread = new Downloader();
                    thread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //        下载数据到达的时候触发;
        public void onDataArrived() {
//            检查当前播放器的状态
            *//**
             * 如果播放器处于等待状态,播放器播放新的内容
             *//*
            if (!player.isPlaying()) {
//                从上次播放完的位置开始,播放到当前位置
                playMusic(lastEnddPos, downloadPos - lastEnddPos);
            }
        }

    }

    public class Downloader extends Thread {
        public Handler handler;

        @Override
        public void run() {
            try {
                URL url = new URL(manager.url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("RANGE", "byte=" + manager.lastEnddPos + "-" + SoundDownloadManager.BLOCK_SIZE);
                InputStream in = conn.getInputStream();
                byte[] buffer = new byte[in.available()];
                in.read(buffer);

                manager.file.seek(manager.lastEnddPos);
                manager.file.write(buffer);
                manager.downloadPos = manager.file.getFilePointer();
//                触发onArrived
                manager.onDataArrived();
//                下载下一段数据
                manager.download();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
}
