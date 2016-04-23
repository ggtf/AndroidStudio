package com.ggtf.specialmusicplayer.animations;

import android.content.Context;

/**
 * Created by ggtf at 2016/4/11
 * Author:ggtf
 * Time:2016/4/11
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */
public class BasisAnimation {
    private Context context;

    private BasisAnimation(Context context) {
        this.context = context;
    }

    private BasisAnimation INSTANCE;

    public synchronized BasisAnimation getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new BasisAnimation(context);
        }
        return INSTANCE;
    }


}
