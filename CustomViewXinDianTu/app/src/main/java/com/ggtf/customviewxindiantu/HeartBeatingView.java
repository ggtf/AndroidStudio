package com.ggtf.customviewxindiantu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ggtf at 2015/10/19
 * Author:ggtf
 * Time:2015/10/19
 * Email:15170069952@163.com
 * ProjectName:CustomViewXinDianTu
 */
public class HeartBeatingView extends View {

    public static final int BG_COLOR=0xFF000000;
    public static final int REFERENCE_COLOR=0xFF808080;
    public static final int FOR_COLOR=0xFF00FF00;
    private byte[] localData = null;
    public static final int STEP = 6;
    private Paint paint;
    public HeartBeatingView(Context context) {
        this(context, null);
    }

    public HeartBeatingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void setLocalData(byte[] localData) {
        this.localData = localData;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        1.绘制背景
        canvas.drawColor(BG_COLOR);
//        2.绘制参考线
        paint.setColor(REFERENCE_COLOR);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(0,getHeight()/2,getWidth(),getHeight()/2,paint);
//        3.绘制数据
//        3.1检查数据是否存在
        if (localData == null || localData.length<1){
            return;
        }
//        3.2从第一个点绘制到最后一个点
//        3.3血压的基准值为95,localData换算成真实的血压值需要+95;
        paint.setColor(FOR_COLOR);
        canvas.translate(0,getHeight()/2);
        for (int i = localData.length-1; i >=Math.max(1,localData.length-getWidth()/STEP+1);i--) {
//            3.4绘制一条线(一条线绘制需要两个点)
            int left = i;
            if (localData.length>getWidth()/STEP){
                left = i-(localData.length-getWidth()/STEP);
            }
            canvas.drawLine(left*STEP,localData[i],left*STEP-STEP,localData[i-1],paint);

        }


    }
}
