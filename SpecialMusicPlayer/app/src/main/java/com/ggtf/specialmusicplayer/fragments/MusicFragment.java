package com.ggtf.specialmusicplayer.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ggtf.specialmusicplayer.R;
import com.ggtf.specialmusicplayer.customs.BannerView;
import com.ggtf.specialmusicplayer.models.describe.BannerInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * 音乐界面
 */
public class MusicFragment extends Fragment {


    private LinearLayout musicView;
    private Context context;

    public MusicFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        musicView = (LinearLayout) inflater.inflate(R.layout.fragment_music, container, false);
        context = getContext();
        initView();

        return musicView;
    }

    /**
     * 初始化视图
     */
    private void initView() {
        List<BannerInfo> bannerInfoList = new LinkedList<>();
        String[] urls = new String[]{"http://p2.so.qhimg.com/bdr/_240_/t018df17a802b3fe28c.jpg",
                "http://image.jeuxvideo.com/medias-md/144077/1440770427-708-card.jpg",
                "http://ww1.sinaimg.cn/large/0060fv5Fgw1ergiu147qej30lc0c0q51.jpg",
                "http://image.jeuxvideo.com/medias-md/144732/1447320419-3336-card.jpg",
                "http://www.52z.com/upload/201305/09/1368091858648639.jpg"};

        String text = "Image";
        try {
            JSONObject[] jsons = new JSONObject[]{new JSONObject("{\"url\":\"" + urls[0] + "\",\"type\":1,\"text\":\"" + text + "\"}"),
                    new JSONObject("{\"url\":\"" + urls[1] + "\",\"type\":1,\"text\":\"" + text + "\"}"),
                    new JSONObject("{\"url\":\"" + urls[2] + "\",\"type\":1,\"text\":\"" + text + "\"}")
                    , new JSONObject("{\"url\":\"" + urls[3] + "\",\"type\":1,\"text\":\"" + text + "\"}")
                    , new JSONObject("{\"url\":\"" + urls[4] + "\",\"type\":1,\"text\":\"" + text + "\"}")};
            bannerInfoList.add(new BannerInfo(jsons[0]));
            bannerInfoList.add(new BannerInfo(jsons[1]));
            bannerInfoList.add(new BannerInfo(jsons[2]));
            bannerInfoList.add(new BannerInfo(jsons[3]));
            bannerInfoList.add(new BannerInfo(jsons[4]));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        View viewShow = musicView.findViewById(R.id.id_banner);
        BannerView bannerView = new BannerView(context,viewShow, bannerInfoList);
//        View viewShow = bannerView.getViewShow();
//        int height = context.getResources().getDimensionPixelSize(R.dimen.banner_height);
//        musicView.addView(viewShow, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
        bannerView.startCyclePlay(3);

    }

}
