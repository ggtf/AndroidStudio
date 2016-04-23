package com.ggtf.specialmusicplayer.models.parse;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by ggtf at 2016/4/11
 * Author:ggtf
 * Time:2016/4/11
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */
public class Song {
    private String name;
    private List<String> singers;
    private List<String> composers;
    private List<String> songWriters;
    private long duration;
    private List<String> categories;
    private String times;

    public Song(String name, List<String> singers, List<String> composers, List<String> songWriters, long duration, List<String> categories, String times) {
        this.name = name;
        this.singers = singers;
        this.composers = composers;
        this.songWriters = songWriters;
        this.duration = duration;
        this.categories = categories;
        this.times = times;
    }
}
