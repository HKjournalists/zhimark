package com.spring.mvc.utils;

/**
 * Created by liluoqi on 15/4/22.
 */
public class StringUtils {

    public static boolean isEmptyOrNull(String string) {
        return string == null || string.isEmpty();
    }

    public static String emptyString() {
        return "";
    }
}
