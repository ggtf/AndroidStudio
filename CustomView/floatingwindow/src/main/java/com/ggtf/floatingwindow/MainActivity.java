package com.ggtf.floatingwindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    private WindowManager windowManager;
    private LinearLayout layout;
    private WindowManager.LayoutParams params;
    private Button floatActivityButton;
    private Button floatAppButton;
    private TextView textView;
    private ViewTreeObserver viewTreeObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatActivityButton = (Button) findViewById(R.id.float_activity);
        floatAppButton = (Button) findViewById(R.id.float_application);

        measureView(floatActivityButton);
        measureView(floatAppButton);

        textView = new TextView(this);
        textView.setText("AAAAAAAAAAAAAAAAAAAAAAAAAa");
        measureView(textView);

        textView.post(new Runnable() {
            @Override
            public void run() {
                int height = textView.getHeight();
                int width = textView.getWidth();
                Log.i("Info", "run height = " + height);
                Log.i("Info", "run width = " + width);
            }
        });

        viewTreeObserver = floatAppButton.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                floatAppButton.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int height = floatAppButton.getHeight();
                int width = floatAppButton.getWidth();
                Log.i("Info", "onGlobalLayout height = " + height);
                Log.i("Info", "onGlobalLayout width = " + width);
            }
        });
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                floatAppButton.getViewTreeObserver().removeOnPreDrawListener(this);
                int height = floatAppButton.getHeight();
                int width = floatAppButton.getWidth();
                Log.i("Info", "onPreDraw height = " + height);
                Log.i("Info", "onPreDraw width = " + width);
