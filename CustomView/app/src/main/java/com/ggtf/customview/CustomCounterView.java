package com.ggtf.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

/**
 * Created by ggtf at 2015/10/14
 * Author:ggtf
 * Time:2015/10/14
 * Email:15170069952@163.com
 * ProjectName:CustomView
 */
public class CustomCounterView extends View{
    /**
     * 画笔,绘制
     */
    private Paint paint;
    /**
     * 矩形(用于绘制背景空间)
     */
    private Rect rect;
    /**
     * 矩形(用于包含需要绘制文本的空间)
     */
    private Rect rectTxt;
    private int count;

    /**
     * 自定义属性的定义
     */
//    背景颜色
    private int bg_color;
    //    文本颜色
    private int text_color;
    //    背景外形
    private int shape;
    //    文本大小
    private float text_size;
    //    绘制的文本
    private String text;
    private int ascent;


    public CustomCounterView(Context context) {
        this(context, null);
        this.getParent().requestDisallowInterceptTouchEvent(true);
    }


    public CustomCounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * 初始化一些数据
         */
        /**
         * flags参数
         * ANTI_ALIAS_FLAG:抗锯齿
         */
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /**
         * 绘制的空间
         */
        rect = new Rect();
        rectTxt = new Rect();
        /**
         * 设置监听事件,使用自己实现的OnClickListener事件处理;
         */
        init(context, attrs);
    }

    /**
     * 初始化属性
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, @Nullable AttributeSet attrs) {
//        默认属性
        bg_color = Color.GRAY;
        text_color = Color.WHITE;
        shape = 1;
        text_size = 30.0f;
        text = "";

        if (attrs != null) {

            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomCounterView);
            if (typedArray != null) {

                bg_color = typedArray.getColor(R.styleable.CustomCounterView_bg_color, bg_color);
                text_color = typedArray.getColor(R.styleable.CustomCounterView_text_color, text_color);
                shape = typedArray.getInt(R.styleable.CustomCounterView_bg_shape, shape);
                text_size = typedArray.getDimension(R.styleable.CustomCounterView_text_size, text_size);
                text = typedArray.getString(R.styleable.CustomCounterView_text);
//            回收TypeArray
                typedArray.recycle();
            }
        }
//        将得到的属性设置到自绘的控件上


    }

    @SuppressLint("NewApi")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 设置画笔颜色
         */
//        paint.setColor(getResources().getColor(R.color.paint_color_bg));
//        paint.setColor(Color.GRAY);
        paint.setColor(bg_color);
        /**
         * getWith();当前控件的宽度
         * getHeight();当前控件的高度
         */
        /**
         * 绘制背景
         */
        int min = Math.min(getWidth(), getHeight());
        int max = Math.max(getWidth(), getHeight());
        if (shape == 1) {
            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        } else if (shape == 2) {
            canvas.drawRoundRect((max-min)/2, 0f, min+(max-min)/2, min, min / 2, min / 2, paint);
        }


        /**
         * 设置绘制文本的颜色
         */
        paint.setColor(text_color);
        /**
         * 设置文本的大小
         * getResources().getDisplayMetrics().density:获取设置逻辑密度
         */
        int density = (int) getResources().getDisplayMetrics().density;
        paint.setTextSize(text_size);
        /**
         * 获取文本的矩形控件
         */
//        String time = String .valueOf(count);
//        String time = "A";
        /**
         * 参数1:需要绘制的文本
         * 参数2:从哪一个字符索引开始绘制
         * 参数3:结束到哪一个字符索引停止绘制
         * 参数4:返回一个包含需要绘制文本的Rect
         */
        paint.getTextBounds(text, 0, text.length(), rectTxt);
        /**
         * 根据包含绘制文本的Rect来获取文本的宽度和高度
         */
        int txtWidth = rectTxt.width();
        int txtHeight = rectTxt.height();
        /**
         *绘制文本
         */
        /**
         * 参数1:需要绘制的文本
         * 参数2:绘制文本区域的左上角横坐标;绘制的起点;
         * 参数3:绘制文本区域的基线位置的纵坐标,
         * 参数4:用于绘制文本的画笔
         * 参数2,参数3构成的坐标(x,y)就是绘制文本区域的左下角的坐标
         */

        canvas.drawText(text,getWidth()/2-txtWidth/2,getHeight()/2+txtHeight/2,paint);
