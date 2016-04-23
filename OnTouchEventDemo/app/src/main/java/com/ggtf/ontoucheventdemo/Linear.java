package com.ggtf.ontoucheventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by ggtf at 2015/10/28
 * Author:ggtf
 * Time:2015/10/28
 * Email:15170069952@163.com
 * ProjectName:OnTouchEventDemo
 */
public class Linear extends LinearLayout{
    private float oldX;
    private float newX;
    private float oldY;
    private float newY;
    /**
     * 子控件是否执行OnTouch事件
     */
    private boolean subViewAction=false;
    private CallBack callBack;

    public Linear(Context context) {
        super(context);
    }

    public Linear(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
    /**
     * 依据onInterceptTouchEvent()的Action_Down的返回值,
     * true表示拦截,就不会分发,会调用自身的onTouchEvent事件
     * ,false表示不拦截,分发到子view
     */

    /**
     * 拦截事件;ActionDown事件返回true,表示拦截事件,
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction()==MotionEvent.ACTION_DOWN){
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
                Tools.selected = this;
                Tools.oldX=event.getRawX();
                Tools.oldY=event.getRawY();
                return true;
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
}
