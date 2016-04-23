package com.ggtf.ttdtmusic.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.*;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ggtf.ttdtmusic.R;
import com.ggtf.ttdtmusic.custom.MusicNotification;
import com.ggtf.ttdtmusic.tools.Constant;
import com.ggtf.ttdtmusic.tools.Tools;

import java.io.File;
import java.io.IOException;

/**
 * Created by ggtf at 2015/10/24
 * Author:ggtf
 * Time:2015/10/24
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class MusicService extends Service implements Runnable {
    /**
     * 常量定义
     */
    public static final int MUSIC_PROGRESS = 2000;
    public static final int[] RES_MUSIC = {R.raw.duanqiaocanxue, R.raw.bushiyinweijimocaixingni};


    //    播放器
    private MediaPlayer player;
    //    Activity的handler
    private Handler mainHandler;
    //    向主线程传递信息,用于更新UI的子线程
    private Thread deliveryThread;
    //    当前音乐是否在播放
    private boolean isRunning;
    //    线程是否该被销毁
    private boolean isDestroy;
    //    当前音乐是否执行完
    private boolean isFinishPlay;
    //    文件系统中音乐文件路径
    private String[] musicFiles;
    //    文件系统中文件音乐的数量
    private int musicNum;
    //    当前播放的是列表中的哪一个音乐
    private int currentPlaying;
//    通知栏
    private MusicNotification musicNotification;
