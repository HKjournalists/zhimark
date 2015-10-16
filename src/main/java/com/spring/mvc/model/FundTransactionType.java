package com.spring.mvc.model;

/**资金交易类型
 * Created by liluoqi on 15/8/11.
 */
public enum  FundTransactionType {

    BROKERAGE_REPAY("1","佣金打款");

    private String code;
    private String description;

    FundTransactionType(String code,String description){
        this.code=code;
        this.description=description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
