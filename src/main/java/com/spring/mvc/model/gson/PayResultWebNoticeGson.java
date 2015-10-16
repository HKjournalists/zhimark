package com.spring.mvc.model.gson;

import com.spring.mvc.utils.DateUtils;

import java.util.Date;

/**
 * 支付返回给web端的结果
 * Created by liluoqi on 15/9/2.
 */
public class PayResultWebNoticeGson {
    private boolean result;
    private String errorMessage;
    private String payTime;
    private String returnTime;

    public PayResultWebNoticeGson() {
    }

    public PayResultWebNoticeGson(boolean result, String errorMessage, String payTime) {
        this.result = result;
        this.errorMessage = errorMessage;
        this.payTime = payTime;
        this.returnTime = DateUtils.formatDateToSeconds(new Date());
    }

    public PayResultWebNoticeGson(boolean result, String errorMessage) {
        this.result = result;
        this.errorMessage = errorMessage;
        this.returnTime = DateUtils.formatDateToSeconds(new Date());
    }

    public boolean isResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getPayTime() {
        return payTime;
    }

    public String getReturnTime() {
        return returnTime;
    }
}