//        canvas.drawText(text, getPaddingLeft()+txtWidth/2, getPaddingTop() - ascent+txtHeight/2+20, paint);


    }

    /**
     * 测量View的和它的内容,来决定measuredWidth,measuredHeight;
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//        widthMeasureSpec = MeasureSpec.makeMeasureSpec(200,MeasureSpec.AT_MOST);
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(200,MeasureSpec.AT_MOST);

        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));

//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 测量当前控件的宽度是多少
     *
     * @param measureSpec
     * @return
     */
    private int measureWidth(int measureSpec) {
        int width = 0;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        /**
         * 精确的测量模式
         */
        if (specMode == MeasureSpec.EXACTLY) {
            width = specSize;
        } else {
            /**
             * 非精确测量模式
             */
            /**
             * 测量Text的宽度
             */
            width = (int) (paint.measureText(text) + getPaddingLeft() + getPaddingRight());
            /**
             * AT_MOST模式;view宽度想要多大就分配多大
             */
            if (specMode == MeasureSpec.AT_MOST) {
                /**
                 * 选择 测量宽度 和 测量view中text的宽度 之间的最小值为view的宽度
                 */
                width = Math.min(width, specSize);
            }
        }

        return width;
    }

    /**
     * 测量当前控件的高度是多少
     *
     * @param measureSpec
     * @return
     */
    private int measureHeight(int measureSpec) {
        int height = 0;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        /**
         * ascent();返回的是基于基线到text文本之上的距离;值为负数;
         */
        ascent = (int) paint.ascent();
        /**
         * 精确测量模式
         */
        if (specMode == MeasureSpec.EXACTLY) {
            height = specSize;
        } else {
            /**
             * 测量Text的高度
             */
            /**
             * descent();返回基于基线到text文本之下的距离;值为正数;
             */
            height = (int) (-ascent + paint.descent() + getPaddingTop() + getPaddingBottom());
            /**
             * AT_MOST模式;view高度想要多大就分配多大
             */
            if (specMode == MeasureSpec.AT_MOST) {
                /**
                 * 选择 测量高度 和 测量view中text的高度 之间的最小值为view的高度
                 */
                height = Math.min(height, specSize);
            }
        }
        return height;
    }

    /**
     * 确定View的摆放位置和大小
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        left+=10;
        top+=10;
        right+=10;
        bottom+=10;
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 给这个自绘的View实现点击事件
     *
     * @param v
     */
 /*   @Override
    public void onClick(View v) {
        Toast.makeText(getContext(),""+text,Toast.LENGTH_SHORT).show();

//        count = 0;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    count++;
//                    *//**
//                     * 强制OnDraw()方法执行
//                     *//*
//                    *//**
//                     * 当前视图可见时,会调用OnDraw()方法;UI-Thread线程调用;
//                     * Not-UI-Thread,采用postInvalidate()方法来实现;
//                     *//*
//                    postInvalidate();
////                invalidate();
//                    if (count == 20 * 2) {
//                        *//**
//                         * 20s后退出线程
//                         *//*
//                        break;
//                    }
//                }
//            }
//        }).start();

    }*/

    /**
     * 给这个自绘控件实现Touch事件

/*    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }*/

    public int getBg_color() {
        return bg_color;
    }

    public void setBg_color(int bg_color) {
        this.bg_color = bg_color;
        /**
         * 强制OnDraw()方法执行;
         */
        invalidate();
    }

    public float getText_size() {
        return text_size;
    }

    public void setText_size(float text_size) {
        this.text_size = text_size;
        requestLayout();
        invalidate();
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
        invalidate();
    }

    public int getText_color() {
        return text_color;
    }

    public void setText_color(int text_color) {
        this.text_color = text_color;
        invalidate();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
    }
}
