package com.ggtf.selfimprovement.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;

import com.ggtf.selfimprovement.R;

/**
 * Created by ggtf at 2016/3/4
 * Author:ggtf
 * Time:2016/3/4
 * Email:15170069952@163.com
 * ProjectName:SelfImprovement
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class SelfOutlineProvider extends ViewOutlineProvider {

    private Context context;

    public SelfOutlineProvider(Context context) {
        this.context = context;
    }

    @Override
    public void getOutline(View view, Outline outline) {
        int size = context.getResources().getDimensionPixelSize(R.dimen.float_button_size);
        outline.setOval(-4, -4, size + 2, size + 2);
    }
}
