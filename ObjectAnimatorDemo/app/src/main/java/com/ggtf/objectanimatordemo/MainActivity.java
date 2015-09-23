package com.ggtf.objectanimatordemo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.launcher_icon);


    }

    private void getAnimator(){
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator_scale);
        playAnimation(animator,imageView,true);
    }

    /**
     * 给指定的View播放动画<br/>
     * @param animator 属性动画对象<br/>
     * @param view 播放动画的对象<br/>
     * @param isReverse boolean true代表动画是animator指定动画的反向动画,false代表使用animator定义的动画<br/>
     */
    private void playAnimation(Animator animator,View view,boolean isReverse){
        animator.setTarget(view);
        AnimatorSet animatorSet = (AnimatorSet) animator;
        ArrayList<Animator> childAnimations = animatorSet.getChildAnimations();
        for (Animator childAnimator : childAnimations) {
            if (childAnimator instanceof ObjectAnimator){
                ObjectAnimator objectAnimator = (ObjectAnimator) childAnimator;
                if (isReverse){
                    objectAnimator.reverse();
                }else {
                    objectAnimator.start();
                }
            }
        }
    }

    /**
     * 点击播放动画
     * @param view
     */
    public void playAnimation(View view) {
        getAnimator();
    }
}
