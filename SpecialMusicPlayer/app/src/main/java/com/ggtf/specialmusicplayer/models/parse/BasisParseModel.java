package com.ggtf.specialmusicplayer.models.parse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ggtf at 2016/4/11
 * Author:ggtf
 * Time:2016/4/11
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */
public abstract class BasisParseModel {

    public BasisParseModel(JSONObject json) throws JSONException{
        initAttrsByJson(json);
    }

    protected abstract void initAttrsByJson(JSONObject json)throws JSONException;
}
