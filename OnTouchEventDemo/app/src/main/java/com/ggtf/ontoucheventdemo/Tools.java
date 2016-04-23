package com.ggtf.ontoucheventdemo;

import android.view.View;

/**
 * Created by ggtf at 2015/10/28
 * Author:ggtf
 * Time:2015/10/28
 * Email:15170069952@163.com
 * ProjectName:OnTouchEventDemo
 */
public class Tools {
    private Tools() {

    }

    public static View selected;
    public static float oldX;
    public static float oldY;
    public static float newX;
    public static float newY;

    public static void setSelected(View view) {
        selected = view;
    }

    ;
}
