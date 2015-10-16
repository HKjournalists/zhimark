package com.spring.mvc.utils;

/**
 * Created by liluoqi on 15/4/29.
 */

import java.text.DecimalFormat;
import java.util.Random;

/**
 * 随机数生成器
 */
public class RandomUtils {

    /**
     * 生成三位的随机数
     *
     * @return
     */
    public static String getThreeRandomNumbers() {
        Random random = new Random();
        DecimalFormat decimalFormat = new DecimalFormat("000");
        return decimalFormat.format(random.nextInt(1000));
    }

    /**
     * 生成七位的随机数
     *
     * @return
     */
    public static String getSevenRandomNumbers() {
        Random random = new Random();
        DecimalFormat decimalFormat = new DecimalFormat("0000000");
        return decimalFormat.format(random.nextInt(10000000));
    }

    /**
     * 生成六位的随机数
     *
     * @return
     */
    public static String getSixRandomNumbers() {
        Random random = new Random();
        DecimalFormat decimalFormat = new DecimalFormat("000000");
        return decimalFormat.format(random.nextInt(1000000));
    }

    /**
     * 生成十位的随机数
     *
     * @return
     */
    public static String getTenRandomNumbers() {
        Random random = new Random();
        return String.valueOf(random.nextLong());
    }


    public static void main(String[] args) {
        System.out.println(getTenRandomNumbers());
    }
}
