package com.ggtf.banneradshow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ggtf.banneradshow.R;

/**
 * Created by ggtf at 2015/10/9
 * Author:ggtf
 * Time:2015/10/9
 * Email:15170069952@163.com
 * ProjectName:BannerADShow
 */
public class AFragment extends Fragment{

    public AFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_a,container,false);
        return view;
    }

}
