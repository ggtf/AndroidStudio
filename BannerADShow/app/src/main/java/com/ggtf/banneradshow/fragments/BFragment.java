package com.ggtf.banneradshow.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.ggtf.banneradshow.R;
import com.ggtf.banneradshow.adapter.AdPointAdapter;
import com.ggtf.banneradshow.adapter.CommonViewPagerAdapter;
import com.ggtf.banneradshow.models.NoParseObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggtf at 2015/10/9
 * Author:ggtf
 * Time:2015/10/9
 * Email:15170069952@163.com
 * ProjectName:BannerADShow
 */
public class BFragment extends Fragment{

    private View fragmentView;
    private List<Integer> imageResId;
    private CommonViewPagerAdapter adapter;
    private ViewPager viewPager;
    private Thread thread;
    private Handler mainHandler;
    private boolean isRunning = false;
    private GridView gridView;
    private List<Integer> imagesPosition;
    private AdPointAdapter adPointAdapter;

    public BFragment() {
    }

    public void setMainHandler(Handler mainHandler) {
        this.mainHandler = mainHandler;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        Parcelable parHandler = arguments.getParcelable("handler");
        if (parHandler instanceof Message){
            Message message = (Message) parHandler;
            if (message.obj instanceof Handler){
                mainHandler = (Handler) message.obj;
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        thread = new Thread(new Runnable() {
            private boolean isSort;
            @Override
            public void run() {
                while (!isRunning){
                    try {
                        if (!isSort){
                            for (int i = 0; i < imageResId.size(); i++) {
                                Thread.sleep(1000);
                                Message message = mainHandler.obtainMessage(0);
                                message.arg1 = i;
                                message.obj = new NoParseObject(gridView,viewPager);
                                mainHandler.sendMessage(message);
                            }
                            isSort = true;
                        }else {
                            for (int i = imageResId.size()-2; i> 0; i--) {
                                Thread.sleep(1000);
                                Message message = mainHandler.obtainMessage(0);
                                message.arg1 = i;
                                message.obj = new NoParseObject(gridView,viewPager);
                                mainHandler.sendMessage(message);
                            }
                            isSort = false;
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_b,container,false);
        fragmentView = view;
        initSelfViewPager();
        imageResId.addAll(initImageResId());
        adapter.notifyDataSetChanged();
        gridView.setNumColumns(imageResId.size());
        for (int i = 0; i < imageResId.size(); i++) {
            imagesPosition.add(imageResId.get(i));
        }
        adPointAdapter.notifyDataSetChanged();
        return view;
    }



    private List<Integer> initImageResId() {
        int[] imageResId = {R.mipmap.ad01, R.mipmap.ad02,R.mipmap.ad03,
                R.mipmap.ad04,R.mipmap.ad05,R.mipmap.ad06,R.mipmap.ad07,R.mipmap.ad08};

        List<Integer> imageResIds = new LinkedList<>();
        for (int i = 0; i < imageResId.length; i++) {
            imageResIds.add(imageResId[i]);
        }
        return imageResIds;
    }

    private void initSelfViewPager() {
        viewPager = (ViewPager) fragmentView.findViewById(R.id.view_pager_fragment);
        imageResId = new LinkedList<>();
        adapter = new CommonViewPagerAdapter(getContext(),imageResId);
        viewPager.setAdapter(adapter);

        gridView = (GridView) fragmentView.findViewById(R.id.grid_view_ad_point);
        imagesPosition = new LinkedList<>();
        adPointAdapter = new AdPointAdapter(getContext(), imagesPosition);
        gridView.setAdapter(adPointAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        thread.start();
    }

    @Override
    public void onDestroyView() {
        isRunning = true;
        super.onDestroyView();
    }
}
