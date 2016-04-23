package com.ggtf.ttdtmusic;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RemoteViews;

import com.ggtf.ttdtmusic.adapter.MainPagerAdapter;
import com.ggtf.ttdtmusic.adapter.SideMenuAdapter;
import com.ggtf.ttdtmusic.broadcast.MusicBroadcastReceiver;
import com.ggtf.ttdtmusic.cache.FileCache;
import com.ggtf.ttdtmusic.custom.MusicNotification;
import com.ggtf.ttdtmusic.fragments.DiscoverFragment;
import com.ggtf.ttdtmusic.fragments.MyFragment;
import com.ggtf.ttdtmusic.fragments.RecommendFragment;
import com.ggtf.ttdtmusic.http.HttpUtils;
import com.ggtf.ttdtmusic.service.MusicService;
import com.ggtf.ttdtmusic.tools.Constant;
import com.ggtf.ttdtmusic.tools.MusicNetworkUrl;
import com.ggtf.ttdtmusic.tools.Tools;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ServiceConnection, DrawerLayout.DrawerListener, TabLayout.OnTabSelectedListener, AdapterView.OnItemClickListener {
    /**
     * 绑定服务控制器,用来响应服务中的一些控制
     */
    private MusicService.Controller controller;
    private boolean isMusicPlaying;
    private int currentMusicPos = 0;
    public  Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg != null) {
                int what = msg.what;
                switch (what) {
                    case MusicService.MUSIC_PROGRESS:
                        isMusicPlaying = (boolean) msg.obj;
                        musicProgress.setMax(msg.arg2);
                        musicProgress.setProgress(msg.arg1);
                        break;
                    case Constant.CACHE_SUCCESSFUL:
//                        TODO 缓存成功后就将SD卡下的Cache目录下的文件读取出来,用于播放
                        Log.i("Info", "Constant.CACHE_SUCCESSFUL");
                        break;
                    case Constant.CACHE_UNSUCCESSFUL:
                        Log.i("Info", "Constant.CACHE_UNSUCCESSFUL");
                        break;
                    case Constant.ACTIVITY_FINISH:
//                        关闭Activity
                        finish();
                        break;
                    case Constant.MUSIC_CURRENT_POSITION:
                        currentMusicPos = msg.arg1;
                        break;
                }
            }
        }
    };
    private ProgressBar musicProgress;
    private Intent musicBindService;
    private MusicNotification musicNotification;
    private DrawerLayout drawerLayout;
    private LinearLayout contentLayout;
    private int oldX;
    private int currentX;
    private float pivotX;
    private boolean isFirstIn = true;
    private ViewPager viewPager;
    private TabLayout titleBar;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        musicProgress = (ProgressBar) findViewById(R.id.music_progress);

        //        DrawerLayout对象实例
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        DrawerLayout设置侧滑监听
        drawerLayout.setDrawerListener(this);
//        内容布局对象实例
        contentLayout = (LinearLayout) findViewById(R.id.content_layout);
//        初始化menuLeft的菜单项
        initMenuView();
//        初始化MainLayout布局
        initMainLayout();
        /**
         * 使用startService来启动服务
         */
        musicBindService = new Intent(this, MusicService.class);
        startService(musicBindService);


//        musicNotification = new MusicNotification(this);
//        musicNotification.sendNotification();


//        String[] imagePath = Tools.getSDCardImagePath(this,null,null);
//        if (imagePath!=null && imagePath.length>0){
//            for (int i = 0; i < imagePath.length; i++) {
//                Log.i("Info","imagePath = "+imagePath[i]);
//            }
//        }
//        String[] audioPath = Tools.getSDCardAudioPath(this, null, null);
//        if (audioPath!=null && audioPath.length>0){
//            for (int i = 0; i < audioPath.length; i++) {
//                Log.i("Info","audioPath = "+audioPath[i]);
//            }
//        }
//        String[] videoPath = Tools.getSDCardVideoPath(this, null, null);
//        if (videoPath!=null && videoPath.length>0){
//            for (int i = 0; i < videoPath.length; i++) {
//                Log.i("Info","videoPath = "+videoPath[i]);
//            }
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        /**
         * 使用bindService来绑定服务
         */
        bindService(musicBindService, this, BIND_AUTO_CREATE);
    }


    /**
     * 服务绑定成功,在服务中返回IBinder;
     *
     * @param name
     * @param service
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        controller = (MusicService.Controller) service;
        controller.setMainHandler(mainHandler);

    }


    /**
     * 服务绑定断开
     *
     * @param name
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {
        controller = null;
    }

    public void musicPlaying(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.in_fast:
//                controller.forward();
                controller.nextMusic(currentMusicPos);
                currentMusicPos++;
//                当播放到最后一首歌曲时,点击下一首就回到播放第一首歌曲
                if (currentMusicPos>=controller.getMusicNum()){
                    currentMusicPos = 0;
                }
                controller.setCurrentMusic(currentMusicPos);
                break;
            case R.id.out_fast:
//                controller.backward();
                if (currentMusicPos>0){
                    controller.previousMusic(currentMusicPos);
                    currentMusicPos--;
                    controller.setCurrentMusic(currentMusicPos);
                }
                break;
            case R.id.play:
                Button play = (Button) view;
                if (isMusicPlaying) {
                    controller.pause();
                    play.setText("播放");
                } else {
                    controller.play();
                    play.setText("暂停");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        解除绑定;
        unbindService(this);
//        关闭服务;
//        stopService(musicBindService);
        /**
         * Activity销毁时关闭通知栏
         */
