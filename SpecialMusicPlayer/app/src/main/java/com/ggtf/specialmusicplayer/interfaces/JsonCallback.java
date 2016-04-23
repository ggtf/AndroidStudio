package com.ggtf.specialmusicplayer.interfaces;

import org.json.JSONObject;

/**
 * Created by ggtf at 2016/4/11
 * Author:ggtf
 * Time:2016/4/11
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */
public interface JsonCallback {
    void jsonParseOk(JSONObject json);

    void jsonParseFailed(JSONObject json);

    void dealException(Throwable ex);
}
