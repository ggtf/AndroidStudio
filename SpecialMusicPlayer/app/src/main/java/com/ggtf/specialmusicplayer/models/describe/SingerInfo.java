package com.ggtf.specialmusicplayer.models.describe;

import com.ggtf.specialmusicplayer.models.parse.Singer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ggtf at 2016/4/24
 * Author:ggtf
 * Time:2016/4/24
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */
public class SingerInfo extends BasisDescribeModel {
    private String headerUrl;
    private Singer singer;
    private int songsNum;
    private int type = 1;

    public SingerInfo(JSONObject json) throws JSONException {
        super(json);
    }

    @Override
    public String toJsonFormat() throws JSONException {
        return null;
    }

    @Override
    protected void initAttrsByJson(JSONObject json) throws JSONException {

    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public Singer getSinger() {
        return singer;
    }

    public int getSongsNum() {
        return songsNum;
    }

    public int getType() {
        return type;
    }
}
