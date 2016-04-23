package com.ggtf.ttdtmusic.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ggtf.ttdtmusic.ContentActivity;
import com.ggtf.ttdtmusic.R;
import com.ggtf.ttdtmusic.adapter.MyListViewAdapter;
import com.ggtf.ttdtmusic.custom.MyListView;
import com.ggtf.ttdtmusic.entities.MyListViewItem;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements View.OnTouchListener {
    private int oldY = 0;
    private int newY = 0;
    private int oldX = 0;
    private int newX = 0;
    private boolean isTop;
    private ImageView bizhi;
    private FrameLayout container;
    private LinearLayout.LayoutParams layoutParams;
    private Handler mainHandler;
    private DrawerLayout drawerLayout;
    private RelativeLayout item1;
    private RelativeLayout item2;
    private RelativeLayout item3;
    private RelativeLayout item4;
    private RelativeLayout bestLove;
    private RelativeLayout collectionMenu;

    public MyFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        //        初始化创建歌单
        initNextView(view);
        return view;
    }


    /**
     * 初始化ListView的内容
     */
    private void initNextView(View view) {
        item1 = (RelativeLayout) view.findViewById(R.id.local_music);
        item2 = (RelativeLayout) view.findViewById(R.id.download_music);
        item3 = (RelativeLayout) view.findViewById(R.id.download_mv);
        item4 = (RelativeLayout) view.findViewById(R.id.lately_listening);
//        item1.setOnClickListener(this);
//        item2.setOnClickListener(this);
//        item3.setOnClickListener(this);
//        item4.setOnClickListener(this);
        item1.setOnTouchListener(this);
        item2.setOnTouchListener(this);
        item3.setOnTouchListener(this);
        item4.setOnTouchListener(this);

        bestLove = (RelativeLayout) view.findViewById(R.id.my_best_love);
        collectionMenu = (RelativeLayout) view.findViewById(R.id.my_collection_menu);

//        bestLove.setOnClickListener(this);
//        collectionMenu.setOnClickListener(this);
        bestLove.setOnTouchListener(this);
        collectionMenu.setOnTouchListener(this);


        MyListView listViewMenu = (MyListView) view.findViewById(R.id.music_menu_list_view);
        List<MyListViewItem> menuItems = new LinkedList<>();
        initListViewMenuItems(menuItems);
        MyListViewAdapter adapterMenu = new MyListViewAdapter(getContext(), menuItems);
        listViewMenu.setAdapter(adapterMenu);


    }

    private void initListViewMenuItems(List<MyListViewItem> menuItems) {
        MyListViewItem itemNew = new MyListViewItem(R.mipmap.music_menu_icon, "点击新建歌单", "");
        for (int i = 0; i < 10; i++) {
            menuItems.add(itemNew);
        }
    }

    private void initView(View view) {
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.my_container);
//        layout.setOnTouchListener(this);
        bizhi = (ImageView) view.findViewById(R.id.bizhi);
        container = (FrameLayout) view.findViewById(R.id.container);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.height = 500;
        container.setLayoutParams(layoutParams);
    }

    public void setMainHandler(Handler mainHandler) {
        this.mainHandler = mainHandler;
    }

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                oldY = (int) event.getRawY();
                newY = (int) event.getRawY();
                oldX = (int) event.getRawX();
                newX = (int) event.getRawX();
                Log.i("Info", "ACTION_DOWN");
//                return true;
                break;
            case MotionEvent.ACTION_MOVE:
                newY = (int) event.getRawY();
                newX = (int) event.getRawX();
                Log.i("Info", "ACTION_MOVE");
                if (newY - oldY > 10) {
                    int distance = newY - oldY;
                    layoutParams.height = 500 + distance / 10;
                    container.setLayoutParams(layoutParams);
                } else if (newX - oldX > 30) {
//                    打开侧滑菜单
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
//                return true;
                break;
            case MotionEvent.ACTION_UP:
                newX = (int) event.getRawX();
                newY = (int) event.getRawY();
                Log.i("Info", "ACTION_UP");
//                layoutParams.height = 500;
//                container.setLayoutParams(layoutParams);
                /**
                 * 点击事件触发
                 */
                if (newX - oldX < 10 || newX - oldX > -10 || newY - oldY < 10 || newY - oldY > -10) {
                    if (v == item1) {
                        localMusicClick(v);
                    } else if (v == item2) {
                        downloadMusicClick(v);
                    } else if (v == item3) {
                        downloadMVClick(v);
                    } else if (v == item4) {
                        latelyPlayClick(v);
                    } else if (v == bestLove) {
                        bestLoveClick(v);
                    } else if (v == collectionMenu) {
                        collectionMenuClick(v);
                    }
                }
                break;
        }
        return false;
    }

    /**
     * 点击事件方法
     */
    /**
     * 点击本地音乐触发的事件
     * @param view
     */
    private void localMusicClick(View view) {
        Toast.makeText(getContext(), "本地音乐", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), ContentActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.side_in_left, R.anim.slide_out_right);
    }

    /**
     * 点击下载音乐触发的事件
     * @param view
     */
    private void downloadMusicClick(View view) {
        Toast.makeText(getContext(), "下载音乐", Toast.LENGTH_SHORT).show();
        showPopupMenu(view);
    }

    /**
     * 点击下载MV触发的事件
     * @param view
     */
    private void downloadMVClick(View view) {
        Toast.makeText(getContext(), "下载MV", Toast.LENGTH_SHORT).show();
    }

    /**
     * 点击最近播放触发的事件
     * @param view
     */
    private void latelyPlayClick(View view) {
        Toast.makeText(getContext(), "最近播放", Toast.LENGTH_SHORT).show();
    }

    /**
     * 点击我的收藏--最爱触发的事件
     * @param view
     */
    private void bestLoveClick(View view) {
        Toast.makeText(getContext(), "我的收藏--最爱", Toast.LENGTH_SHORT).show();
    }

    /**
     * 点击我的收藏--歌单触发的事件
     * @param view
     */
    private void collectionMenuClick(View view) {
        Toast.makeText(getContext(), "我的收藏--歌单", Toast.LENGTH_SHORT).show();
    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view, Gravity.TOP);
        popupMenu.inflate(R.menu.menu_popup);
        popupMenu.show();
    }
}
