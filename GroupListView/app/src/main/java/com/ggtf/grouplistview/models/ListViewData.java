package com.ggtf.grouplistview.models;

import android.view.View;

import com.ggtf.grouplistview.tools.ConstantViewType;

/**
 * Created by ggtf at 2015/10/17
 * Author:ggtf
 * Time:2015/10/17
 * Email:15170069952@163.com
 * ProjectName:GroupListView
 */
public class ListViewData {
//    /**
//     * 附带一个Groups对象,用于标识当前视图数据的某一种状态(这里标识Item的视图)
//     */
//    private Groups groups;

    /**
     * 指定固定属性,来标识当前Item数据的视图状态
     */
//    是否被分组
    private boolean isGrouped;
//    分组ID
    private int groupId;
//    当前Item的视图
    private int viewType;
//    是否被选中
    private boolean isChecked;
//    是否被点击展开
    private boolean isExpanding;
//    CheckBox是否显示
    private boolean  isCheckable;


    /**
     * Item的内容数据
     */
    private String content;


    public ListViewData() {
//        -1    代表未被分组
        groupId = -1;
        isChecked = false;
        isGrouped = false;
        isExpanding = false;
        isCheckable = false;
        viewType = ConstantViewType.CONTACT_VIEW_TYPE;
    }

    public boolean isCheckable() {
        return isCheckable;
    }

    public void setIsCheckable(boolean isCheckable) {
        this.isCheckable = isCheckable;
    }

    public boolean isExpanding() {
        return isExpanding;
    }

    public void setIsExpanding(boolean isExpanding) {
        this.isExpanding = isExpanding;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isGrouped() {
        return isGrouped;
    }

    public void setIsGrouped(boolean isGrouped) {
        this.isGrouped = isGrouped;
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
