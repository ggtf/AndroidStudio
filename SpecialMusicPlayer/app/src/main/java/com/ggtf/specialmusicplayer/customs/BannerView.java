package com.ggtf.specialmusicplayer.customs;

/**
 * Created by ggtf at 2016/4/21
 * Author:ggtf
 * Time:2016/4/21
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ggtf.specialmusicplayer.R;
import com.ggtf.specialmusicplayer.adapters.BannerAdapter;
import com.ggtf.specialmusicplayer.models.describe.BannerInfo;
import com.ggtf.specialmusicplayer.tools.Logs;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * 实现广告条数据的加载和展示
 */
public class BannerView implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private View viewShow;
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private Context context;
    private List<BannerInfo> bannerInfoList;
    private Handler handler;
    private boolean isStartCycle;
    private int num;

    public BannerView(Context context,View viewShow, List<BannerInfo> bannerInfoList) {
        handler = new Handler(context.getMainLooper());
        this.viewShow = viewShow;
        this.context = context;
        this.bannerInfoList = bannerInfoList;
        initView();
    }

    /**
     * 初始化视图对象
     */
    private void initView() {
        num = bannerInfoList != null ? bannerInfoList.size() : 1;
        num = num <= 0 ? 1 : num;
//        viewShow = LayoutInflater.from(context).inflate(R.layout.banner, null);
//        TODO 实现Viewpager界面切换的自定义动画
        viewPager = (ViewPager) viewShow.findViewById(R.id.id_banner_show);
        viewPager.addOnPageChangeListener(this);
        BannerAdapter adapter = new BannerAdapter(context, bannerInfoList, this);
        viewPager.setAdapter(adapter);
        radioGroup = (RadioGroup) viewShow.findViewById(R.id.id_view_pager_circles);
        setChecked(0);
    }

    /**
     * 设置选中的图片位置
     *
     * @param pos 位置
     */
    private void setChecked(int pos) {
        if (pos >= 0 && pos < radioGroup.getChildCount()) {
            RadioButton rb = (RadioButton) radioGroup.getChildAt(pos);
            rb.setChecked(true);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setChecked(position % num);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 开启广告条轮播;该方法只有效执行一次
     *
     * @param offTimes 时间间隔，单位/秒
     */
    public void startCyclePlay(final int offTimes) {
        if (offTimes <= 0) return;
        if (isStartCycle) {
            return;
        }
        isStartCycle = true;
        Executors.newFixedThreadPool(1).submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(offTimes * 1000);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                            }
                        });
                    } catch (InterruptedException e) {
                        Logs.i(e);
                        Logs.write2Logfile(e);
                        break;
                    }
                }

            }
        });
    }

    /**
     * 返回视图，用于加载到显示界面
     *
     * @return BannerView视图
     */
    public View getViewShow() {
        return viewShow;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.id_banner_show_item:
//                TODO 点击Banner图片进行相应跳转
                int pos = (int) v.getTag();
                toSkipNewUi(pos);
                break;
        }
    }

    /**
     * 依据点击图片的位置，来进行相对应的数据跳转
     *
     * @param pos 位置
     */
    private void toSkipNewUi(int pos) {

    }
}