//        Intent closeNotification = new Intent(this,MusicService.class);
//        closeNotification.putExtra("closeNotification",Constant.CLOSE_NOTIFICATION_SERVICE);
//        startService(closeNotification);
    }
    public void downloadMusic(View view) {
        String[] musicUrls = MusicNetworkUrl.musicUrls;
        String[] musicName = MusicNetworkUrl.musicName;
        for (int i = 0; i < musicName.length; i++) {
//            TODO 依据所给的音乐链接和音乐名调用下载音乐方法来下载音乐
        }
    }


    private void initMainLayout() {
        titleBar = (TabLayout) findViewById(R.id.tab_layout);
        /**
         * 给TabLayout设置Tab的颜色时，必须指定透明度，否则无法显示出来
         */
        titleBar.setTabTextColors(0xffababab, 0xffffffff);
        titleBar.setTabMode(TabLayout.MODE_FIXED);
        titleBar.setSelectedTabIndicatorColor(0xffffffff);
        titleBar.setTabGravity(TabLayout.GRAVITY_FILL);
        TabLayout.Tab tab = titleBar.newTab();
//        TextView tabView = (TextView) LayoutInflater.from(this).inflate(R.layout.tab_layout_my,null);
        tab.setCustomView(R.layout.tab_layout_my);
        titleBar.addTab(tab);
        tab = titleBar.newTab();
        tab.setCustomView(R.layout.tab_layout_recommend);
        titleBar.addTab(tab);
        tab = titleBar.newTab();
        tab.setCustomView(R.layout.tab_layout_discover);
        titleBar.addTab(tab);


        titleBar.setSelectedTabIndicatorHeight(10);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
//        String[] titles = {"我的","推荐","发现"};
        fragments = new LinkedList<>();
        initFragments(fragments);
        FragmentManager manager = getSupportFragmentManager();
//        MainPagerAdapter adapter = new MainPagerAdapter(manager,fragments,titles);
        MainPagerAdapter adapter = new MainPagerAdapter(manager, fragments);
        viewPager.setAdapter(adapter);
//        titleBar.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(titleBar) {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Fragment fragment = fragments.get(position);
                if (fragment!=null){
                    setStartAnimation(fragment);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (isFirstIn){
                    Fragment fragment = fragments.get(position);
                    if (fragment!=null){
                        setStartAnimation(fragment);
                    }
                    isFirstIn = false;
                }
            }
        });
//        viewPager.addOnPageChangeListener(this);
        titleBar.setOnTabSelectedListener(this);
    }

    /**
     * 设置启动动画
     * @param fragment
     */
    private void setStartAnimation(Fragment fragment) {
        if (fragment.getView()!=null){
            if (isFirstIn){
                pivotX = fragment.getView().getPivotX();
            }
            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    0f,
                    1f,
                    0f,
                    1f,
                    pivotX,
                    0);
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1f);
            AnimationSet animationSet = new AnimationSet(true);
            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(alphaAnimation);
            animationSet.setDuration(1500);
            animationSet.setInterpolator(new BounceInterpolator());
            fragment.getView().startAnimation(animationSet);
        }

    }

    private void initFragments(List<Fragment> fragments){
        MyFragment myFragment = new MyFragment();
        myFragment.setMainHandler(mainHandler);
        myFragment.setDrawerLayout(drawerLayout);
        RecommendFragment recommendFragment = new RecommendFragment();
        DiscoverFragment discoverFragment = new DiscoverFragment();

        fragments.add(myFragment);
        fragments.add(recommendFragment);
        fragments.add(discoverFragment);
    }

    /**
     * 初始化MenuLeft菜单项的内容
     */
    private void initMenuView() {
        int[] itemRes = {R.drawable.item1_selector, R.drawable.item2_selector, R.drawable.item3_selector,
                R.drawable.item4_selector, R.drawable.item5_selector, R.drawable.item6_selector,
                R.drawable.item7_selector, R.drawable.item8_selector, R.drawable.item9_selector,
                R.drawable.item10_selector, R.drawable.item11_selector, R.drawable.item12_selector,
                R.drawable.item13_selector};
        ListView listView = (ListView) findViewById(R.id.menu_left_list);
        List<Integer> items = new LinkedList<>();
        for (int i = 0; i < itemRes.length; i++) {
            items.add(itemRes[i]);
        }
        SideMenuAdapter adapter = new SideMenuAdapter(this, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }


    /**
     * ListView的Item点击事件监听；
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
//                    仅wifi联网
                break;
            case 1:
//                    透明模式
                break;
            case 2:
//                    壁纸&皮肤
                break;
            case 3:
//                    睡眠设置
                break;
            case 4:
//                    滴滴求歌
                break;
            case 5:
//                    音效
                break;
            case 6:
//                    音乐包
                break;
            case 7:
//                    设置
                break;
            case 8:
//                    扫描音乐
                break;
            case 9:
//                    听歌识曲
                break;
            case 10:
//                    极速传歌
                break;
            case 11:
//                    流量包
                break;
            case 12:
//                    精品推荐
                break;

        }
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        contentLayout.setTranslationX(drawerView.getWidth() * slideOffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
    }

    @Override
    public void onDrawerClosed(View drawerView) {
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }


    public void titleClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.function_key:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.search:
//                跳转到搜索界面
                startOtherActivity(ContentActivity.class);
                break;
        }
    }

    /**
     * Tab被选中的回调
     * @param tab
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int tabPosition = titleBar.getSelectedTabPosition();
        viewPager.setCurrentItem(tabPosition, false);
    }

    /**
     * Tab未被选中的回调
     * @param tab
     */
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    /**
     * Tab重复选中的回调
     * @param tab
     */
    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**
     * 启动其他Activity
     * @param targetClass
     */
    private void startOtherActivity(Class targetClass){
        Intent intent = new Intent(this,targetClass);
        startActivity(intent);
        overridePendingTransition(R.anim.side_in_left, R.anim.slide_out_right);
    }


}
