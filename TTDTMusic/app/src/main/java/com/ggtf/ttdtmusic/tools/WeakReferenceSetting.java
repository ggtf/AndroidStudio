package com.ggtf.ttdtmusic.tools;

import com.ggtf.ttdtmusic.entities.BaseUI;

import java.lang.ref.WeakReference;

/**
 * Created by ggtf at 2015/10/27
 * Author:ggtf
 * Time:2015/10/27
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class WeakReferenceSetting {
    private WeakReferenceSetting(){

    }
    public  static BaseUI addInWeakReference(BaseUI baseUI){
        WeakReference<BaseUI> weakReference = new WeakReference<BaseUI>(baseUI);
        return weakReference.get();
    }
    public  static <T extends BaseUI> T addAllInWeakReference(T t){
        WeakReference<T> weakReference = new WeakReference<T>(t);
        return weakReference.get();
    }

}
