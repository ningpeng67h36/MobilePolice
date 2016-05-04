package com.monet.yashi.mobilepolice.util;

/**
 * Created by Administrator on 2016/5/4.
 */
public class StringTools {

    /**
     * 判断字符串数组中的内容是否有空字符串
     * @param str
     * @return true:都不为空  false:至少有一个为空
     */
    public static boolean isStringEmpty(String ... str) {

        for(int i=0; i<str.length; i++) {
            if(str[i].equals("")) {
                return false;
            }
        }
        return true;
    }
}
