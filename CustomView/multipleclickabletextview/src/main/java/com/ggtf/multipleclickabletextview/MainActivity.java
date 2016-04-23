package com.ggtf.multipleclickabletextview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView multipleTextView = (TextView) findViewById(R.id.multiple_text_view);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append("username-").append(i).append("、");
        }
        String likeUsers = builder.substring(0, builder.lastIndexOf("、"));
        multipleTextView.setMovementMethod(LinkMovementMethod.getInstance());
        multipleTextView.setText(addClickablePart(likeUsers), TextView.BufferType.SPANNABLE);
    }

    private SpannableStringBuilder addClickablePart(String str) {
        /**
         * 第一个赞图标
         */
        ImageSpan span = new ImageSpan(this,R.mipmap.ic_launcher);

        SpannableString spanStr = new SpannableString("p.");
        spanStr.setSpan(span,0,1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        SpannableStringBuilder ssb = new SpannableStringBuilder(spanStr);
        ssb.append(str);
        String[] likeUsers = str.split("、");
        if (likeUsers.length>0){
//            最后一个
            for (int i = 0; i < likeUsers.length; i++) {
                final String name = likeUsers[i];
                int start = str.indexOf(name)+spanStr.length();
                ssb.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
//                        设置文本颜色
                        ds.setColor(Color.RED);
//                        设置去掉下划线
                        ds.setUnderlineText(false);
                    }
                },start,start+name.length(),0);
            }
        }
        return ssb.append("等"+likeUsers.length+"个人赞了您。");
    }
}
