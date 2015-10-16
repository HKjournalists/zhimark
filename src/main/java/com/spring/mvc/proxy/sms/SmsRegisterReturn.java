package com.spring.mvc.proxy.sms;

/**
 * Created by liluoqi on 15/7/7.
 */
public enum SmsRegisterReturn {

    SUCCESS("0", "成功"),
    USERNAME_OR_PASSWORD_WRONG("-1", "用户名或密码不正确"),
    ALREADY_REGISTERED("-3", "用户已经注册"),
    BASIC_INFO_EXCEED("-26", "注册企业基本信息超长"),
    OTHER_SYS_ERROR("-100", "其他系统异常");

    private String code;
    private String description;

    SmsRegisterReturn(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
