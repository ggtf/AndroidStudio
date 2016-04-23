package com.ggtf.androidtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] urls = {
            "http://p4.so.qhimg.com/bdr/_240_/t0196201526efcfc28e.jpg",
            "http://p3.so.qhimg.com/bdr/_240_/t01aa177536f1531ea3.jpg",
            "http://p0.so.qhimg.com/bdr/_240_/t01b503f0f7781da9c0.jpg",
            "http://p3.so.qhimg.com/bdr/_240_/t0112d557f0de5d4663.jpg",
            "http://p2.so.qhimg.com/bdr/_240_/t016860684d326e0f0c.jpg",
            "http://p2.so.qhimg.com/bdr/_240_/t01f9c059cc4244c047.jpg",
            "http://p2.so.qhimg.com/bdr/_240_/t01cc8430a8d671492c.jpg",
            "http://p3.so.qhimg.com/bdr/_240_/t01f59a6aa2a676b36d.jpg",
            "http://p2.so.qhimg.com/bdr/_240_/t01964827dd6974795c.jpg",
            "http://p0.so.qhimg.com/bdr/_240_/t01d6c7b1554efb81c0.gif",
            "http://p4.so.qhimg.com/bdr/_240_/t01105962b5ef959c6e.jpg",
            "http://p0.so.qhimg.com/bdr/_240_/t01a7288db01f2b25cf.jpg",
            "http://p1.so.qhimg.com/bdr/_240_/t01b53e121b40620001.jpg",
            "http://p4.so.qhimg.com/bdr/_240_/t0163b2e0d02e32036e.jpg",
            "http://p1.so.qhimg.com/bdr/_240_/t01f2334567ad07aec1.jpg",
            "http://p2.so.qhimg.com/bdr/_240_/t01b05c8ff54811261c.jpg",
            "http://p2.so.qhimg.com/bdr/_240_/t01ce9b5e5167560212.jpg",
            "http://p0.so.qhimg.com/bdr/_240_/t015ab723d2e4caff9f.jpg",
            "http://p3.so.qhimg.com/bdr/_240_/t012a710c85fec8cea8.jpg",
            "http://p3.so.qhimg.com/bdr/_240_/t014aca5391645b1c2d.jpg",
            "http://p0.so.qhimg.com/bdr/_240_/t014061f2a1f20277ff.jpg",
            "http://p0.so.qhimg.com/bdr/_240_/t0188a7c7316f17ae7a.jpg",
            "http://p1.so.qhimg.com/bdr/_240_/t017f322cbccc75a166.jpg",
            "http://p2.so.qhimg.com/bdr/_240_/t0158825a0e5beac7c2.jpg",
            "http://p3.so.qhimg.com/bdr/_240_/t018db674fc1afbeb58.jpg",
            "http://p0.so.qhimg.com/bdr/_240_/t01291bb89bf93aa820.jpg",
            "http://p0.so.qhimg.com/bdr/_240_/t010990b5cf6d4210ef.jpg",
            "http://p2.so.qhimg.com/bdr/_240_/t01e5c76cc71a330b42.jpg",
            "http://p4.so.qhimg.com/bdr/_240_/t01c30c33355fccd369.jpg",
            "http://p3.so.qhimg.com/bdr/_240_/t014b9435ebb2f42688.jpg",
            "http://p0.so.qhimg.com/bdr/_240_/t01474ff1defb69d95f.jpg",
            "http://p3.so.qhimg.com/bdr/_240_/t01c8f1398f610c1c58.jpg",
            "http://p4.so.qhimg.com/bdr/_240_/t018826dd7b3b32f374.jpg",
            "http://p0.so.qhimg.com/bdr/_240_/t016029247bee50b7aa.jpg"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListView();
        initXutils();
    }

    private void initListView() {
        ListView ls = (ListView) findViewById(R.id.ls);
        List<String> items = new LinkedList<>();
        initItems(items);
        LsAdapter adapter = new LsAdapter(this,items);
        ls.setAdapter(adapter);
    }

    private void initItems(List<String> items) {
        List<String> list = Arrays.asList(urls);
        for (int i = 0; i < 10; i++) {
            items.addAll(list);
        }

    }

    private void initXutils() {
        String url = "http://p1.so.qhimg.com/bdr/_240_/t01c2b79f26bfd86f46.jpg";

    }

    private void init() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Log.i("Info", "width = " + width);
        Log.i("Info", "height = " + height);
        int byteCount = bitmap.getByteCount();
        Log.i("Info", "byteCount = " + byteCount);

