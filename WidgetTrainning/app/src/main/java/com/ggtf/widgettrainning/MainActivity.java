package com.ggtf.widgettrainning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MultiAutoCompleteTextView multiAutoCompleteTextView;
    private com.ggtf.calendarview.CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_calendar);
//        initView();
//        initCalendarView();
//        initGridViewCalendarView();
        calendarView = (com.ggtf.calendarview.CalendarView) findViewById(R.id.my_calendar_view);
//        calendarView.post(new Runnable() {
//            @Override
//            public void run() {
//                int height = calendarView.getHeight();
//                int width = calendarView.getWidth();
//                Log.i("Info", "width = " + width);
//                Log.i("Info", "height = " + height);
//                width = Math.min(width, height);
//                calendarView.getCurrentAttributes(width, width);
//                calendarView.initData();
//            }
//        });
        calendarView.setOnClickListener(this);
    }

    private void initGridViewCalendarView() {
        GridView gridView = (GridView) findViewById(R.id.grid_view_calendar);
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
        CalendarAdapter adapter = new CalendarAdapter(this,days);
        gridView.setAdapter(adapter);
    }

    private void initCalendarView() {
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(MainActivity.this, "日期改变了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        String formatDate = format.format(date);
        Date dateFirst = new Date(0);
        String formatDateFirst = format.format(dateFirst);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFirst);
        Log.i("Info", "Date Year = " + date.getYear());
        Log.i("Info","formatDateFirst "+formatDateFirst);
        Log.i("Info","formatDate "+formatDate);
        Log.i("Info","Calendar  "+calendar.get(Calendar.YEAR));
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.multi_auto);
        String[] contents = {"Apple","Apple1","Apple2","Orange","Orange1","Appol","Apple2","Orange","Orange1","Appol"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contents);
        multiAutoCompleteTextView.setAdapter(adapter);
        MultiAutoCompleteTextView.CommaTokenizer tokenizer = new MultiAutoCompleteTextView.CommaTokenizer();
        /**
         * Tokenizer类的定义
         */
        tokenizer.findTokenStart("p",2);
        multiAutoCompleteTextView.setTokenizer(tokenizer);

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.auto);
        autoCompleteTextView.setAdapter(adapter);
    }

    public void show(View view) {
        multiAutoCompleteTextView.showDropDown();
        multiAutoCompleteTextView.setThreshold(1);
    }

    public void choose(View view) {

    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button){
            Button button = (Button) v;
            button.setBackgroundResource(R.drawable.button_shape_selected);
        }
    }
}
