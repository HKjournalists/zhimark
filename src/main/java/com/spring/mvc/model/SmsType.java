package com.spring.mvc.model;

/**
 * Created by liluoqi on 15/7/9.
 */
public enum SmsType {
    VALIDATION_CODE("短信验证", "短信验证码校验"),
    PASSWORD_RESET("密码重置或修改","密码重置或是找回"),
    DELIVERED_NOTIFICATION("发货通知", "订单发货通知收件人,包含EMS信息");

    private String typeName;
    private String description;

    SmsType(String typeName, String description) {
        this.typeName = typeName;
        this.description = description;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getDescription() {
        return description;
    }
}
