package com.ggtf.ttdtmusic.entities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by ggtf at 2015/10/25
 * Author:ggtf
 * Time:2015/10/25
 * Email:15170069952@163.com
 * ProjectName:TTDTMusic
 */
public class NotificationState implements Parcelable {
    private Bundle bundle;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBundle(bundle);
    }
    public static final Parcelable.Creator<NotificationState> CREATOR = new Creator<NotificationState>() {
        @Override
        public NotificationState createFromParcel(Parcel source) {
            return new NotificationState(source);
        }

        @Override
        public NotificationState[] newArray(int size) {
            return new NotificationState[size];
        }
    };
    public NotificationState(Parcel in){
        bundle = in.readBundle();
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}
