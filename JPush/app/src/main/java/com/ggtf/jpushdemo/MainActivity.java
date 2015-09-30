package com.ggtf.jpushdemo;


import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = getIntent().getExtras();
            String json = bundle.getString(JPushInterface.EXTRA_EXTRA);
            try {
                JSONObject jsonObject = new JSONObject(json);
                String url = jsonObject.getString("url");
                Intent intentNetwork = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(url);
                intentNetwork.setData(uri);
                startActivity(intent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,com.example.jpushdemo.MainActivity.class);
        startActivity(intent);
        finish();
    }
}
