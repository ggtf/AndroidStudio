package com.ggtf.sharesdkapp;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShareSDK.initSDK(this);
    }

    /**
     * 获取QQ空间
     * 获取平台授权数据
     */
    private void getAccess(){
        Platform qZone = ShareSDK.getPlatform(this, QZone.NAME);
        String accessToken = qZone.getDb().getToken();
        String userId = qZone.getDb().getUserId();
        String nickname = qZone.getDb().get("nickname");

        /**
         * D86FC54B1FBBEF22810F51384C4AC7AF
         */
        Log.i("Info", "accessToken = " + accessToken);
        /**
         * 861F4BC0AB2BECA3B42563D42251DB9E
         */
        Log.i("Info", "useId = " + userId);
        Log.i("Info", "nickname = " + nickname);

    }
    private void isAccessibility(){
        Platform qZone = ShareSDK.getPlatform(this,QZone.NAME);
        /**
         * 检测是否授权
         */
        if (qZone.isValid()){
            /**
             * 取消授权
             */
//            qZone.removeAccount();
            Log.i("Info","isValid()");
            getAccess();
        }else {
            Log.i("Info"," ! isValid()");
        }
        qZone.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                Log.i("Info", "onComplete");
                String name = platform.getName();
                Log.i("Info", "name = " + name);
                Set<String> keys = hashMap.keySet();
                for (String key : keys) {
                    Log.i("Info", "key = " + key);
                }
                Collection<Object> values = hashMap.values();
                for (Object obj : values) {
                    Log.i("Info", "values = " + obj.toString());
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.i("Info", "onError");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.i("Info", "onCancel");

            }
        });
        qZone.authorize();
//        Platform.ShareParams shareParams = new QZone.ShareParams();
//        qZone.share(shareParams);
        qZone.SSOSetting(true);
//        qZone.showUser("1207182337");
        qZone.showUser("1003443286");
    }

    /*private void authorize(Platform plat) {
        if (plat == null) {
            popupOthers();
            return;
        }
//判断指定平台是否已经完成授权
        if(plat.isAuthValid()) {
            String userId = plat.getDb().getUserId();
            if (userId != null) {
                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
                login(plat.getName(), userId, null);
                return;
            }
        }
        plat.setPlatformActionListener(this);
        // true不使用SSO授权，false使用SSO授权
        plat.SSOSetting(true);
        //获取用户资料
        plat.showUser(null);
    }*/

    private void isAccessibilityInWeChat(){
        Platform weChat = ShareSDK.getPlatform(this, Wechat.NAME);
        /**
         * 检测是否授权
         */
        if (weChat.isValid()){
            /**
             * 取消授权
             */
//            weChat.removeAccount();
            Log.i("Info","isValid()");
        }else {
            Log.i("Info"," ! isValid()");
        }
        weChat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                Log.i("Info", "onComplete");
                String name = platform.getName();
                Log.i("Info", "name = " + name);
                Set<String> keys = hashMap.keySet();
                for (String key : keys) {
                    Log.i("Info", "key = " + key);
                }
                Collection<Object> values = hashMap.values();
                for (Object obj : values) {
                    Log.i("Info", "values = " + obj.toString());
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.i("Info", "onError");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.i("Info", "onCancel");

            }
        });
        weChat.authorize();
//        Platform.ShareParams shareParams = new weChat.ShareParams();
//        weChat.share(shareParams);
        weChat.SSOSetting(true);
//        weChat.showUser("1207182337");
        weChat.showUser("1003443286");
    }

    /**
     * 点击分享--
     *
     * @param view
     */
    public void btnShare(View view) {
        showShare();
    }

    /**
     * 分享方法调用
     */
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//         oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片

//        HashMap<String, Object> shareParamsMap = oks.getShareParamsMap();
//        int size = shareParamsMap.size();
//        Log.i("Info","size = "+size);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File images = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File[] files = images.listFiles();
//            List<String> imageArray = new LinkedList<>();

            if (files.length>0){
                File[] files1 = files[1].listFiles();
                if (files1.length>0){
                    for (int i = 0; i < files1.length; i++) {
                        String imagePath = files1[i].getAbsolutePath();
                        if (imagePath.endsWith(".png")){
                            Log.i("Info", "imagePath = " + imagePath);
//                            oks.setImageArray();
//                            imageArray.add(imagePath);
                            oks.setImagePath(imagePath);//确保SDcard下面存在此张图片
                            if (i>2){
//                                String[] arr = new String[imageArray.size()];
//                                oks.setImageArray(imageArray.toArray(arr));
                                break;
                            }
                        }

                    }
                }
            }
        }
//        oks.setImageUrl("http://img5.imgtn.bdimg.com/it/u=1375472312,1897902111&fm=21&gp=0.jpg");
//         oks.setImagePath("/storage/emulated/0/Pictures/Screenshots/Screenshot_2015-11-02-00-21-13.png");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }

    public void login(View view) {
        isAccessibility();
//        isAccessibilityInWeChat();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK(this);
    }
}
