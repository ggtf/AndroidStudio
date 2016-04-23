package com.ggtf.specialmusicplayer.tools;

import android.content.Context;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ggtf at 2016/4/17
 * Author:ggtf
 * Time:2016/4/17
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */
public class Utils {
    private Utils() {

    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * MD5加密
     *
     * @param str 需要加密的字符串
     * @return 加密后输出的字符串
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();

        StringBuilder md5StrBuff = new StringBuilder();
        for (byte aByteArray : byteArray) {
            if (Integer.toHexString(0xFF & aByteArray).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & aByteArray));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & aByteArray));
        }
        //16位加密，从第9位到25位
        return md5StrBuff.substring(8, 24).toUpperCase();
    }
}
