package com.ggtf.customview;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ShareActionProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ShareActivity extends Activity {

    private ShareActionProvider shareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        MenuItem item = menu.findItem(R.id.action_share_text);
        if (item!=null){
            shareActionProvider = (ShareActionProvider) item.getActionProvider();
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello World");
            shareActionProvider.setShareIntent(shareIntent);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Share(View view) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            ContentResolver resolver = this.getContentResolver();
            Uri uri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(resolver, uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bitmap != null) {
//                上传图片
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = resolver.query(uri, proj, null, null, null);
                int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String path = cursor.getString(index);
                File file = new File(path);
                Log.i("Info", "file path = " + file.getAbsolutePath());

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/*");
                Uri imageUri = Uri.fromFile(file);
//                shareIntent.setData(imageUri);
                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
//                shareIntent.setPackage("com.tencent.mm");//微信分享
//                shareIntent.setPackage("com.qzone");//QQ空间分享
                shareIntent.setPackage("com.tencent.mobileqq");//QQ分享
                startActivity(shareIntent);
//                startActivity(Intent.createChooser(shareIntent,"请选择"));
            }

        }
    }

    private ArrayList<Uri> getUriListForImages(){
        ArrayList<Uri> myList = new ArrayList<>();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File[] files = directory.listFiles();
            if (files.length>0){
                File file = files[1];
                String[] fileList = file.list();
                if (fileList.length>0){
                    for (int i = 0; i < 5; i++) {
                        try {
                            ContentValues values = new ContentValues(7);
                            values.put(MediaStore.Images.Media.TITLE,fileList[i]);
                            values.put(MediaStore.Images.Media.DISPLAY_NAME, fileList[i]);

                            values.put(MediaStore.Images.Media.DATE_TAKEN, new Date().getTime());

                            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

                            values.put(MediaStore.Images.ImageColumns.BUCKET_ID, file.getAbsolutePath().hashCode());

                            values.put(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, fileList[i]);

                            values.put("_data", file.getAbsolutePath() + fileList[i]);

                            ContentResolver contentResolver = getApplicationContext().getContentResolver();

                            Uri uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                            myList.add(uri);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return myList;
    }

    private ArrayList<Uri> getUriImages(){
        ArrayList<Uri> uris = new ArrayList<>();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File pictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File[] files = pictures.listFiles();
            if (files.length>0){
                File[] listFiles = files[1].listFiles();
                for (int i = 0; i < 5; i++) {
                    Uri uri = Uri.fromFile(listFiles[i]);
                    uris.add(uri);
                }
            }
        }
        return uris;
    }

    public void shareMultiple(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");
//        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,getUriListForImages());
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,getUriImages());
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "你好 ");
        intent.putExtra(Intent.EXTRA_TITLE, "我是标题");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, "请选择"));
    }
}
