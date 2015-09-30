package com.ggtf.cryptbase64;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DesActivity extends AppCompatActivity {

    private EditText txtContent;
    private EditText txtPassword;
    private EditText txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des);
        txtContent = (EditText) findViewById(R.id.txt_content);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        txtResult = (EditText) findViewById(R.id.txt_result);
    }

    /**
     * 加密
     * @param view
     */
    public void btnEncrypt(View view) {
        String content = txtContent.getText().toString();
        String password = txtPassword.getText().toString();
//        DES 密码8个字节;
        if (password.length() == 8){
            byte[] keyData = password.getBytes();
            byte[] data = content.getBytes();
//            TODO 加密处理
            try {
//                1.Cipher用于加密/解密
                Cipher cipher = Cipher.getInstance("DES");
//                2.设置Cipher是加密还是解密
//                2.1 DESKeySpec需要创建,用来生成Key
                DESKeySpec spec = new DESKeySpec(keyData);
//                2.2 DESKeySpec 需要使用SecretKeyFactory来生成 Key对象
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//                2.3生成Key
                SecretKey key = keyFactory.generateSecret(spec);
                /**
                 * 初始化
                 * 参数1:加密还是解密
                 * 参数2:Key类型的,密钥;每一种算法Key的生成都不一样;
                 */
                cipher.init(Cipher.ENCRYPT_MODE, key);
//                返回加密后的数据
                byte[] encryptedData = cipher.doFinal(data);
//                TODO 加密后的数据是无法进行 new String(byte[] bytes)
                String result = Base64.encodeToString(encryptedData, Base64.NO_WRAP);
                txtResult.setText(result);

            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException e) {
//                找不到指定的算法 / 无填充 / 无效的密钥 / 无效的KeySpec / 错误的填充 / 无效的分块大小
                e.printStackTrace();
            }
        }
    }

    /**
     * 解密
     * @param view
     */
    public void btnDecrypt(View view) {
        String result = txtResult.getText().toString();
        String password = txtPassword.getText().toString();
//        DES 密码8个字节;
        if (password.length() == 8){
//            密码
            byte[] keyData = password.getBytes();
//            加密后的内容
            byte[] data = Base64.decode(result,Base64.NO_WRAP);
//            TODO 加密处理
            try {
//                1.Cipher用于加密/解密
                Cipher cipher = Cipher.getInstance("DES");
//                2.设置Cipher是加密还是解密
//                2.1 DESKeySpec需要创建,用来生成Key
                DESKeySpec spec = new DESKeySpec(keyData);
//                2.2 DESKeySpec 需要使用SecretKeyFactory来生成 Key对象
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//                2.3生成Key
                SecretKey key = keyFactory.generateSecret(spec);
                /**
                 * 初始化
                 * 参数1:加密还是解密
                 * 参数2:Key类型的,密钥;每一种算法Key的生成都不一样;
                 */
                cipher.init(Cipher.DECRYPT_MODE, key);
//                返回解密后的数据
                byte[] selfData = cipher.doFinal(data);
//                TODO 加密后的数据是无法进行 new String(byte[] bytes)
                String resultData = new String (selfData);
                Toast.makeText(DesActivity.this, "原始数据 = "+resultData, Toast.LENGTH_SHORT).show();

            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidKeySpecException e) {
//                找不到指定的算法 / 无填充 / 无效的密钥 / 无效的KeySpec
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        }
    }
}
