package com.ggtf.specialmusicplayer.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.ggtf.specialmusicplayer.R;
import com.ggtf.specialmusicplayer.storages.FileManager;
import com.ggtf.specialmusicplayer.tools.Logs;
import com.ggtf.specialmusicplayer.tools.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ggtf at 2016/4/11
 * Author:ggtf
 * Time:2016/4/11
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */
public class HttpUtils {
    private static final int CONNECT_TIMEOUT = 5;
    private static final int READ_TIMEOUT = 15;
    private static final int MAX_SCALE = 8;
    private static final int BASE_DATA_LENGTH = 128;
    private static final String POST = "POST";
    private static final String GET = "GET";
    public static Handler handler;

    private HttpUtils() {

    }

    public static void setContext(@NonNull Context con) {
        if (handler == null)
            handler = new Handler(con.getMainLooper());
    }

    /**
     * 依据请求数据的长度来获取字节数组的大小比例
     *
     * @param contentLength 获取的数据长度
     * @return 比例
     */
    public static int getScale(long contentLength) {
        int scale = 1;
        if (contentLength > 0) {
            int scaleValue = (int) (contentLength / BASE_DATA_LENGTH);
            scale = scaleValue < MAX_SCALE ? scaleValue : MAX_SCALE;
        }
        return scale;

    }

    public static void getJson(
            String url,
            String requestMethod,
            byte[] params,
            int connectTimeout,
            int readTimeout) {

    }

    public static HttpURLConnection getUrlConnection(
            String urlLink,
            String requestMethod,
            byte[] params,
            int connectTime,
            int readTimeout,
            NetworkListener networkListener) {
        String encode = Uri.encode(urlLink);
        try {
            URL url = new URL(encode);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setConnectTimeout(connectTime * 1000);
            urlConnection.setReadTimeout(readTimeout * 1000);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestMethod(requestMethod);
            if (requestMethod.equals(POST) && params != null) {
//                TODO post方式提交数据

                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_PARTIAL) {
                    networkListener.networkSuccessful();
                    return urlConnection;
                }
                return null;
            } else if (requestMethod.equals(GET)) {
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    networkListener.networkSuccessful();
                    return urlConnection;
                }
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            networkListener.networkFailed(e);
        }
        return null;
    }

    public static void getImage(final String imageUrl, final ImageCallback callback) {
        Log.i("Info", "getImage");
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imageUrl);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setDoInput(true);
                    int responseCode = urlConnection.getResponseCode();
                    Log.i("Info", "responseCode =" + responseCode);
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = urlConnection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        callback.getImage(bitmap);
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i("Info", "IOException ", e);
                }
            }
        });
    }

    public static void getJsonByPost(@NonNull Context context, @NonNull final String urlStr, @NonNull final byte[] params, @NonNull final RequestStatusListener listener) {
        setContext(context);
        Executors.newFixedThreadPool(1).submit(new Runnable() {
            @Override
            public void run() {
                try {
//                    String spec = Uri.encode(urlStr);
                    URL url = new URL(urlStr);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.setConnectTimeout(5 * 1000);
                    urlConnection.setReadTimeout(20 * 1000);
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");// 请求头, 必须设置
                    urlConnection.setRequestProperty("Content-Length", params.length + "");// 注意是字节长度, 不是字符长度
                    urlConnection.setUseCaches(false);
                    urlConnection.getOutputStream().write(params);
                    urlConnection.connect();
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream stream = urlConnection.getInputStream();
                        int contentLength = urlConnection.getContentLength();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int scale = getScale(contentLength);
                        byte[] arr = new byte[BASE_DATA_LENGTH * scale];
                        int len = 0;
                        while ((len = stream.read(arr)) != -1) {
                            baos.write(arr, 0, len);
                        }
                        stream.close();
                        arr = null;
                        final byte[] values = baos.toByteArray();
                        int length = values.length;
                        Log.i("Info", "length = " + length);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.backValue(values);
                            }
                        });
                        baos.close();
                    }
                    urlConnection.disconnect();
                } catch (IOException e) {
                    listener.catchException(e);
                }
            }
        });


    }

    public interface NetworkListener {
        void networkFailed(Throwable ex);

        void networkSuccessful();
    }

    public interface ImageCallback {
        void getImage(Bitmap bitmap);
    }

    public interface RequestStatusListener {
        void backValue(byte[] values);

        void catchException(Throwable throwable);
    }

    /**
     * 给某一ImageView视图设置图标
     * 先从文件中读取，如若未读取到再到网络服务器上读取
     *
     * @param context 上下文
     * @param urlStr  图片链接
     * @param view    ImageView
     */
    public static void setImage(final Context context, final String urlStr, final ImageView view) {
        if (urlStr != null && view != null && context != null) {
            final String fileName = Utils.getMD5Str(urlStr);
            final File dir = FileManager.createImageCacheDir(context);
            Bitmap imageFromFile = FileManager.getImageFromFile(fileName, dir);
            if (imageFromFile != null) {
                Log.i("Info", "from file");
                view.setImageBitmap(imageFromFile);
                return;
            }
            Executors.newFixedThreadPool(1).submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        setContext(context);
                        Log.i("Info", "from nework");
                        URL url = new URL(urlStr);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setDoInput(true);
//                      urlConnection.setDoOutput(true);
                        urlConnection.setConnectTimeout(5 * 1000);
                        urlConnection.setReadTimeout(20 * 1000);
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setUseCaches(false);
                        urlConnection.connect();
                        int responseCode = urlConnection.getResponseCode();
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            InputStream is = urlConnection.getInputStream();
                            final Bitmap bitmap = BitmapFactory.decodeStream(is);
                            File file = FileManager.createFile(fileName, dir);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(file));
                            is.close();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    view.setImageBitmap(bitmap);
                                }
                            });

                        } else {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    view.setImageResource(R.mipmap.banner_bg);
                                }
                            });
                            Logs.write2Logfile("responseCode err " + responseCode);
                        }
                        urlConnection.disconnect();


                    } catch (IOException e) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.setImageResource(R.mipmap.banner_bg);
                            }
                        });
                        Logs.write2Logfile(e);
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
