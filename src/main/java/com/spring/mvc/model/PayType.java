package com.spring.mvc.model;

/**
 * Created by liluoqi on 15/8/3.
 */
public enum PayType {
    UNCERTAIN_PAY_TYPE(2, "未确定支付方式-未支付"),
    ALIPAY(4, "支付宝"),
    LIAN_LIAN_PAY(11, "连连支付");

    private int code;
    private String desc;

    private PayType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static PayType getPayTypeFromPayTypeCode(int code) {
        for (PayType payType : PayType.values()) {
            if (payType.getCode() == code) {
                return payType;
            }
        }
        return UNCERTAIN_PAY_TYPE;
    }
}