//        ByteArrayOutputStream osPng = new ByteArrayOutputStream();
//        boolean compressPng = bitmap.compress(Bitmap.CompressFormat.PNG, 100, osPng);
//        Log.i("Info","compressPng = "+compressPng);
//        int sizePng = osPng.size();
//        Log.i("Info", "sizePng = " + sizePng);
//        byte[] bytesPng = osPng.toByteArray();
//        int lengthPng = bytesPng.length;
//        Log.i("Info", "lengthPng = " + lengthPng);
//        ByteArrayInputStream isPng = new ByteArrayInputStream(bytesPng);
//        Bitmap bitmapPng = BitmapFactory.decodeStream(isPng);
//        int heightPng = bitmapPng.getHeight();
//        int widthPng = bitmapPng.getWidth();
//        Log.i("Info","widthPng = "+widthPng);
//        Log.i("Info","heightPng = "+heightPng + "\n");

//        ByteArrayOutputStream osPngHalf = new ByteArrayOutputStream();
//        boolean compressPngHalf = bitmap.compress(Bitmap.CompressFormat.PNG, 50, osPngHalf);
//        Log.i("Info","compressPngHalf = "+compressPngHalf);
//        int sizePngHalf = osPngHalf.size();
//        Log.i("Info", "sizePngHalf = " + sizePngHalf);
//        byte[] bytesPngHalf = osPngHalf.toByteArray();
//        int lengthPngHalf = bytesPngHalf.length;
//        Log.i("Info","lengthPngHalf = "+lengthPngHalf+"\n");

        ByteArrayOutputStream osJpeg = new ByteArrayOutputStream();
        boolean compressJpeg = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, osJpeg);
        Log.i("Info", "compressJpeg = " + compressJpeg);
        int sizeJpeg = osJpeg.size();
        Log.i("Info", "sizeJpeg = " + sizeJpeg);
        byte[] bytesJpeg = osJpeg.toByteArray();
        int lengthJpeg = bytesJpeg.length;
        Log.i("Info", "lengthJpeg = " + lengthJpeg);
        ByteArrayInputStream isJpeg = new ByteArrayInputStream(bytesJpeg);
        int available = isJpeg.available();
        Log.i("Info", "avaiable = " + available);
        Bitmap bitmapJpeg = BitmapFactory.decodeStream(isJpeg);
        int heightJpeg = bitmapJpeg.getHeight();
        int widthJpeg = bitmapJpeg.getWidth();
        Log.i("Info", "widthJpeg = " + widthJpeg);
        Log.i("Info", "heightJpeg = " + heightJpeg);
        int byteCountJpeg = bitmapJpeg.getByteCount();
        Log.i("Info", "byteCountJpeg = " + byteCountJpeg);

        ByteArrayOutputStream osJpegHalf = new ByteArrayOutputStream();
        boolean compressJpegHalf = bitmap.compress(Bitmap.CompressFormat.JPEG, 10, osJpegHalf);
        Log.i("Info", "compressJpegHalf = " + compressJpegHalf);
        int sizeJpegHalf = osJpegHalf.size();
        Log.i("Info", "sizeJpegHalf = " + sizeJpegHalf);
        byte[] bytesJpegHalf = osJpegHalf.toByteArray();
        int lengthJpegHalf = bytesJpegHalf.length;
        Log.i("Info", "lengthJpegHalf = " + lengthJpegHalf);
        ByteArrayInputStream isJpegHalf = new ByteArrayInputStream(bytesJpegHalf);
        int availableHalf = isJpegHalf.available();
        Log.i("Info", "availableHalf = " + availableHalf);
        Bitmap bitmapJpegHalf = BitmapFactory.decodeStream(isJpegHalf);
        int heightJpegHalf = bitmapJpegHalf.getHeight();
        int widthJpegHalf = bitmapJpegHalf.getWidth();
        Log.i("Info", "widthJpegHalf = " + widthJpegHalf);
        Log.i("Info", "heightJpegHalf = " + heightJpegHalf);
        int byteCountJpegHaf = bitmapJpegHalf.getByteCount();
        Log.i("Info", "byteCountJpegHalf = " + byteCountJpegHaf);


        try {
//            osPng.close();
//            osPngHalf.close();
            osJpeg.close();
//            isPng.close();
            isJpeg.close();
            isJpegHalf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
