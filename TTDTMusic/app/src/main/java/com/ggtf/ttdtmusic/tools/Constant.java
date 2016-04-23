package com.ggtf.ttdtmusic.tools;

/**
 * Created by ggtf at 2015/10/24
 * Author:ggtf
 * Time:2015/10/24
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class Constant {



    private Constant() {

    }

    /**
     * 文件缓存是否成功
     */
    public static final int CACHE_SUCCESSFUL = 1000;
    public static final int CACHE_UNSUCCESSFUL = 1001;
    /**
     *Notification的状态
     */
    public static final int NOTIFICATION_STATE = 2002;
    public static final int NOTIFICATION_PLAY = 2003;
    public static final int NOTIFICATION_PRE = 2004;
    public static final int NOTIFICATION_NEXT = 2005;
    public static final int NOTIFICATION_TO_START_ACTIVITY = 2006;
    public static final int NOTIFICATION_DELETE = 2007;



    /**
     * 服务类型
     */
    public static final int BROADCAST_TO_SERVICE = 3000;
    public static final int CLOSE_NOTIFICATION_SERVICE = 3001;
    public static final int ACTIVITY_FINISH = 3002;
    public static final int MUSIC_CURRENT_POSITION = 3003;





    /**
     * 自定义的广播action
     */
    public static final String MUSIC_BROADCAST_ACTION = "MUSIC_BROADCAST_RECEIVER_ACTION";
}
