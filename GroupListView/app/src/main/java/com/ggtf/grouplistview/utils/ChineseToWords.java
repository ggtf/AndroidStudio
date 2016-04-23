package com.ggtf.grouplistview.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by ggtf at 2015/10/9
 * Author:ggtf
 * Time:2015/10/9
 * Email:15170069952@163.com
 * ProjectName:GroupListView
 */
public class ChineseToWords {
    private ChineseToWords(){

    }
    /**
     * 返回一个字的拼音
     */
    public static String toWords(char chinese){
        HanyuPinyinOutputFormat pingYin = new HanyuPinyinOutputFormat();
        pingYin.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        pingYin.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
        pingYin.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        String [] pingYinArray=null;
        if (chinese>=0x4e00 && chinese<=0x9fa5){
//            try {
//                pingYinArray = PinyinHelper.toHanyuPinyinStringArray(chinese,pingYin);
//            } catch (BadHanyuPinyinOutputFormatCombination e) {
//                e.printStackTrace();
//            }
            pingYinArray = PinyinHelper.toHanyuPinyinStringArray(chinese);
        }
//        返回获取的拼音
        if (pingYinArray!=null){
            return pingYinArray[0];
        }
        return null;

    }
}
