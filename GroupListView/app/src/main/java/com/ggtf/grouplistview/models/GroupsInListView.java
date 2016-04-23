package com.ggtf.grouplistview.models;

import android.view.View;


/**
 * Created by ggtf at 2015/10/9
 * Author:ggtf
 * Time:2015/10/9
 * Email:15170069952@163.com
 * ProjectName:GroupListView
 */
public class GroupsInListView {
    public static final int VIEW_GROUP_TYPE = 0;
    public static final int VIEW_GROUP_ITEM_TYPE = 1;
//    组名
    private String groupName;
//    item所在的组的ID
    private int groupId;
//    是否被分组
    private boolean isGrouped;
//    显示的视图类型
    private int viewType;

    public GroupsInListView() {
//        默认分组ID-1,代表未被分组
        groupId = -1;
        viewType = VIEW_GROUP_TYPE;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public boolean isGrouped() {
        return isGrouped;
    }

    public void setIsGrouped(boolean isGrouped) {
        this.isGrouped = isGrouped;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
