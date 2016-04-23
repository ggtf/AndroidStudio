package com.ggtf.mytime.entities;

/**
 * Created by ggtf at 2015/10/29
 * Author:ggtf
 * Time:2015/10/29
 * Email:15170069952@163.com
 * ProjectName:MyTime
 */
public class Reminds {
    /**
     * 日期天数;天数文本颜色;天数背景颜色
     */
    private String day;
    private int dayTextColor;
    private int dayBg;

    /**
     * 记录文本;记录文本颜色;记录背景颜色
     */
    private String thing;
    private int thingTextColor;
    private int thingBg;
    /**
     * 最底层的颜色
     */
    private int previousBg;

    public int getDayTextColor() {
        return dayTextColor;
    }

    public void setDayTextColor(int dayTextColor) {
        this.dayTextColor = dayTextColor;
    }

    public int getThingTextColor() {
        return thingTextColor;
    }

    public void setThingTextColor(int thingTextColor) {
        this.thingTextColor = thingTextColor;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getDayBg() {
        return dayBg;
    }

    public void setDayBg(int dayBg) {
        this.dayBg = dayBg;
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public int getThingBg() {
        return thingBg;
    }

    public void setThingBg(int thingBg) {
        this.thingBg = thingBg;
    }

    public int getPreviousBg() {
        return previousBg;
    }

    public void setPreviousBg(int previousBg) {
        this.previousBg = previousBg;
    }
}
