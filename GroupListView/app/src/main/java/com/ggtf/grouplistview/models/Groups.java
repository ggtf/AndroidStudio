package com.ggtf.grouplistview.models;

/**
 * Created by ggtf at 2015/10/17
 * Author:ggtf
 * Time:2015/10/17
 * Email:15170069952@163.com
 * ProjectName:GroupListView
 */
public class Groups {

    /**
     * 被分组的组Id, -1代表未被分组
     */
    private int groupId;
    /**
     * 当前Item所在视图的类型
     */
    private int viewType;
    /**
     * 是否被分组
     */
    private boolean isGrouped;

    public Groups() {
        groupId = -1;
        isGrouped = false;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public boolean isGrouped() {
        return isGrouped;
    }

    public void setIsGrouped(boolean isGrouped) {
        this.isGrouped = isGrouped;
    }
}
