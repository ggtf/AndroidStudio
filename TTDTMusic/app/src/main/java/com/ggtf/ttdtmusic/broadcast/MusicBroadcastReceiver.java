package com.ggtf.ttdtmusic.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.ggtf.ttdtmusic.MainActivity;
import com.ggtf.ttdtmusic.entities.NotificationState;
import com.ggtf.ttdtmusic.service.MusicService;
import com.ggtf.ttdtmusic.tools.Constant;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by ggtf at 2015/10/25
 * Author:ggtf
 * Time:2015/10/25
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class MusicBroadcastReceiver extends BroadcastReceiver {

    @Override
    public IBinder peekService(Context myContext, Intent service) {
        return super.peekService(myContext, service);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Log.i("Info", "收到了广播");
            Log.i("Info", intent.getAction());
            int value = intent.getIntExtra("notification", -1);
            Log.i("Info", "" + value);
            if (value != -1) {
                Intent service = new Intent(context, MusicService.class);
                service.putExtra("broadcast", value);
                context.startService(service);
            }


        }
    }
}
