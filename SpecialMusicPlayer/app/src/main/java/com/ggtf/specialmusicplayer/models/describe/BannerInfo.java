package com.ggtf.specialmusicplayer.models.describe;

/**
 * Created by ggtf at 2016/4/21
 * Author:ggtf
 * Time:2016/4/21
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */

import com.ggtf.specialmusicplayer.models.parse.BasisParseModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Banner的描述信息
 */
public class BannerInfo extends BasisDescribeModel {
    private String url;
    private String text;
    private int type;

    public BannerInfo(JSONObject json) throws JSONException {
        super(json);
    }

    @Override
    public String toJsonFormat() throws JSONException {
        return null;
    }

    @Override
    protected void initAttrsByJson(JSONObject json) throws JSONException {
        if (json != null) {
            url = json.getString("url");
            text = json.getString("text");
            type = json.getInt("type");
        }
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    public int getType() {
        return type;
    }
}
