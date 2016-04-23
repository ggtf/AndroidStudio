package com.ggtf.ontoucheventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

/**
 * Created by ggtf at 2015/10/28
 * Author:ggtf
 * Time:2015/10/28
 * Email:15170069952@163.com
 * ProjectName:OnTouchEventDemo
 */
public class Frame extends FrameLayout {
    /**
     * 子控件是否执行OnTouch事件
     */
    private boolean subViewAction=false;
    /**
     * 是否将事件交给父控件处理,执行Down事件
     */
    private boolean parentViewAction = false;
    private CallBack callBack;

    public Frame(Context context) {
        super(context);
    }

    public Frame(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * 拦截事件
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            return !subViewAction;
        }
        return super.onInterceptTouchEvent(ev);
    }
    /**
     * OnTouch事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                Tools.oldX=event.getRawX();
                Tools.oldY=event.getRawY();
                Tools.selected = this;
                return !parentViewAction;
            case MotionEvent.ACTION_MOVE:
                Tools.newX=event.getRawX();
                Tools.newY=event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                Tools.newX=event.getRawX();
                Tools.newY=event.getRawY();
                callBack.click(this);
                break;
        }
        return super.onTouchEvent(event);
    }

    public boolean isSubViewAction() {
        return subViewAction;
    }

    public void setSubViewAction(boolean subViewAction) {
        this.subViewAction = subViewAction;
    }

    public boolean isParentViewAction() {
        return parentViewAction;
    }

    public void setParentViewAction(boolean parentViewAction) {
        this.parentViewAction = parentViewAction;
    }
}
