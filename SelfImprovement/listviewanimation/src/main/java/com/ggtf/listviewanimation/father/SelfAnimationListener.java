package com.ggtf.listviewanimation.father;

import android.view.animation.Animation;

import com.ggtf.listviewanimation.interfaces.AnimationEnd;
import com.ggtf.listviewanimation.interfaces.AnimationStart;

/**
 * Created by ggtf at 2016/3/5
 * Author:ggtf
 * Time:2016/3/5
 * Email:15170069952@163.com
 * ProjectName:SelfImprovement
 */
public class SelfAnimationListener implements Animation.AnimationListener {
    public AnimationEnd end;
    public AnimationStart start;

    public SelfAnimationListener(AnimationEnd end) {
        this.end = end;
    }

    public SelfAnimationListener(AnimationStart start) {
        this.start = start;
    }

    @Override
    public void onAnimationStart(Animation animation) {
        if (start != null)
            start.animationStart();
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (end != null)
            end.animationEnd();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
