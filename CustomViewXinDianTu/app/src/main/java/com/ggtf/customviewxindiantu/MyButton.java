package com.ggtf.customviewxindiantu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ggtf at 2015/10/19
 * Author:ggtf
 * Time:2015/10/19
 * Email:15170069952@163.com
 * ProjectName:CustomViewXinDianTu
 */

/**
 * 红绿灯绘制
 */
public class MyButton extends View {
    /**
     * 3张图片
     */
    public String image_state1 = "";
    public String image_state2 = "";
    public String image_state3 = "";

    public int currentState = 0;

    public boolean isChecked;
    private boolean isDown;
    private Paint paint;


    public MyButton(Context context) {
        this(context, null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.setClickable(true);

        image_state1 = "com.ggtf.customviewxindiantu/images/1.png";
        image_state1 = "com.ggtf.customviewxindiantu/images/2.png";
        image_state1 = "com.ggtf.customviewxindiantu/images/3.png";

    }

    @Override
    protected void onDraw(Canvas canvas) {
        String imagepath = image_state1;
        Bitmap bitmap = getImageFromAssets(imagepath);
        if (bitmap !=null){
            Rect rect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
            Rect rect1 = new Rect(0,0,getWidth(),getHeight());
            canvas.drawBitmap(bitmap,rect,rect1,paint);
            bitmap.recycle();
        }

    }

    private Bitmap getImageFromAssets(String imagepath) {
        InputStream inputStream = null;
        try {
            inputStream = this.getContext().getAssets().open(imagepath);
        } catch (IOException e) {

        }
        if (inputStream == null) {
            return null;
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        if (bitmap != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }else {
            return null;
        }
    }
}
