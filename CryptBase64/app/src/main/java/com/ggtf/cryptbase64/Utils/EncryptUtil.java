package com.ggtf.cryptbase64.Utils;

/**
 * Created by ggtf at 2015/9/30
 * Author:ggtf
 * Time:2015/9/30
 * Email:15170069952@163.com
 * ProjectName:CryptBase64
 */

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加解密以及常见的编码 算法集合
 */
public class EncryptUtil {
    private EncryptUtil() {

    }

    /**
     * DES 加密
     *
     * @param data
     * @param password
     * @return
     */
    public static byte[] desEncrypt(byte[] data, byte[] password) {
        byte[] ret = null;
        if (data != null && password != null && password.length == 8) {
            try {
                Cipher cipher = Cipher.getInstance("DES");
                DESKeySpec desKeySpec = new DESKeySpec(password);
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                SecretKey key = keyFactory.generateSecret(desKeySpec);
                cipher.init(Cipher.ENCRYPT_MODE, key);
                ret = cipher.doFinal();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    /**
     * DES 解密
     *
     * @param encryptedData 加密后的得到的字节数组
     * @param password
     * @return
     */
    public static byte[] desDecrypt(byte[] encryptedData, byte[] password) {
        byte[] ret = null;
        if (encryptedData != null && password != null && password.length == 8) {
            try {
                Cipher cipher = Cipher.getInstance("DES");
                DESKeySpec desKeySpec = new DESKeySpec(password);
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                SecretKey key = keyFactory.generateSecret(desKeySpec);
                cipher.init(Cipher.DECRYPT_MODE, key);
                ret = cipher.doFinal();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

//    ASE 方法1 算法选择，无模式和填充的情况；也就是Cipher.getInstance("AES") 简单方式

    /**
     * ASE 加密
     *
     * @param data
     * @param password
     * @return
     */
    public static byte[] aesEncryptSimple(byte[] data, byte[] password) {
        byte[] ret = null;
        if (data != null && password != null && password.length == 16) {//128bit
            try {
                Cipher cipher = Cipher.getInstance("AES");
//                AES 简单模式 Key 的创建；
                SecretKeySpec secretKeySpec = new SecretKeySpec(password, "AES");
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
                ret = cipher.doFinal(data);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }

        }
        return ret;

    }

    /**
     * ASE 解密
     *
     * @param data
     * @param password
     * @return
     */
    public static byte[] aesDecryptSimple(byte[] data, byte[] password) {
        byte[] ret = null;
        if (data != null && password != null && password.length == 16) {
            try {
                Cipher cipher = Cipher.getInstance("AES");
                SecretKeySpec secretKeySpec = new SecretKeySpec(password, "AES");
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
                ret = cipher.doFinal(data);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }

        }
        return ret;
    }

    //    AES 方法2 CBC模式，采用Iv向量的方式

    /**
     * 使用"AES/模式/填充"这种加密模式的AES
     * 需要使用 Iv向量来设置
     * @param data
     * @param password
     * @param ivData
     * @return
     */
    public static byte[] aseEncryptRecommend(byte[] data, byte[] password,byte[] ivData) {
        byte[] ret = null;
//        ivData长度至少16个字节，超过16个字节，也会取前16位
        if (data != null && password != null && password.length == 16 && ivData!=null && ivData.length>=16) {
            try {
//                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS/Padding");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//                1.准备AES密钥(第一套密码)
                SecretKeySpec key = new SecretKeySpec(password,"AES");
//                2.准备CBC模式的向量Iv参数（可以认为是第二套密码）
                IvParameterSpec iv = new IvParameterSpec(ivData);
//                3.初始化 CBC 模式的 Cipher
                cipher.init(Cipher.ENCRYPT_MODE,key);

                ret = cipher.doFinal(data);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * AES 解密
     * @param data
     * @param password
     * @param ivData
     * @return
     */
    public static byte[] aseDecryptRecommend(byte[] data, byte[] password,byte[] ivData) {
        byte[] ret = null;
//        ivData长度至少16个字节，超过16个字节，也会取前16位
        if (data != null && password != null && password.length == 16 && ivData!=null && ivData.length>=16) {
            try {
//                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS/Padding");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//                1.准备AES密钥(第一套密码)
                SecretKeySpec key = new SecretKeySpec(password,"AES");
//                2.准备CBC模式的向量Iv参数（可以认为是第二套密码）
                IvParameterSpec iv = new IvParameterSpec(ivData);
//                3.初始化 CBC 模式的 Cipher
                cipher.init(Cipher.DECRYPT_MODE,key);

                ret = cipher.doFinal(data);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

//    单元测试这个方法的可行性;(在androidTest目录下创建对应要测试的类;)
//    RSA 加解密
//    1.加解密，需要的密钥需要生成，不能够随意写；

    /**
     * 生成RSA的密钥对
     * @param keySize 密钥的长度
     * @return
     */
    public static KeyPair genRSAKey(int keySize){
        KeyPair ret = null;
//        必须1024bit以上
        if (keySize >= 1024) {
            try {
//                密钥生成器
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//                2.设置生成的 密钥长度
                keyPairGenerator.initialize(keySize);
//                3.生成公钥私钥
               ret =  keyPairGenerator.generateKeyPair();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * RSA 加密
     * @param data
     * @param key 可以时PublicKey 也可以时PrivateKey
     * @return
     */
    public static byte[] rsaEncrypt(byte[] data,Key key){
        byte[] ret = null;
        if (data!=null && key !=null){
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE,key);
                ret = cipher.doFinal(data);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * RSA 解密
     * @param data
     * @param key 解密时,公钥加密私钥解密;私钥加密公钥解密
     * @return
     */
public static byte[] rsaDecrypt(byte[] data,Key key){
        byte[] ret = null;
        if (data!=null && key !=null){
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE,key);
                ret = cipher.doFinal(data);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }


}
