package com.ggtf.specialmusicplayer.functions.menu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ggtf.specialmusicplayer.R;
import com.ggtf.specialmusicplayer.network.HttpUtils;
import com.ggtf.specialmusicplayer.tools.ContentsValue;
import com.ggtf.specialmusicplayer.tools.Utils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggtf at 2016/4/16
 * Author:ggtf
 * Time:2016/4/16
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 * 音乐播放器的音乐菜单模块
 */
public class MusicMenu implements View.OnClickListener {
    public static final int LIST_ITEM_TYPE_0 = 0;
    public static final int LIST_ITEM_TYPE_1 = 1;
    public static final int LIST_ITEM_TYPE_2 = 2;
    public static final int LIST_ITEM_TYPE_3 = 3;
    private Context context;
    private List<MenuItem> subFuc;
    private MenuAdapter adapter;
    private int typeCount;
    private WindowManager.LayoutParams lp;
    private boolean isOpen;
    private View menuFloat;
    private FrameLayout menu;
    private boolean isHadAddView;
    private TextView action;

    public MusicMenu(Context context, @NonNull List<MenuItem> subFuc) {
        this.context = context;
        this.subFuc = subFuc;
        typeCount = 1;
    }

    private void initMenu(@NonNull ListView showFuc) {
        if (subFuc != null) {
            adapter = new MenuAdapter(context, subFuc, typeCount);
            showFuc.setAdapter(adapter);
        }
    }

    public void setTypeCount(int typeCount) {
        this.typeCount = typeCount;
    }

    private void openMenu(Activity activity) {
//        TODO 打开菜单
        isOpen = true;
        showActivityFloatWindow(activity, true);
    }

    private void closeMenu(Activity activity) {
//        TODO 关闭菜单
        isOpen = false;
        showActivityFloatWindow(activity, false);
    }

    public void playAnimation(int typeAni) {
//        TODO 依据typeAni来采用对应的动画展示/关闭菜单
    }

    public static MenuItem createMenuItem(int type, String label) {
        return new MenuItem(type, label);
    }

    public static List<MenuItem> createMenuItemSet(Context context) {
        List<MenuItem> menuItems = new LinkedList<>();
        menuItems.add(createMenuItem(LIST_ITEM_TYPE_0, context.getResources().getString(R.string.menu_function_0)));
        menuItems.add(createMenuItem(LIST_ITEM_TYPE_0, context.getResources().getString(R.string.menu_function_1)));
        menuItems.add(createMenuItem(LIST_ITEM_TYPE_0, context.getResources().getString(R.string.menu_function_2)));
        menuItems.add(createMenuItem(LIST_ITEM_TYPE_0, context.getResources().getString(R.string.menu_function_3)));
        menuItems.add(createMenuItem(LIST_ITEM_TYPE_0, context.getResources().getString(R.string.menu_function_4)));
        return menuItems;
    }

    public void showActivityFloatWindow(Activity activity, boolean isOpen) {
        WindowManager manager = activity.getWindowManager();
        if (lp == null) {
            lp = new WindowManager.LayoutParams();
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            lp.gravity = Gravity.START | Gravity.CENTER_VERTICAL;
            lp.type = WindowManager.LayoutParams.TYPE_APPLICATION;
            lp.format = PixelFormat.TRANSPARENT;
        }
        if (menuFloat == null) {
            menuFloat = LayoutInflater.from(activity).inflate(R.layout.float_music_menu, null);
            menu = (FrameLayout) menuFloat.findViewById(R.id.id_float_music_menu);
            View musicMenu = LayoutInflater.from(context).inflate(R.layout.music_menu, menu, false);
            final ImageView userHeader = (ImageView) musicMenu.findViewById(R.id.id_mm_user);
            HttpUtils.getImage(ContentsValue.URLValues.USER_HEADER_URL, new HttpUtils.ImageCallback() {
                @Override
                public void getImage(Bitmap bitmap) {
                    userHeader.setImageBitmap(bitmap);
                }
            });
            ListView showFuc = (ListView) musicMenu.findViewById(R.id.id_mm_list);
            initMenu(showFuc);
            menu.addView(musicMenu);
            action = (TextView) menuFloat.findViewById(R.id.id_float_action);
            action.setOnClickListener(this);
        }
        if (isOpen) {
            menu.setVisibility(View.VISIBLE);
            lp.width = Utils.getScreenWidth(activity) * 2 / 3;
            lp.flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
            playInAni(menu);
        } else {
            menu.setVisibility(View.GONE);
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        }
        if (!isHadAddView) {
            manager.addView(menuFloat, lp);
            isHadAddView = true;
        } else {
            manager.updateViewLayout(menuFloat, lp);
        }
    }

    private void playInAni(View view) {
        Animation inAni = AnimationUtils.loadAnimation(context, R.anim.l2r);
        view.startAnimation(inAni);
    }

    private void playAniHide(View view) {
        Animation aniHide = AnimationUtils.loadAnimation(context, R.anim.float_action_hide);
        view.startAnimation(aniHide);
    }
    private void playAniShow(View view) {
        Animation aniShow = AnimationUtils.loadAnimation(context, R.anim.float_action_show);
        view.startAnimation(aniShow);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.id_float_action:
                if (isOpen) {
                    playAniHide(action);
                    closeMenu((Activity) context);
                } else {
                    playAniShow(action);
                    openMenu((Activity) context);
                }

                break;
        }
    }
}

