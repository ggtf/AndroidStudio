package com.ggtf.customviewxindiantu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        HeartBeatingView heartBeatingView = new HeartBeatingView(this);
//        byte[] data = new byte[100];
//        for (int i = 0; i < data.length; i++) {
//            if (i % 6 == 0) {
//                data[i] = 25;
//            } else {
//                if ((i) % 6 == 1) data[i] = -25;
//                else data[i] = 0;
//
//
//            }
//        }
//        heartBeatingView.setLocalData(data);
//        setContentView(heartBeatingView);
        MyButton myButton = new MyButton(this);
        setContentView(myButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
