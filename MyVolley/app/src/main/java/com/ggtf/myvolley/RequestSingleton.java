package com.ggtf.myvolley;

/**
 * Created by ggtf at 2015/9/30
 * Author:ggtf
 * Time:2015/9/30
 * Email:15170069952@163.com
 * ProjectName:MyVolley
 */
public class RequestSingleton {
    private RequestSingleton requestSingleton = new RequestSingleton();
    private RequestSingleton(){

    }
    public RequestSingleton getInstance(){
        return requestSingleton;
    }
}
