package com.ggtf.banneradshow;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.ggtf.banneradshow.adapter.FragmentAdapter;
import com.ggtf.banneradshow.fragments.AFragment;
import com.ggtf.banneradshow.fragments.BFragment;
import com.ggtf.banneradshow.fragments.CFragment;
import com.ggtf.banneradshow.models.NoParseObject;
import com.ggtf.libraries.CustomViews.CircleImageView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> fragments;
    private FragmentAdapter adapter;
    private Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg != null) {
                int what = msg.what;
                switch (what) {
                    case 0:
                        int position = msg.arg1;
                        if (msg.obj instanceof NoParseObject) {
                            NoParseObject noParseObject = (NoParseObject) msg.obj;
                            noParseObject.getViewPager().setCurrentItem(position);
                            GridView gridView = noParseObject.getGridView();
                            for (int i = 0; i < gridView.getChildCount(); i++) {
                                View childAt = gridView.getChildAt(i);
                                if (childAt instanceof FrameLayout){
                                    FrameLayout frameLayout = (FrameLayout) childAt;
                                    View viewById = frameLayout.findViewById(R.id.ad_point_position);
                                    if (viewById instanceof ImageView){
                                        ImageView imageView = (ImageView) viewById;
                                        imageView.setImageResource(R.drawable.ad_position_point_normal);
                                        if (position == i){
                                            imageView.setImageResource(R.drawable.ad_position_point_checked);
                                        }
                                    }
                                }
                            }


                        }
                        break;
                }
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();
        fragments.addAll(initFragments());
        adapter.notifyDataSetChanged();



    }

    private List<Fragment> initFragments() {
        List<Fragment> fragments = new LinkedList<>();
        fragments.add(new AFragment());
        BFragment bFragment = new BFragment();
//        设置Fragment的参数;
        Bundle bundle = new Bundle();
        Message message = new Message();
        message.obj = mainHandler;
        bundle.putParcelable("handler", message);
        bFragment.setArguments(bundle);
//        bFragment.setMainHandler(mainHandler);
        fragments.add(bFragment);
        fragments.add(new CFragment());
        return fragments;
    }


    private void initViewPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        FragmentManager manager = getSupportFragmentManager();
        fragments = new LinkedList<>();
        adapter = new FragmentAdapter(manager, fragments);
        viewPager.setAdapter(adapter);
    }

}
