package com.ggtf.smartscale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
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
        RefreshListView refreshListView = (RefreshListView) findViewById(R.id.refresh_listview);
        List<String> items = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            items.add("Apple"+i);
        }
        ListAdapter adapter = new ListAdapter(this,items);
        refreshListView.setAdapter(adapter);
    }

    private float dw = 320f;
    private float dh = 480f;
    private final String WTemplate = "<dimen name=\"x{0}\">{1}px</dimen>\n";
    private final String HTemplate = "<dimen name=\"y{0}\">{1}px</dimen>\n";
    private String rootPath;

    private void initValues() {
        File filesDir = getFilesDir();
        File fileValues = new File(filesDir,"values-{0}x{1}");
        boolean mkdir = fileValues.mkdir();
        if (mkdir){
            rootPath = fileValues.getAbsolutePath();
            Log.i("Info","rootPath = "+rootPath);
            makeString(320, 480);
            makeString(480,800);
            makeString(480, 854);
            makeString(540, 960);
            makeString(600, 1024);
            makeString(720, 1184);
            makeString(720, 1196);
            makeString(720, 1280);
            makeString(768, 1024);
            makeString(800, 1280);
            makeString(1080, 1812);
            makeString(1080, 1920);
            makeString(1440, 2560);
        }
    }

    public  void makeString(int w, int h) {

        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<resources>");
        float cellw = w / dw;
        for (int i = 1; i < 320; i++) {
            sb.append(WTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellw * i) + ""));
        }
        sb.append(WTemplate.replace("{0}", "320").replace("{1}", w + ""));
        sb.append("</resources>");

        StringBuffer sb2 = new StringBuffer();
        sb2.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb2.append("<resources>");
        float cellh = h / dh;
        for (int i = 1; i < 480; i++) {
            sb2.append(HTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellh * i) + ""));
        }
        sb2.append(HTemplate.replace("{0}", "480").replace("{1}", h + ""));
        sb2.append("</resources>");

        String path = rootPath.replace("{0}", h + "").replace("{1}", w + "");
        File rootFile = new File(path);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File layxFile = new File(path + "lay_x.xml");
        File layyFile = new File(path + "lay_y.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sb.toString());
            pw.close();
            pw = new PrintWriter(new FileOutputStream(layyFile));
            pw.print(sb2.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static float change(float a) {
        int temp = (int) (a * 100);
        return temp / 100f;
    }

    private void init() {
        float dimension = getResources().getDimension(R.dimen.dimen);
        float density = getResources().getDisplayMetrics().density;
        int densityDpi = getResources().getDisplayMetrics().densityDpi;
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        int heightPixels = getResources().getDisplayMetrics().heightPixels;

        Log.i("Info", "dimension = " + dimension);
        Log.i("Info", "density = " + density);
        Log.i("Info", "densityDpi = " + densityDpi);
        Log.i("Info", "widthPixels = " + widthPixels);
        Log.i("Info", "heightPixels = " + heightPixels);
    }


}
