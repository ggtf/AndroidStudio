package com.ggtf.listviewanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import com.ggtf.listviewanimation.adapters.TextAdapter;
import com.ggtf.listviewanimation.father.SelfAnimationListener;
import com.ggtf.listviewanimation.interfaces.AnimationEnd;
import com.ggtf.listviewanimation.interfaces.AnimationStart;
import com.ggtf.listviewanimation.models.TextShow;
import com.ggtf.listviewanimation.tools.ModelTools;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListView();
    }

    private void initListView() {
        ListView listView = (ListView) findViewById(R.id.list_view);
        List<TextShow> items = ModelTools.getTextShowSet(50);
        adapter = new TextAdapter(this, items, this);
        listView.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.list_item:
                deleteItem(v);
                addItem(v);
                break;
        }
    }

    private void addItem(View view) {
        startAnimationAdd(view);
    }

    private void startAnimationAdd(final View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.from_left);
        animation.setAnimationListener(new SelfAnimationListener(new AnimationStart() {
            @Override
            public void animationStart() {
                int position = (int) view.getTag();
                adapter.add(position, new TextShow("Orange" + position));
            }
        }));
        view.startAnimation(animation);
    }

    private void deleteItem(View v) {
        startAnimationDelete(v);
    }

    public void startAnimationDelete(final View view) {
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.to_right);
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.to_up);
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.to_bottom);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_rotate);
        animation.setAnimationListener(new SelfAnimationListener(new AnimationEnd() {
            @Override
            public void animationEnd() {
                int position = (int) view.getTag();
                adapter.remove(position);
            }
        }));
        view.startAnimation(animation);
    }

}
