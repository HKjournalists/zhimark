package com.spring.mvc.model;

/**
 * Created by liluoqi on 15/7/9.
 */
public enum SmsReturn {

    SUCCESS("0", "发送成功"),
    INNER_INTERVAL("-1", "未到下次发送时间"),
    EXCEED_MAX_COUNT("-2", "超出最大发送次数"),
    VALIDATE_FAIL("-3", "校验失败"),
    SYSTEM_ERROR("-10", "系统异常,请稍后再试"),
    MOBILE_NOT_EXIST("-11", "手机号码不存在");

    private String code;
    private String message;

    SmsReturn(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
