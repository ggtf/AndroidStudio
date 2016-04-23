package com.ggtf.selfimprovement.tools;

import com.ggtf.selfimprovement.models.TextShow;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggtf at 2016/3/4
 * Author:ggtf
 * Time:2016/3/4
 * Email:15170069952@163.com
 * ProjectName:SelfImprovement
 */
public class Tools {
    private Tools() {

    }

    public static List<TextShow> getTextShowData(int size) {
        List<TextShow> data = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            data.add(i, new TextShow("Apple<" + i + ">"));
        }
        return data;
    }
}
