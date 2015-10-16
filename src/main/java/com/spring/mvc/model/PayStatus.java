package com.spring.mvc.model;

/**
 * Created by liluoqi on 15/9/2.
 */
public enum PayStatus {
    TRADE_FINISHED(0,"交易成功"),
    TRADE_FAILED(1,"支付失败");

    private int code;//code:0代表成功，1代表失败
    private String description;

    PayStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
