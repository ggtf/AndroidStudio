package com.ggtf.customview.custom;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.ggtf.customview.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ggtf at 2015/11/5
 * Author:ggtf
 * Time:2015/11/5
 * Email:15170069952@163.com
 * ProjectName:CustomView
 */
public class MyAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTime(context,appWidgetManager),1,6000);
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
    }


    /**
     * 内部类--MyTime
     */
    private class MyTime extends TimerTask{
        public RemoteViews remoteViews;
        private AppWidgetManager appWidgetManager;
        private ComponentName thisWidget;
        public MyTime(Context context,AppWidgetManager appWidgetManager){
            this.appWidgetManager = appWidgetManager;
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            thisWidget = new ComponentName(context,MyAppWidgetProvider.class);
        }

        @Override
        public void run() {
            Date date = new Date();
            Calendar calendar = new GregorianCalendar(2016,6,11);
            long days = (calendar.getTimeInMillis()-date.getTime())/1000/864000;
            remoteViews.setTextViewText(R.id.widget_button,"Days = "+days);
            appWidgetManager.updateAppWidget(thisWidget,remoteViews);
        }
    }
}
