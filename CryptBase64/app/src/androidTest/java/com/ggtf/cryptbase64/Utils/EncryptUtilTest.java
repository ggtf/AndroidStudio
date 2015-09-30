package com.ggtf.cryptbase64.Utils;

import android.util.Log;

import junit.framework.TestCase;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by ggtf at 2015/9/30
 * Author:ggtf
 * Time:2015/9/30
 * Email:15170069952@163.com
 * ProjectName:CryptBase64
 */
public class EncryptUtilTest extends TestCase {
    public void testGenRSAkey(){
        KeyPair keyPair = EncryptUtil.genRSAKey(1024);
        assertNotNull(keyPair);

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        Log.i("Info","publicKey = "+publicKey);
        Log.i("Info","privateKey = "+privateKey);
    }
}
