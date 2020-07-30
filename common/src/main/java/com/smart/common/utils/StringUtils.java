package com.smart.common.utils;

/**
 * @author LinDingQiang
 * @time 2020/7/29 11:54
 * @email dingqiang.l@verifone.cn
 */
public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || "null".equals(str);
    }
}
