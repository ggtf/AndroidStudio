package com.ggtf.switchtabcustom;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        final RadioGroup group = (RadioGroup) findViewById(R.id.tab_container);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        List<Integer> imageResIds = new LinkedList<>();
        imageResIds.add(R.mipmap.kk);
        imageResIds.add(R.mipmap.mm);
        imageResIds.add(R.mipmap.ll);
        CommandViewPagerAdapter adapter = new CommandViewPagerAdapter(this,imageResIds);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton childAt = (RadioButton) group.getChildAt(position);
                childAt.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        RadioButton childAt = (RadioButton) group.getChildAt(1);
        childAt.setChecked(true);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.tab_hot:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.tab_new:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.tab_activity:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
    }
}
