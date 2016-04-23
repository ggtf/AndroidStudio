package com.ggtf.multipleclickabletextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by ggtf at 2015/11/9
 * Author:ggtf
 * Time:2015/11/9
 * Email:15170069952@163.com
 * ProjectName:CustomView
 */
public class CircleImage extends ImageView {
    private Paint paint;
    private PorterDuffXfermode xfermode;

    public CircleImage(Context context) {
        this(context,null);
    }
    public CircleImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 初始化
         */
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);

        paint.setXfermode(xfermode);


    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2-10, paint);

    }
}
