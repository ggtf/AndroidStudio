package com.ggtf.cryptbase64;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipFile;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usingBase64();
//        usingURLEncoding();
//        usingZipFile();
//        usingGZIP();
    }

    private void usingBase64(){
        /**
         * Base64 编码后的"=",通常在字符串末尾,作为空数据字节的填充,解码的时候
         * 不进行处理,因此最后的"="
         */
        byte[] data = new byte[0];
        try {
            data = "I 爱 Android!".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /**
         * 编码
         * 参数1:byte[] 字节数组;需要编码的数据
         * 参数2:int flag;编码的控制;NO_WRAP(输出一行)
         * return: String 编码后的一个字符串
         */
        String str = Base64.encodeToString(data, Base64.NO_WRAP);
        Log.i("Info", "Base64.encode = " + str);
        /**
         * 解码 将Base64的编码数据 还原为原始数据
         * 参数1: 字符串
         * 参数2: 标志,与编码时的标志一致;
         */
        byte[] decode = Base64.decode("SSDniLEgQW5kcm9pZA==", Base64.NO_WRAP);
        str = new String(decode);
        Log.i("Info", "Base64.decode = " + str);
    }
    private void usingURLEncoding(){
        try {
            /**
             * 编码;
             * 参数1:需要被编码的字符串
             * 参数2:字符格式集
             */
            String encode = URLEncoder.encode("变形金刚", "UTF-8");
            Log.i("Info","URLEncoder.encode = "+encode);

            /**
             * 解码:
             * 参数1:被编码后的字符串
             * 参数2:字符格式集 与编码时的格式集一致;
             */
            String decode = URLDecoder.decode("I+LOVE+ANDROID", "UTF-8");
            Log.i("Info","URLEncoder.decode = "+decode);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    private void usingZipFile(){
//        ZipFile

    }
    private void usingGZIP(){
//        GZIPInputStream 读压缩数据
//        GZIPOutputStream 写压缩数据

    }

    /*
        加密算法
        1.对称加密: 加解密同一个密码
            1.1 DES 8个字节64bit长度:"分块加密"
            1.2 AES
        2.非对称加密: 加密密钥 与 解密密钥 是不同的;
     */
    private void usingDES(){

    }

    public void btnJump(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_des:
                Intent intent = new Intent(this,DesActivity.class);
                startActivity(intent);
                break;
        }
    }
}
