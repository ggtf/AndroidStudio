package com.ggtf.listviewanimation.tools;

import com.ggtf.listviewanimation.models.TextShow;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggtf at 2016/3/5
 * Author:ggtf
 * Time:2016/3/5
 * Email:15170069952@163.com
 * ProjectName:SelfImprovement
 */
public class ModelTools {
    private ModelTools() {

    }

    public static List<TextShow> getTextShowSet(int num) {
        List<TextShow> values = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            values.add(new TextShow("Apple< " + i + " >"));
        }
        return values;
    }
}
