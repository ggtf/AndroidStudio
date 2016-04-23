package com.ggtf.gridviewtraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int GRID_VIEW_ITEM_POSITION = 1;
    private Animation animation;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.grid_view);
        List<String> arrs = new LinkedList<>();
        for (int i = '1'; i <= '9'; i++) {
            arrs.add(String.valueOf((char) i));
        }
        GridViewAdapter adapter = new GridViewAdapter(this, arrs);
        gridView.setAdapter(adapter);
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_set);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        gridView.setLayoutAnimation(controller);
        controller.start();
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

    /**
     * GridView的Item按钮的点击事件
     *
     * @param view
     */
    public void btnOnClick(View view) {
        if (view instanceof Button) {
            int position = (int) view.getTag(R.id.grid_view);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_set);
            view.startAnimation(animation);
            Button number = (Button) view;
            String num = number.getText().toString();
            Toast.makeText(MainActivity.this, "number = " + num, Toast.LENGTH_SHORT).show();
            /**
             * 显示position位置Item被选中
             */
//            gridView.setSelection(position);
//            gridView.smoothScrollByOffset(10);
//            gridView.smoothScrollToPosition(20);
        }

    }


}
