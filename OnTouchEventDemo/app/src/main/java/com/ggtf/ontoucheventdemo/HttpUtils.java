package com.ggtf.ontoucheventdemo;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ggtf at 2015/10/28
 * Author:ggtf
 * Time:2015/10/28
 * Email:15170069952@163.com
 * ProjectName:OnTouchEventDemo
 */
public class HttpUtils {
    private HttpUtils() {

    }

    /**
     * Post方式提交数据,接收服务器返回的数据字节数组
     */
    public static byte[] sendByPost(String path, HashMap<String, String> params, String encode) throws IOException {
        byte[] result = null;
//        先把要提交的每对数据封装成NameValuePair类型的(httpcore-4.1.4jar)
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
//        把要提交的数据组织成提交的格式;
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, encode);
//        创建要提交方式对象
        HttpPost post = new HttpPost(path);
        post.setEntity(entity);
//        创建执行提交的对象
        HttpClient client = new DefaultHttpClient();
//        执行提交
        HttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = response.getEntity().getContent();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] arr = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(arr)) != -1) {
                bout.write(arr,0,len);
            }
            result = bout.toByteArray();
            inputStream.close();
        }

        return result;
    }

    public static InputStream getInputStream(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setDoInput(true);
        InputStream inputStream = null;
        if (conn.getResponseCode()==HttpURLConnection.HTTP_OK){
            inputStream = conn.getInputStream();
        }
        return inputStream;
    }

    public static byte[] post(String path,HashMap<String,String> params,String encode) throws IOException {
        byte[] result = null;
//        将要提交的数据取出拼接成字符串格式;
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<String,String> entry:params.entrySet()){
            builder.append(entry.getKey())
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue(),encode))
                    .append("&");
        }
//        删除最后的&符号
        builder.deleteCharAt(builder.length()-1);
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(5000);
        conn.setDoInput(true);
//        设置可以向服务器端发送数据;
        conn.setDoOutput(true);
//        设置提交的数据类型
        conn.setRequestProperty("Content-Type"," application/x-www-form-urlencoded");
//        设置提交的数据长度
        byte[] arr = builder.toString().getBytes(encode);
        conn.setRequestProperty("Content-Length",String.valueOf(arr.length));
//        向服务端提交数据
        OutputStream out = conn.getOutputStream();
        out.write(arr, 0, arr.length);
        out.close();
//        读取服务器端返回的数据
        InputStream in=null;
        ByteArrayOutputStream bos=null;
        if (conn.getResponseCode()==HttpURLConnection.HTTP_OK){
            in = conn.getInputStream();
            bos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = in.read(bytes))!=-1){
                bos.write(bytes,0,len);
            }
            in.close();
            result = bos.toByteArray();
        }
        return result;
    }


}
