package com.lyl.smzdk.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtils {

    /**
     * 字符串是否为空
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 时间戳转换成日期格式字符串
     */
    public static String formatDate(long timeStamp) {
        // long timeStamp = System.currentTimeMillis();
        // //这个是你要转成后的时间的格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(timeStamp));
    }
}