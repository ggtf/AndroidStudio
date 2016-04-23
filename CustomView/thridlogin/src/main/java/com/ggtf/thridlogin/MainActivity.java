package com.ggtf.thridlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edit);

    }


    public void verification(View view) {
        String result = editText.getText().toString();
        if (result.length()>0){
            boolean isPhone = checkIsPhone(result);
            Toast.makeText(MainActivity.this, "isPhone = "+isPhone, Toast.LENGTH_SHORT).show();
        }
     }

    private boolean checkIsPhone(String phone) {
        boolean isPhone = false;
        String regex ="[1][358][0-9]{9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        isPhone = matcher.matches();
        return isPhone;
    }
    private boolean checkPassword(String password){
        boolean isPassword = false;
        String regex = "[A-Za-z0-9_]{6,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        isPassword = matcher.matches();
        return isPassword;
    }

    public void verificationPassword(View view) {
        String result = editText.getText().toString();
        if (result.length()>0){
            boolean isPassword = checkPassword(result);
            Toast.makeText(MainActivity.this, "isPassword = "+isPassword, Toast.LENGTH_SHORT).show();
        }
    }
}
