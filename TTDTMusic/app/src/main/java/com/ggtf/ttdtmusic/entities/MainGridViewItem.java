package com.ggtf.ttdtmusic.entities;

/**
 * Created by ggtf at 2015/10/26
 * Author:ggtf
 * Time:2015/10/26
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class MainGridViewItem {
    private int resId;
    private String title;
    private String content;

    public MainGridViewItem(int resId, String title, String content) {
        this.resId = resId;
        this.title = title;
        this.content = content;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
