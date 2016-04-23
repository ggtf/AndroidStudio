package com.ggtf.specialmusicplayer.models.describe;

import com.ggtf.specialmusicplayer.models.parse.BasisParseModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ggtf at 2016/4/24
 * Author:ggtf
 * Time:2016/4/24
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */
public abstract class BasisDescribeModel extends BasisParseModel {
    public BasisDescribeModel(JSONObject json) throws JSONException {
        super(json);
    }

    public abstract String toJsonFormat() throws JSONException;
}
