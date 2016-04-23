package com.ggtf.customview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.ggtf.customview.adapter.ListViewImageAdapter;

import java.util.LinkedList;
import java.util.List;

public class ListViewShowActivity extends AppCompatActivity {

    private List<Integer> imageResId;
    private ListViewImageAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_show);
        context = this;
        initListView();
    }

    private void initListView() {
        final ListView listView= (ListView) findViewById(R.id.list_view);
        listView.post(new Runnable() {
            @Override
            public void run() {
                imageResId = new LinkedList<>();
                imageResId.add(R.mipmap.aa);
                imageResId.add(R.mipmap.bb);
                imageResId.add(R.mipmap.cc);
                imageResId.add(R.mipmap.dd);
                imageResId.add(R.mipmap.ee);
                imageResId.add(R.mipmap.ff);
                imageResId.add(R.mipmap.hh);
                imageResId.add(R.mipmap.ii);
                imageResId.add(R.mipmap.jj);
                imageResId.add(R.mipmap.kk);
                imageResId.add(R.mipmap.ll);
                imageResId.add(R.mipmap.mm);
                adapter = new ListViewImageAdapter(context, imageResId,listView.getHeight());
                listView.setAdapter(adapter);
            }
        });


    }


}
