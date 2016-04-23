package com.ggtf.ttdtmusic.entities;

/**
 * Created by ggtf at 2015/10/27
 * Author:ggtf
 * Time:2015/10/27
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class MyListViewItem {
    private int iconRes;
    private String menuName;
    private String musicNum;

    public MyListViewItem(int iconRes, String menuName, String musicNum) {
        this.iconRes = iconRes;
        this.menuName = menuName;
        this.musicNum = musicNum;
    }

    public int getIconRes() {
        return iconRes;
    }

    public String getMusicNum() {
        return musicNum;
    }

    public String getMenuName() {
        return menuName;
    }
}
