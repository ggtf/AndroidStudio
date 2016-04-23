package com.ggtf.ttdtmusic.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ggtf.ttdtmusic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends Fragment{
    boolean isClick=false;

    public RecommendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        FloatingActionButton actionButton = (FloatingActionButton) view.findViewById(R.id.floating_action_button);
        actionButton.setClickable(true);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof FloatingActionButton){
                    FloatingActionButton button = (FloatingActionButton) v;
                    if (isClick){
                        isClick = false;
                        button.setRippleColor(Color.GREEN);
                    }else {
                        isClick = true;
                        button.setRippleColor(Color.BLUE);
                    }
                    Toast.makeText(getContext(), "我是悬浮按钮", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
