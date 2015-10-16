package com.spring.mvc.model;

import com.spring.mvc.utils.StringUtils;

/**
 * Created by liluoqi on 15/7/1.
 */
public enum RepaymentApplyStatus {

    NEW("0", "已申请"),
//    APPROVED("3", "通过"),
    DECLINED("2", "拒绝"),
    PAID("1", "已支付");

    private String code;
    private String description;

    RepaymentApplyStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getDescriptionByCode(String code) {
        for (RepaymentApplyStatus repaymentApplyStatus : RepaymentApplyStatus.values()) {
            if (repaymentApplyStatus.getCode().equals(code)) {
                return repaymentApplyStatus.getDescription();
            }
        }
        return StringUtils.emptyString();
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