//                return false;
                return true;
            }
        });
        viewTreeObserver.addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
            @Override
            public void onWindowFocusChanged(boolean hasFocus) {
                if (hasFocus) {
                    int height = floatAppButton.getHeight();
                    int width = floatAppButton.getWidth();
                    Log.i("Info", "onWindowFocusChanged height = " + height);
                    Log.i("Info", "onWindowFocusChanged width = " + width);
                }
            }
        });

        try {
            parseBySAX();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        parseByDOM();
        parserByPull();

    }

    /**
     * Pull解析
     */
    private void parserByPull() {
        try {
//            创建Pull解析工厂
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            创建Pull解析器
            XmlPullParser pullParser = factory.newPullParser();
//            设置需要解析的文档输入流和编码
            pullParser.setInput(new FileInputStream("file"),"UTF-8");
//            得到第一个事件类型
            int eventType = pullParser.getEventType();
//            解析开始,循环解析直至解析到文档结尾
            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
//                    文档开始,进行初始化
                    case XmlPullParser.START_DOCUMENT:
                        break;
//                    标签,解析具体内容
                    case XmlPullParser.START_TAG:

                        break;
                    case XmlPullParser.END_TAG:
                }
//                进入下一个事件处理
                eventType = pullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * DOM解析
     */
    private void parseByDOM() {
        try {
//        创建DOM解析工厂
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            创建DOM解析器
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
//            开始解析XML文档,并且得到整个文档的对象模型
            Document document = documentBuilder.parse(new FileInputStream("file"));
//            得到根节点
            Element root = document.getDocumentElement();
//            得到根标签下所有标签为<person>的子节点
            NodeList person = root.getElementsByTagName("person");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * SAX解析
     */
    private void parseBySAX() throws ParserConfigurationException, SAXException, IOException {
//        创建SAX解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
//        创建SAX解析处理器
        SAXParser parser = factory.newSAXParser();
//        创建事件处理程序
        saxParse saxParse = new saxParse();
//        开始解析
        parser.parse(new FileInputStream("file"), saxParse);

    }

    /**
     * 测量控件的实际宽高
     *
     * @param view 需要测量的控件
     */
    private void measureView(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        int width = view.getMeasuredWidth();
        Log.i("Info", "height = " + height);
        Log.i("Info", "width = " + width);
    }


    public void btnClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.float_activity:
                createFloatingActivityWindow();
                break;
            case R.id.float_application:
                createFloatingAppWindow();
                break;
        }
    }

    /**
     * 创建Application层的悬浮窗
     */
    private void createFloatingAppWindow() {
        final WindowManager appWindowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        final WindowManager.LayoutParams appParams = new WindowManager.LayoutParams();
        final LinearLayout appLayout = (LinearLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.floating_layout, null);
        appLayout.setBackgroundColor(Color.BLUE);
        appParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        appParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        appParams.format = PixelFormat.RGBA_8888;
//        appParams.gravity=Gravity.TOP|Gravity.LEFT;
        appParams.gravity = Gravity.CENTER;
        appParams.x = 0;
        appParams.y = 0;
        /**
         * 设置按钮
         */
        appParams.buttonBrightness = -1;
        /**
         * 设置屏幕的亮度
         */
        appParams.screenBrightness = -1;
        /**
         * 设置状态栏的可见性
         */
        appParams.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE;
        /**
         * 必须为LayoutParams指定宽高,否则某些属性设置将无效
         */
        appParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        appParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        appParams.width = 200;
//        appParams.height = 200;
        appWindowManager.addView(appLayout, appParams);
        /**
         * 为悬浮设置滑动事件
         */
        final Button appFloatingButton = (Button) appLayout.findViewById(R.id.floating);
        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.floating_window);
//        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.floating_window);
        appFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int pivotX = appFloatingButton.getMeasuredWidth() / 2;
//                int pivotY = appFloatingButton.getMeasuredHeight() / 2;
//                ScaleAnimation animation = new ScaleAnimation(1, 0.5f, 1, 0.5f, pivotX, pivotY);
//                animation.setDuration(3000);
                appFloatingButton.startAnimation(animation);
            }
        });
        appFloatingButton.setOnTouchListener(new View.OnTouchListener() {
            private int rawX;
            private int rawY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                rawX = (int) event.getRawX();
                rawY = (int) event.getRawY();
//                appParams.width = v.getMeasuredWidth();
//                appParams.height = v.getMeasuredHeight();
//                appParams.width=400;
//                appParams.height=400;
//                appParams.x = rawX - v.getMeasuredWidth() / 2;
//                appParams.y = rawY - v.getMeasuredHeight() / 2;
//                appParams.x=100;
//                appParams.y=100;
//                appWindowManager.updateViewLayout(appLayout, appParams);
                /**
                 * Touch事件返回false,Click事件才可以执行
                 */
                return false;
//                return true;
            }
        });

    }

    /**
     * 创建Activity层的悬浮窗
     */
    private void createFloatingActivityWindow() {
        /**
         * window管理器
         */
        windowManager = this.getWindowManager();
        /**
         * 悬浮窗展示的布局
         */
        layout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.floating_layout, null);
        /**
         * 悬浮窗的layout布局属性
         */
        params = new WindowManager.LayoutParams();
        /**
         * 设置具体的Layout布局属性
         */
//        Type
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
//        设置图片格式,效果为背景透明
        params.format = PixelFormat.RGBA_8888;
//        设置浮动窗口不可聚焦(实现操作除浮动窗口外的其他可见窗口的操作)
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        设置悬浮窗显示的位置
        params.gravity = Gravity.LEFT | Gravity.TOP;
//        以屏幕左上角为原点,设置x,y的初始值,相对于gravity
        params.x = 0;
        params.y = 0;
//        设置悬浮窗长宽数据
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        /**
         * 加入悬浮窗视图
         */
        windowManager.addView(layout, params);
        /**
         * 设置悬浮窗视图的滑动事件
         */
        final Button floatButton = (Button) layout.findViewById(R.id.floating);
        floatButton.setOnTouchListener(new View.OnTouchListener() {
            private int x;
            private int y;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = (int) event.getRawX();
                y = (int) event.getRawY();
                params.x = x - floatButton.getMeasuredWidth() / 2;
                params.y = y - floatButton.getMeasuredHeight() / 2;
                /**
                 * 修改layout参数后更新
                 */
                windowManager.updateViewLayout(layout, params);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        /**
         * LocalWindowManager生命周期比Activity生命周期短,在finish()之前必须先移除view
         */
        if (windowManager != null) {
            windowManager.removeViewImmediate(layout);
        }
        finish();
    }

    /**
     * SAX解析处理类
     */
    public final class saxParse extends DefaultHandler {

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
        }
    }
}


