package com.spring.mvc.model;

import com.spring.mvc.utils.StringUtils;

/**
 * Created by liluoqi on 15/6/26.
 */
public enum RepaymentPlanStatus {

    SETTLED("3", "已结算"),
    PROCESSING("2", "结算中"),
    WAITING("0", "未到账期"),
    UNSETTLED("1", "未申请");

    private String code;
    private String description;

    RepaymentPlanStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public static String getDescriptionByCode(String code) {
        for (RepaymentPlanStatus repaymentPlanStatus : RepaymentPlanStatus.values()) {
            if (repaymentPlanStatus.getCode().equals(code)) {
                return repaymentPlanStatus.getDescription();
            }
        }
        return StringUtils.emptyString();
    }

    public String getDescription() {
        return description;
    }

    public static void main(String[] args) {
        System.out.println(getDescriptionByCode("1"));
    }
}
