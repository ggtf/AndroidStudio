package com.ggtf.calendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggtf at 2015/10/22
 * Author:ggtf
 * Time:2015/10/22
 * Email:15170069952@163.com
 * ProjectName:WidgetTrainning
 */

/**
 * 使用GridView自定义的日历控件
 */
public class CalendarView extends GridView{
    /**
     * 用于加载日历数据的adapter
     */
    private BaseAdapter adapter;
    /**
     * 存储数据的集合
     */
    private List<String> days;
    private int normalTextColor;
    private int selectedBackgroundColor;
    private int selectedDayTextColor;
    private int textSize;
    /**
     * 属性数据
     */
    private int screenHeight;
    private int screenWidth;
    private int itemWidth;
    private int today;

    /**
     * 事件处理
     */
    private View.OnClickListener onClickListener;

    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 初始化数据
         */
        initAttributes();
//        initData();
//        执行加载数据
        postData();
    }

    private void postData() {
        this.post(new Runnable() {
            @Override
            public void run() {
                int height = getHeight();
                int width = getWidth();
                Log.i("Info", "width = " + width);
                Log.i("Info", "height = " + height);
                width = Math.min(width, height);
                getCurrentAttributes(width, width);
                initData();
            }
        });
    }

    /**
     * 初始化属性
     */
    private void initAttributes() {
        this.setNumColumns(7);
        this.setHorizontalSpacing(2);
        this.setVerticalSpacing(2);
        this.setStretchMode(STRETCH_COLUMN_WIDTH);
//        this.setStretchMode(STRETCH_SPACING_UNIFORM);
//        this.setStretchMode(STRETCH_SPACING);
        this.setBackgroundResource(R.drawable.calendar_bg);

    }

    public void getCurrentAttributes(int height, int width) {
        this.screenHeight = height;
        this.screenWidth = width;
    }

    /**
     * 初始化内容数据
     */
    public void initData() {
        /**
         * 初始化集合数据
         */
        days = initDays();
        /**
         * 设置Item的宽高,使其相等,展现时是一个正方形,由于背景设置为圆形,因此效果也是圆形
         */
        int width = Math.min(screenHeight, screenWidth);
        /**
         * 以防为获取到屏幕的宽高
         */
        width = Math.max(width,100);
        /**
         * Item的宽度=(整个视图的宽度-LeftMargin-RightMargin-水平间的间隙*(7-1))/7;
         */
        itemWidth = (width - getLeft() - (2 * 6)) / 7;
        /**
         * CalendarView的日期显示View
         */
//        getDate(new Date(System.currentTimeMillis()+1000*60*60*24));
        getDate(new Date());
        /**
         * 初始化Adapter
         */
        adapter = new CalendarAdapter(getContext(), days, onClickListener, itemWidth,today);
        /**
         * 设置数据源adapter
         */
        this.setAdapter(adapter);
        /**
         * 将数据显示出来
         */
        adapter.notifyDataSetChanged();


    }

    private List<String> initDays() {
        List<String> days = new LinkedList<>();
        days.add("日");
        days.add("一");
        days.add("二");
        days.add("三");
        days.add("四");
        days.add("五");
        days.add("六");
        for (int i = 1; i <= 31; i++) {
            days.add(String.valueOf(i));
        }
        return days;
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * 选择器;依据当前所选择的Item,凸显出来;
     */
    public void onSelectionChange(int position) {
        setSelection(position);
        Log.i("Info", "currentSelection = " + position);
        String selectedItem = "A";
        days.add(position, selectedItem);
        days.remove(position + 1);
        adapter.notifyDataSetChanged();
    }

    /**
     * 依据当前日期,获取当前的日历的显示样式;
     * @param date
     */
    private void getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        /**
         * 获取年/月/日--2015-10-22>>>>>
         */
        int year = calendar.get(Calendar.YEAR);
//        月份从0开始;0~11-->1月到12月;
        int month = calendar.get(Calendar.MONTH);
//        今天是这个月的第几天
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//        星期是从星期天开始算起;1~7-->星期天~星期六
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int totalDaysOfMonth = getTotalDaysOfMonth(month+1, year);
        /**
         * 依据该月的今天是星期几;和今天是该月的第几天,来决定days的1号是星期几
         */
        calendar.set(year,month,1);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

//        int weekOffset =0;
//        if (dayOfMonth % 7 ==0 ){
//            weekOffset  = (dayOfWeek+1)%7;
//        }else {
//             weekOffset = dayOfMonth % 7 + dayOfWeek - 1;
//            if (weekOffset<0){
//                weekOffset = 7+weekOffset;
//            }
//        }
        for (int i = 7; i <firstDayOfWeek+ 7-1; i++) {
            days.add(i, "");
        }
        /**
         * 决定当月的最后一天
         */
        for (int i = 0; i < 31-totalDaysOfMonth; i++) {
            days.remove(days.size()-1);
        }
        /**
         * 此时的今天的位置变为--
         */
        today = 7+dayOfMonth+firstDayOfWeek-1-1;
    }
    /**
     * 计算出某年某月的天数
     * @param month 月份
     * @param year 年
     * @return
     */
    private int getTotalDaysOfMonth(int month, int year) {
        boolean isLeapYear = (year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
        Log.i("Info","isLeapYear = "+isLeapYear);
        int totalDays = 28;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                totalDays = 31;
                break;
            case 2:
                totalDays = 28;
                if (isLeapYear) {
                    totalDays = 29;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                totalDays = 30;
                break;
        }
        return totalDays;
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(3);
//        canvas.drawCircle(100,100,50,paint);
//
//        canvas.translate(0,300);
//        super.onDraw(canvas);
//    }
}
