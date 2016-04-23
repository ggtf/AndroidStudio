package com.ggtf.specialmusicplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ggtf.specialmusicplayer.fragments.FoundFragment;
import com.ggtf.specialmusicplayer.fragments.MusicFragment;
import com.ggtf.specialmusicplayer.fragments.RecommendFragment;
import com.ggtf.specialmusicplayer.functions.actionbar.SwitchFuc;
import com.ggtf.specialmusicplayer.functions.fragment.SwitchFra;
import com.ggtf.specialmusicplayer.functions.menu.MenuItem;
import com.ggtf.specialmusicplayer.functions.menu.MusicMenu;
import com.ggtf.specialmusicplayer.models.parse.Singer;
import com.ggtf.specialmusicplayer.models.parse.Song;
import com.ggtf.specialmusicplayer.network.HttpUtils;
import com.ggtf.specialmusicplayer.tools.BeJson;
import com.ggtf.specialmusicplayer.tools.ContentsValue;
import com.ggtf.specialmusicplayer.tools.Logs;

import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private float oldX;
    private boolean moveToLeft;
    private ImageView left;
    private ImageView center;
    private ImageView right;
    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_main);
        Logs.launchLogs(this);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
//        toShowAni();
        toShowAni1();

//        setContentView(R.layout.activity_main);
    }

    private void toShowAni1() {

        FrameLayout actionBarContainer = (FrameLayout) findViewById(R.id.id_action_bar);
        SwitchFuc switchFuc = new SwitchFuc(actionBarContainer);
        ViewPager viewPager = (ViewPager) findViewById(R.id.id_view_pager);
        List<Fragment> fragments = new LinkedList<>();
        fragments.add(new FoundFragment());
        fragments.add(new MusicFragment());
        fragments.add(new RecommendFragment());
        FragmentManager manager = getSupportFragmentManager();
        SwitchFra switchFra = new SwitchFra(this, manager, viewPager, fragments);
        switchFra.boundSwitchFuc(switchFuc);

        List<MenuItem> subFuc = MusicMenu.createMenuItemSet(this);
        MusicMenu musicMenu = new MusicMenu(this, subFuc);
        musicMenu.showActivityFloatWindow(this, false);
    }

    private void toShowAni() {
        FrameLayout actionBarContainer = (FrameLayout) findViewById(R.id.id_action_bar);
        left = (ImageView) findViewById(R.id.id_left);
        center = (ImageView) findViewById(R.id.id_center);
        right = (ImageView) findViewById(R.id.id_right);
        actionBarContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i("Info", "ACTION_DOWN");
                        oldX = event.getRawX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.i("Info", "ACTION_MOVE");
                        float distance = event.getRawX() - oldX;
                        if (distance > 0) {
                            moveToLeft = false;
                        } else {
                            moveToLeft = true;
                        }
                        playAni(Math.abs(distance));
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i("Info", "ACTION_UP");
                        toBeNormal();
                        break;
                }
                return false;
            }
        });

    }

    private void toBeNormal() {
        left.setScaleX(1);
        left.setScaleY(1);
        center.setScaleX(1);
        center.setScaleY(1);
        right.setScaleX(1);
        right.setScaleY(1);
    }

    private void playAni(float distance) {
        float scale = distance / screenWidth * 2;
        Log.i("Info", "scale = " + scale);
        float scaleSmall = (1 - scale) > 0.3f ? (1 - scale) : 0.3f;
        float scaleBig = (1 + scale * 1.3f) < 1.7f ? (1 + scale * 1.3f) : 1.7f;
        left.setScaleX(scaleSmall);
        left.setScaleY(scaleSmall);
        center.setScaleX(scaleBig);
        center.setScaleY(scaleBig);
        right.setScaleX(scaleSmall);
        right.setScaleY(scaleSmall);

        if (moveToLeft) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) left.getLayoutParams();
            params.rightMargin = -30;
            left.setLayoutParams(params);
        } else {
        }
    }

    private void toTestMenu() {
//        JSONObject
        String requestParams = "name=Apple&age=24&isMan=true&home=江西";
        String urlStr = ContentsValue.URLValues.URL_PRE + "/servlet/SongsServlet";
        byte[] params = URLEncoder.encode(requestParams).getBytes();
//        byte[] params = requestParams.getBytes();
        HttpUtils.getJsonByPost(this, urlStr, params, new HttpUtils.RequestStatusListener() {
            @Override
            public void backValue(byte[] values) {
                if (values != null) {
                    String json = new String(values);
                    boolean empty = TextUtils.isEmpty(json);
                    Log.i("Info", "empty = " + empty);
                    if (!empty) {
                        Log.i("Info", "json = " + json);
                    }
                }
            }

            @Override
            public void catchException(Throwable throwable) {
                Log.i("Info", "exception = ", throwable);
            }
        });
    }

    private void toTestGson() {
        List<Song> typicalSongs = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            List<String> singers = new LinkedList<>();
            List<String> composers = new LinkedList<>();
            List<String> songWriters = new LinkedList<>();
            List<String> categories = new LinkedList<>();
            singers.add("Apple");
            composers.add("Apple" + i);
            songWriters.add("Apple" + (i * 3));
            categories.add("伤感" + i);
            Song song = new Song("水果", singers, composers, songWriters, 3 * 60 * 1000, categories, "90");
            typicalSongs.add(song);
        }
        Singer singer = new Singer("Apple", true, 24, "江西南昌", 0, "A", typicalSongs);
        String json = BeJson.getInstance().getJsonByObject(singer);
        Log.i("Info", "json  = " + json);

        Singer singerNew = BeJson.getInstance().getObjectByJson(json, Singer.class);
        boolean sex = singerNew.isSex();
        Log.i("Info", "sex = " + sex);
    }

    public void btnClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.id_show_menu:
                toTestMenu();
                break;
        }
    }
}
