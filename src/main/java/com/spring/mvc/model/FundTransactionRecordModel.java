package com.spring.mvc.model;

import java.util.Date;

/**
 * 资金交易记录表,目前发生在此系统的资金交易是佣金打款
 * Created by liluoqi on 15/8/11.
 */
public class FundTransactionRecordModel extends BaseModel {
    private int fromUser;
    private int toUser;
    private double amount;
    private String transactionType;
    private String transactionDesc;
    private Date createdDate;
    private Date updatedDate;

    public FundTransactionRecordModel() {
    }

    public FundTransactionRecordModel(int fromUser, int toUser, double amount, FundTransactionType fundTransactionType) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
        this.transactionType = fundTransactionType.getCode();
        this.transactionDesc = fundTransactionType.getDescription();
        this.createdDate = new Date();
        this.updatedDate = this.createdDate;
    }

    public int getFromUser() {
        return fromUser;
    }

    public int getToUser() {
        return toUser;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }
}
