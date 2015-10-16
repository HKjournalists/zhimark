package com.spring.mvc.utils;


import com.google.gson.Gson;
import com.spring.mvc.model.gson.LcPayAsyncNoticeResultGson;
import com.spring.mvc.model.gson.SmsReturnGson;
import org.joda.time.DateTime;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

import static com.spring.mvc.utils.StringUtils.emptyString;

/**
 * Created by liluoqi on 15/5/5.
 */
public class Test {
    public static void main(String[] args) {
//        HttpClientUtils.postForm("http://stageapp.z-mark.net:8080/gateway/service/alipay/push-payInfo",
//                MapUtils.convertStringToMap("orderNo", "XGYX201510131535045868"));
        System.out.println("本人承诺所购买商品系个人自用，现委托相关企业代理申报、代缴税款等通关事宜，保证遵守《海关法》和相关法律法规，保证提供的身份信息和收货信息真实完整，无侵犯他人权益的行为，以上系如实填写，愿意接受海关、检验检疫机构及其他监管部门的监管，并承担相应法律责任".length());
    }

    class Result {
        String ret_code;
        String ret_msg;

        public String getRet_code() {
            return ret_code;
        }

        public String getRet_msg() {
            return ret_msg;
        }
    }
}
