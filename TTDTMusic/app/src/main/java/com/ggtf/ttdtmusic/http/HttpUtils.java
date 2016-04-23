package com.ggtf.ttdtmusic.http;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ggtf at 2015/10/24
 * Author:ggtf
 * Time:2015/10/24
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class HttpUtils {
    private HttpUtils() {

    }

    public static byte[] getNetworkData(String path) throws IOException {
        byte[] result = null;
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setConnectTimeout(5000);

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream in = connection.getInputStream();
            int contentLength = connection.getContentLength();
            Log.i("Info", "contentLength =" + contentLength);
            ByteArrayOutputStream bout=new ByteArrayOutputStream();
            byte[] arr = new byte[1024];
            int len = 0;
            while ((len=in.read(arr))!=-1){
                bout.write(arr,0,len);
            }
            in.close();
            arr = null;
            result = bout.toByteArray();
        }
        return result;
    }
}
