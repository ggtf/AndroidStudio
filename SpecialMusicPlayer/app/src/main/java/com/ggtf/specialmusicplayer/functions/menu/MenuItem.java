package com.ggtf.specialmusicplayer.functions.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.ggtf.specialmusicplayer.tools.ContentsValue;

/**
 * Created by ggtf at 2016/4/16
 * Author:ggtf
 * Time:2016/4/16
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 * 菜单模块中的功能项的描述实体类类
 */
public class MenuItem {
    private int type;/*item 的type 类型*/
    private String label;/*item 的文本描述*/
    private int tvColor;/*文本颜色*/
    private int bgColor;/*背景颜色*/
    private int anim;/*进入动画*/

    public MenuItem(int type, String label) {
        this.type = type;
        this.label = label;
        tvColor = ContentsValue.TV_COLOR_DEFAULT;
        bgColor = ContentsValue.BG_COLOR_DEFAULT;
        anim = ContentsValue.ANIM;
    }

    public int getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public void setTvColor(int tvColor) {
        this.tvColor = tvColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public void setAnim(int anim) {
        this.anim = anim;
    }

    public void setTvColor(TextView tv) {
        tv.setTextColor(tvColor);
    }

    public void setBgColor(ViewGroup view) {
        view.setBackgroundColor(bgColor);
    }

    public void startAnimation(Context context, ViewGroup view) {
        Animation animRender = AnimationUtils.loadAnimation(context, anim);
        view.startAnimation(animRender);
    }

}
