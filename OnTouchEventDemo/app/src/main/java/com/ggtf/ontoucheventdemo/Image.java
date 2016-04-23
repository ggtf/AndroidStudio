package com.ggtf.ontoucheventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by ggtf at 2015/10/28
 * Author:ggtf
 * Time:2015/10/28
 * Email:15170069952@163.com
 * ProjectName:OnTouchEventDemo
 */
public class Image extends ImageView {


    private CallBack callBack;
    /**
     * 是否将事件交给父控件处理,执行Down事件
     */
    private boolean parentViewAction = false;

    public Image(Context context) {
        this(context, null);
    }

    public Image(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * OnTouch事件
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
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

    public boolean isParentViewAction() {
        return parentViewAction;
    }

    public void setParentViewAction(boolean parentViewAction) {
        this.parentViewAction = parentViewAction;
    }


}
