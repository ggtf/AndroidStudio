package com.ggtf.specialmusicplayer.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ggtf.specialmusicplayer.R;
import com.ggtf.specialmusicplayer.models.describe.BannerInfo;
import com.ggtf.specialmusicplayer.network.HttpUtils;

import java.util.List;

/**
 * Created by ggtf at 2016/4/21
 * Author:ggtf
 * Time:2016/4/21
 * Email:15170069952@163.com
 * ProjectName:SpecialMusicPlayer
 */
public class BannerAdapter extends PagerAdapter {
    private List<BannerInfo> bannerInfoList;
    private Context context;
    private LayoutInflater inflater;
    private View.OnClickListener listener;
    private int size;

    public BannerAdapter(Context context, List<BannerInfo> bannerInfoList, View.OnClickListener listener) {
        this.context = context;
        this.bannerInfoList = bannerInfoList;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
        size = bannerInfoList != null ? bannerInfoList.size() : 1;
        size = size <= 0 ? 1 : size;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE >> 2;
//        return bannerInfoList != null ? bannerInfoList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int pos = position % size;
        ImageView image = (ImageView) inflater.inflate(R.layout.banner_image, container, false);
        image.setOnClickListener(listener);
        image.setTag(pos);
        BannerInfo info = bannerInfoList.get(pos);
        String url = info.getUrl();
        HttpUtils.setImage(context, url, image);
        container.addView(image);
        return image;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof ImageView) {
            ImageView view = (ImageView) object;
            container.removeView(view);
        }
    }
}
