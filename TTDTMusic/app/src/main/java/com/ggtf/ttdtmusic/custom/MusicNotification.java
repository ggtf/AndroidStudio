package com.ggtf.ttdtmusic.custom;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.widget.RemoteViews;

import com.ggtf.ttdtmusic.MainActivity;
import com.ggtf.ttdtmusic.R;
import com.ggtf.ttdtmusic.entities.NotificationState;
import com.ggtf.ttdtmusic.tools.Constant;

/**
 * Created by ggtf at 2015/10/25
 * Author:ggtf
 * Time:2015/10/25
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class MusicNotification{
    private Notification.Builder builder;
    private NotificationManager manager;
    private RemoteViews remoteViews;

    public MusicNotification(Context context) {
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new Notification.Builder(context);
        initNotificationView(context);

    }

    private void initNotificationView(Context context) {
        /**
         * 点击Notification进入Activity,通知不会消失
         */
        builder.setAutoCancel(false);
        /**
         * 用户无法在通知栏中通过滑动来移除通知,必须由应用程序来移除
         */
        builder.setOngoing(true);
        builder.setTicker("");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setWhen(System.currentTimeMillis());
        RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.notification_layout);
        builder.setContent(views);
//        int layoutId = remoteViews.getLayoutId();
        remoteViews = views;
//        builder.setPriority(Notification.PRIORITY_MIN);
        /**
         * 点击Notification中视图的按钮就发送广播;
         */
//        Intent intent = new Intent(Constant.MUSIC_BROADCAST_ACTION);
//        intent.putExtra("notification",100);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,Constant.NOTIFICATION_PLAY,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//        views.setOnClickPendingIntent(R.id.notification_play,pendingIntent);

        views.setOnClickPendingIntent(R.id.notification_play,setNotificationIntent(context,Constant.NOTIFICATION_PLAY));
        views.setOnClickPendingIntent(R.id.notification_pre,setNotificationIntent(context,Constant.NOTIFICATION_PRE));
        views.setOnClickPendingIntent(R.id.notification_next,setNotificationIntent(context,Constant.NOTIFICATION_NEXT));
        views.setOnClickPendingIntent(R.id.notification_delete,setNotificationIntent(context,Constant.NOTIFICATION_DELETE));
        /**
         *点击Notification触发的事件监听
         */
        Intent intentToStartActivity = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, Constant.NOTIFICATION_TO_START_ACTIVITY, intentToStartActivity, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);



    }
    public void sendNotification(){
        /**
         * 使用bigContentView来解决Notification布局的高度不能适应定义的高度问题
         */
        Notification notification = builder.getNotification();
        notification.bigContentView=remoteViews;
        manager.notify(Constant.NOTIFICATION_STATE,notification);
    }

    public RemoteViews getRemoteViews() {
        return remoteViews;
    }

    public NotificationManager getManager() {
        return manager;
    }

    /**
     * 发送带数据的Broadcast广播
     * @param context
     * @param value
     * @return
     */
    private PendingIntent setNotificationIntent(Context context,int value){
        Intent intent = new Intent(Constant.MUSIC_BROADCAST_ACTION);
        intent.putExtra("notification", value);
        /**
         * 参数2:请求码
         * 请求码要不一样,要不会被覆盖之前的PendingIntent
         */
        return PendingIntent.getBroadcast(context,value,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
