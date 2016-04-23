package com.ggtf.specialmusicplayer.tools;

import com.google.gson.Gson;

/**
 * Created by ggtf at 2016/4/11
 * Author:ggtf
 * Time:2016/4/11
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */
public class BeJson {

    private Gson gson;

    private BeJson() {
        gson = new Gson();
    }

    private static BeJson INSTANCE = new BeJson();

    public static BeJson getInstance() {
        return INSTANCE;
    }

    public String getJsonByObject(Object src) {
        return gson.toJson(src);
    }

    public <T> T getObjectByJson(String json, Class<T> t) {
        return gson.fromJson(json, t);
    }

}