//    播放控制器
    private Controller controller;
    private boolean unbind;

    /**
     * 子线程实现;用于发送信息给UI线程
     */
    @Override
    public void run() {
        while (true) {
            if (isDestroy) {
                break;
            }
            /**
             * 每隔一秒发送一次消息
             */
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (mainHandler != null && player != null) {
                synchronized (MusicService.class) {
                    Message progress = mainHandler.obtainMessage(MUSIC_PROGRESS);
                    progress.obj = player.isPlaying();
                    progress.arg1 = player.getCurrentPosition();
                    progress.arg2 = player.getDuration();
                    mainHandler.sendMessage(progress);
                }
            }
        }

    }


    /**
     * 内部类;用于进行Activity对服务的内部访问
     */
    public class Controller extends Binder {
        //        播放音乐
        public void play() {
            if (!player.isPlaying()) {
                player.start();
                Message message = mainHandler.obtainMessage(Constant.MUSIC_CURRENT_POSITION);
                message.arg1=currentPlaying;
                mainHandler.sendMessage(message);
//                音乐首次播放时启动子线程
                if (!isRunning) {
                    deliveryThread.start();
                    isRunning = true;
                }
            }

        }

        //        暂停音乐
        public void pause() {
            if (player.isPlaying()) {
                player.pause();
            }

        }

        //        快进5秒
        public void forward() {
            int pos = player.getCurrentPosition() + 5000;
            seekTo(pos);

        }

        //        后退5秒
        public void backward() {
            int pos = player.getCurrentPosition() - 5000;
            seekTo(pos);

        }

        //        指定播放位置
        private void seekTo(int pos) {
//            音乐的时长,毫秒单位
            int musicLength = player.getDuration();
//            快进到音乐最后时,音乐直接进入到下一首
            if (pos > musicLength) {
                pos = musicLength;
                isFinishPlay = true;
            } else if (pos < 0) {
                pos = 0;
            }
            player.seekTo(pos);

        }

        //        上一首
        public void previousMusic(int currentPos) {
            playMusic(currentPos - 1);
        }

        //        下一首
        public void nextMusic(int currentPos) {
            playMusic(currentPos + 1);

        }

        //        播放指定位置音乐
        private void playMusic(int pos) {
            if (pos < musicNum && pos >= 0) {
                player.reset();
//                player = MediaPlayer.create(MusicService.this, RES_MUSIC[pos]);
                try {
                    player.setDataSource(musicFiles[pos]);
                    player.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.start();
            }
        }
//        返回音乐列表中音乐的总数
        public int getMusicNum(){
            return musicNum;
        }
//        设置当前播放的音乐是第几首;
        public void setCurrentMusic(int currentMusic){
            currentPlaying = currentMusic;
        }

        public void setMainHandler(Handler handler) {
            mainHandler = handler;
        }

    }

    /**
     * 使用bindService绑定服务,会调用该方法;
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        controller = new Controller();
        return controller;
    }

    /**
     * Service首次启动时会调用,用于初始化数据
     */
    @Override
    public void onCreate() {
        super.onCreate();
//        播放本地raw文件目录下的音乐
//        player = MediaPlayer.create(this, RES_MUSIC[0]);
//        播放文件系统中的音乐文件
        player = new MediaPlayer();
        if (Tools.isHadSDCard()) {
            File sdDirectory = Environment.getExternalStorageDirectory();
            File cache = new File(sdDirectory, "Cache");
            if (cache.isDirectory()) {
                musicFiles = Tools.hasFilesPathList(cache, ".mp3");
                if (musicFiles != null) {
                    musicNum = musicFiles.length;
                    try {
                        player.setDataSource(musicFiles[currentPlaying]);
                        player.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

//        初始化线程
        deliveryThread = new Thread(this);
        musicNotification = new MusicNotification(this);
        musicNotification.sendNotification();

    }

    /**
     * 使用startService启动服务时,会回调这个方法
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent!=null){
            boolean closeMusicNotification = intent.getBooleanExtra("closeMusicNotification",false);
            if (closeMusicNotification){
                musicNotification.getManager().cancel(Constant.NOTIFICATION_STATE);
                mainHandler.sendEmptyMessage(Constant.ACTIVITY_FINISH);
                stopSelf();
            }

            int broadcast = intent.getIntExtra("broadcast",-1);
            Log.i("Info",""+broadcast);
            if (broadcast!=-1){
                Message message = mainHandler.obtainMessage(Constant.MUSIC_CURRENT_POSITION);
                switch (broadcast){
                    case Constant.NOTIFICATION_PLAY:
//                        点击了播放按钮
                        if (player.isPlaying()){
                            player.pause();
                            musicNotification.getRemoteViews().setImageViewResource(R.id.notification_play,R.drawable.play_image_selector);
                        }else {
                            player.start();
                            musicNotification.getRemoteViews().setImageViewResource(R.id.notification_play, R.drawable.pause_image_selector);
                        }
                        mainHandler.sendMessage(message);
                        musicNotification.sendNotification();
                        break;
                    case Constant.NOTIFICATION_NEXT:
//                        点击了下一首按钮
                        controller.nextMusic(currentPlaying);
                        currentPlaying++;
                        if (currentPlaying>=musicNum){
                            currentPlaying=0;
                        }
                        message.arg1=currentPlaying;
                        mainHandler.sendMessage(message);
                        break;
                    case Constant.NOTIFICATION_PRE:
//                        点击了上一首按钮
                        if (currentPlaying>0){
                            controller.previousMusic(currentPlaying);
                            currentPlaying--;
                            message.arg1=currentPlaying;
                            mainHandler.sendMessage(message);
                        }

                        break;
                    case Constant.NOTIFICATION_DELETE:
//                        点击了关闭按钮,关闭通知栏,并且关闭Activity;同时关闭服务;
                        musicNotification.getManager().cancel(Constant.NOTIFICATION_STATE);
                        mainHandler.sendEmptyMessage(Constant.ACTIVITY_FINISH);
                        stopSelf();
                        break;
                }
            }
            int closeNotification = intent.getIntExtra("closeNotification",-1);
            if (closeNotification != -1){
                musicNotification.getManager().cancel(Constant.NOTIFICATION_STATE);
            }
        }else {
            /**
             * 进程意外退出后,服务再次启动,Intent为null,执行关闭通知栏之后关闭服务
             */
            musicNotification.getManager().cancel(Constant.NOTIFICATION_STATE);
            stopSelf();
        }
//        return Service.START_STICKY_COMPATIBILITY;
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player.isPlaying()) {
            player.stop();
        }
        isDestroy = true;
        player.reset();
        player.release();
        player = null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        unbind = true;
//        Log.i("Info","onUnbind");
        return super.onUnbind(intent);

    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onTrimMemory(int level) {
//        if (unbind){
//            Log.i("Info","unbind onTrimMemory");
//        }else {
//            Log.i("Info","onTrimMemory");
//        }
        super.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
//        Log.i("Info","onLowMemory");
        super.onLowMemory();
    }
}
