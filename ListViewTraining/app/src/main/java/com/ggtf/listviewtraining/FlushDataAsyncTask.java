package com.ggtf.listviewtraining;

import android.os.AsyncTask;
import android.view.View;

/**
 * Created by ggtf at 2015/10/23
 * Author:ggtf
 * Time:2015/10/23
 * Email:15170069952@163.com
 * ProjectName:ListViewTraining
 */
public class FlushDataAsyncTask extends AsyncTask<Object ,Integer,String> {

    private Object view;
    @Override
    protected String doInBackground(Object... params) {
        String result = "result";
        if (params!=null && params.length>0){
            view = params[0];
            try {
                Thread.sleep(1000);
                result = "flush is over";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        if (s!=null){
            if (s.equals("flush is over")){
                if (view instanceof View){
                    View headView = (View) view;
                    headView.setPadding(0,-300,0,0);
                }
            }
        }
    }
}
