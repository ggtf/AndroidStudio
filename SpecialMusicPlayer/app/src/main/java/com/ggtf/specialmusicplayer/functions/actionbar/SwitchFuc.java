package com.ggtf.specialmusicplayer.functions.actionbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ggtf.specialmusicplayer.R;
import com.ggtf.specialmusicplayer.functions.fragment.SwitchFra;

/**
 * Created by ggtf at 2016/4/17
 * Author:ggtf
 * Time:2016/4/17
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 * 实现功能的切换展示
 */
public class SwitchFuc implements View.OnClickListener {
    private static final int LEFT = 0;
    private static final int CENTER = 1;
    private static final int RIGHT = 2;
    private static final int[] IMAGE_SORT = new int[]{R.mipmap.action_bar_found, R.mipmap.action_bar_music, R.mipmap.action_bar_recommend};
    private static final int[] SORT_LCR = new int[]{LEFT, CENTER, RIGHT};
    private static final int[] SORT_RLC = new int[]{RIGHT, LEFT, CENTER};
    private static final int[] SORT_CRL = new int[]{CENTER, RIGHT, LEFT};


    private ImageView left;
    private ImageView center;
    private ImageView right;
    private int leftMargin;
    private int centerMargin;
    private int rightMargin;
    private float oldX;
    private int[] sort = SORT_LCR;
    private ViewGroup actionbarContainer;
    private int pos;
    private SwitchFra switchFra;

    public SwitchFuc(ViewGroup actionbarContainer) {
        pos = 1;
        this.actionbarContainer = actionbarContainer;
        this.actionbarContainer.setClickable(true);
        initFuc();
    }

    private void initFuc() {
        left = (ImageView) actionbarContainer.findViewById(R.id.id_left);
        center = (ImageView) actionbarContainer.findViewById(R.id.id_center);
        right = (ImageView) actionbarContainer.findViewById(R.id.id_right);
        left.setOnClickListener(this);
        center.setOnClickListener(this);
        right.setOnClickListener(this);
        toBeDefault();
    }

    private void toBeDefault() {
        left.setScaleX(0.8f);
        left.setScaleY(0.8f);
        center.setScaleX(1.2f);
        center.setScaleY(1.2f);
        right.setScaleX(0.8f);
        right.setScaleY(0.8f);
    }

    private void playAni(float distance) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.id_left:
                playLeft();
                break;
            case R.id.id_center:
                playCenter();
                break;
            case R.id.id_right:
                playRight();
                break;
        }
        setImages();
    }

    private void setImages() {
        pos = sort[1];
        if (switchFra != null)
            switchFra.setFragmentPos(pos);
        left.setImageResource(IMAGE_SORT[sort[0]]);
        center.setImageResource(IMAGE_SORT[sort[1]]);
        right.setImageResource(IMAGE_SORT[sort[2]]);
    }

    private void playRight() {
        if (sort == SORT_CRL) {
            sort = SORT_RLC;
        } else if (sort == SORT_RLC) {
            sort = SORT_LCR;
        } else if (sort == SORT_LCR) {
            sort = SORT_CRL;
        }

    }

    private void playCenter() {

    }

    private void playLeft() {
        if (sort == SORT_CRL) {
            sort = SORT_LCR;
        } else if (sort == SORT_RLC) {
            sort = SORT_CRL;
        } else if (sort == SORT_LCR) {
            sort = SORT_RLC;
        }
    }

    public void setSort(int pos) {
        switch (pos) {
            case LEFT:
                sort = SORT_RLC;
                break;
            case RIGHT:
                sort = SORT_CRL;
                break;
            case CENTER:
                sort = SORT_LCR;
                break;
        }
        setImages();
    }

    public void boundSwitchFra(SwitchFra fra) {
        switchFra = fra;
    }

}
