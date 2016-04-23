package com.ggtf.smartscale;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by ggtf at 2016/1/8
 * Author:ggtf
 * Time:2016/1/8
 * Email:15170069952@163.com
 * ProjectName:CustomView
 */
public class RefreshListView extends ListView implements AbsListView.OnScrollListener {

    private String loadingStr;
    private int upToDownLoadingImgId;
    private int downToUpLoadingImgId;

    private View headView;
    private ImageView headRefreshImg;
    private TextView headRefreshTv;
    private int headViewHeight;

    private View footerView;
    private ImageView footerRefreshImg;
    private TextView footerRefreshTv;
    private int footerViewHeight;


    private boolean isShowHead;
    private boolean isShowFooter;
    private boolean isUpToDownRefresh;
    private boolean isDownToUpRefresh;
    private boolean isDownToUp;
    private boolean isUpToDown;

    private int downY;

    private final int PULL_REFRESH = 0;//下拉刷新的状态
    private final int RELEASE_REFRESH = 1;//松开刷新的状态
    private final int REFRESHING = 2;//正在刷新的状态

    private int currentState = PULL_REFRESH;
    private OnRefreshListener listener;
    private boolean isSuccess = true;

    private RotateAnimation upAnimation, downAnimation;

    public interface OnRefreshListener {
        void onUpToDown();

        void onDownToUp();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (!isSuccess) {
                updateFalse();
            }
        }
    };

    public RefreshListView(Context context) {
        this(context, null);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化控件
     */
    private void initView(Context context) {
        initRotateAnimation();

        headView = LayoutInflater.from(context).inflate(R.layout.refresh_listview_head, this, false);
        footerView = LayoutInflater.from(context).inflate(R.layout.refresh_listview_footer, this, false);

        headRefreshImg = (ImageView) headView.findViewById(R.id.rl_head_img);
        headRefreshTv = (TextView) headView.findViewById(R.id.rl_head_txt);
        footerRefreshImg = (ImageView) footerView.findViewById(R.id.rl_footer_img);
        footerRefreshTv = (TextView) footerView.findViewById(R.id.rl_footer_txt);

        headRefreshImg.setImageResource(upToDownLoadingImgId);
        headRefreshTv.setText(loadingStr);
        footerRefreshImg.setImageResource(downToUpLoadingImgId);
        footerRefreshTv.setText(loadingStr);

        headView.measure(0, 0);
        headViewHeight = headView.getMeasuredHeight();
        headView.setPadding(0, -headViewHeight, 0, 0);
        footerView.measure(0, 0);
        footerViewHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, -footerViewHeight, 0, 0);

        this.addHeaderView(headView);
        this.addFooterView(footerView);

        setOnScrollListener(this);


    }

    /**
     * 初始化控件的属性
     */
    private void initAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        loadingStr = "正在加载数据";
        upToDownLoadingImgId = R.mipmap.ic_launcher;
        downToUpLoadingImgId = R.mipmap.ic_launcher;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RefreshListView);
        if (typedArray != null) {
            int count = typedArray.getIndexCount();
            for (int i = 0; i < count; i++) {
                int attr = typedArray.getIndex(i);
                switch (attr) {
                    case R.styleable.RefreshListView_loadingStr:
                        loadingStr = typedArray.getString(R.styleable.RefreshListView_loadingStr);
                        break;
                    case R.styleable.RefreshListView_downToUpLoadingImg:
                        downToUpLoadingImgId = typedArray.getResourceId(R.styleable.RefreshListView_downToUpLoadingImg, R.mipmap.ic_launcher);
                        break;
                    case R.styleable.RefreshListView_upToDownLoadingImg:
                        upToDownLoadingImgId = typedArray.getResourceId(R.styleable.RefreshListView_upToDownLoadingImg, R.mipmap.ic_launcher);
                        break;
                }
            }

            typedArray.recycle();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (currentState == REFRESHING) {
                    break;
                }
                int deltaY = (int) (ev.getY() - downY);

                int paddingTop = -headViewHeight + deltaY;
                if (paddingTop > -headViewHeight && getFirstVisiblePosition() == 0) {
                    headView.setPadding(0, paddingTop, 0, 0);
                    if (paddingTop >= 0 && currentState == PULL_REFRESH) {
                        //从下拉刷新进入松开刷新状态
                        currentState = RELEASE_REFRESH;
                        refreshHeaderView();
                    } else if (paddingTop < 0 && currentState == RELEASE_REFRESH) {
                        //进入下拉刷新状态
                        currentState = PULL_REFRESH;
                        refreshHeaderView();
                    }
                    return true;//拦截TouchMove，不让listview处理该次move事件,会造成listview无法滑动
                }


                break;
            case MotionEvent.ACTION_UP:
                if (currentState == PULL_REFRESH) {
                    //隐藏headerView
                    headView.setPadding(0, -headViewHeight, 0, 0);
                } else if (currentState == RELEASE_REFRESH) {
                    headView.setPadding(0, 0, 0, 0);
                    currentState = REFRESHING;
                    refreshHeaderView();
                    if (listener != null) {
                        listener.onUpToDown();
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }


    /**
     * 初始化旋转动画
     */
    private void initRotateAnimation() {
        upAnimation = new RotateAnimation(0, -180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        upAnimation.setDuration(300);
        upAnimation.setFillAfter(true);
        downAnimation = new RotateAnimation(-180, -360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        downAnimation.setDuration(300);
        downAnimation.setFillAfter(true);
    }

    /**
     * 根据currentState来更新headerView
     */
    private void refreshHeaderView() {
        switch (currentState) {
            case PULL_REFRESH:
                headRefreshTv.setText("下拉刷新");
                headRefreshImg.startAnimation(downAnimation);
                break;
            case RELEASE_REFRESH:
                headRefreshTv.setText("松开刷新");
                headRefreshImg.startAnimation(upAnimation);
                break;
            case REFRESHING:
                headRefreshImg.clearAnimation();//因为向上的旋转动画有可能没有执行完
                headRefreshImg.setVisibility(View.INVISIBLE);
                headRefreshTv.setText("正在刷新...");
                isSuccess = false;
                handler.sendEmptyMessageDelayed(1, 5000);
                break;
        }
    }

    /**
     * 完成刷新操作，重置状态,在你获取完数据并更新完adater之后，去在UI线程中调用该方法
     */
    public void updateFalse() {
        if (isDownToUp) {
            //重置footerView状态
            isDownToUp = false;
            isSuccess = true;
        } else {
            //重置headerView状态
            headView.setPadding(0, -headViewHeight, 0, 0);
            currentState = PULL_REFRESH;
            isSuccess = true;
        }

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        int count = getCount();
        int last = getLastVisiblePosition();
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && last == (count - 1) && !isUpToDown) {
            isUpToDown = true;
            footerView.setPadding(0, 0, 0, 0);//显示出footerView
            setSelection(count);//让listview最后一条显示出来
            if (listener != null) {
                listener.onUpToDown();
            }
            isSuccess = false;
            handler.sendEmptyMessageDelayed(1, 5000);
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
