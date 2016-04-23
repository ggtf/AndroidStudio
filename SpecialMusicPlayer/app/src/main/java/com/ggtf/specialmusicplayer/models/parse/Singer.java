package com.ggtf.specialmusicplayer.models.parse;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggtf at 2016/4/11
 * Author:ggtf
 * Time:2016/4/11
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */
public class Singer {
    private String name;
    private boolean sex;
    private int age;
    private String hometown;
    private int constellation;
    private String bloodType;
    private List<Song> typicalSongs;


    public Singer(String name, boolean sex, int age, String hometown, int constellation, String bloodType, List<Song> typicalSongs) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.hometown = hometown;
        this.constellation = constellation;
        this.bloodType = bloodType;
        this.typicalSongs = typicalSongs;
    }

    public boolean isSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getHometown() {
        return hometown;
    }

    public int getConstellation() {
        return constellation;
    }

    public String getBloodType() {
        return bloodType;
    }

    public List<Song> getTypicalSongs() {
        return typicalSongs;
    }
}
