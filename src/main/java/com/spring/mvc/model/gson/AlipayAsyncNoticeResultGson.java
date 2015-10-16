package com.spring.mvc.model.gson;

/**
 * Created by liluoqi on 15/7/3.
 */
public class AlipayAsyncNoticeResultGson {

    private String notify_type;
    private String notify_time;
    private String notify_id;
    private String sign_type;
    private String sign;
    private String notify_reg_time;
    private String total_fee;
    private String currency;
    private String out_trade_no;
    private String trade_no;
    private String trade_status;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public String getNotify_type() {
        return notify_type;
    }

    public String getNotify_time() {
        return notify_time;
    }

    public String getNotify_id() {
        return notify_id;
    }

    public String getSign_type() {
        return sign_type;
    }

    public String getSign() {
        return sign;
    }

    public String getNotify_reg_time() {
        return notify_reg_time;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public String getCurrency() {
        return currency;
    }
}
