package com.ggtf.gdiplusdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ggtf at 2015/10/22
 * Author:ggtf
 * Time:2015/10/22
 * Email:15170069952@163.com
 * ProjectName:GdiPlusDemo
 */
public class MyView extends View {

    private Paint paint;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 初始化数据
         */
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /**
         * 裁剪区域,只有在参数中的区域才可以被绘制
         */
//        canvas.clipPath(Path path);
//        透明背景
        canvas.drawColor(Color.TRANSPARENT);
//        设置颜色
        paint.setColor(Color.RED);
//        设置抗锯齿;(反走样)
        paint.setAntiAlias(true);
//        设置填充充样式(边框)
        paint.setStyle(Paint.Style.STROKE);
//        设置边框的宽度
        paint.setStrokeWidth(3);
//        绘制圆形
        canvas.drawCircle(300, 300, 50, paint);
        /**
         * 把绘制的原点平移translate
         */
        canvas.translate(100, 100);
        canvas.drawCircle(300, 300, 50, paint);
        /**
         * 绘制一个椭圆,并旋转
         */
        canvas.translate(100, 100);
        paint.setColor(Color.CYAN);
        canvas.drawOval(new RectF(-50, -50, 100, 50), paint);
        /**
         * 旋转30度(默认在中心原点旋转)rotate
         */
        canvas.rotate(30);
        paint.setColor(Color.RED);
        canvas.drawOval(new RectF(-50, -50, 100, 50), paint);
        /**
         * 缩放scale
         */
        canvas.translate(100, 100);
        canvas.drawCircle(300, 300, 50, paint);
        canvas.scale(0.5f, 1.5f);
        canvas.drawCircle(300, 300, 50, paint);
        paint.setStyle(Paint.Style.FILL);
        Bitmap bitmap = getHeadImage();
        /**
         * 渲染Shader
         */
        BitmapShader bs = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        paint.setShader(bs);
        canvas.scale(2f, 0.75f);
//        canvas.translate(300,300);
        canvas.drawCircle(100,100,bitmap.getWidth()/2,paint);
    }

    private Bitmap getHeadImage(){
        InputStream inputStream =null;
        try {
            inputStream = this.getContext().getAssets().open("drawing.jpg");
        } catch (IOException e) {

        }
        if (inputStream == null){
            return null;
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }
}
