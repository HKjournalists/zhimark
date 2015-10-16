package com.spring.mvc.model;

import java.util.Date;

/**
 * Created by liluoqi on 15/9/2.
 */
public class PayChannelReturnModel extends BaseModel {
    private String payType;
    private String status;//交易状态
    private String orderNo;
    private String tradeNo;//支付交易号
    private String errorDescription;
    private Date createdDate;
    private Date updatedDate;


    public static PayChannelReturnModel createSuccessInstance(PayType payType, PayStatus status, String orderNo, String tradeNo) {
        return new PayChannelReturnModel(payType, status, orderNo, tradeNo, null);
    }

    public static PayChannelReturnModel createFailInstance(PayType payType, PayStatus status, String orderNo, String tradeNo, String errorDescription) {
        return new PayChannelReturnModel(payType, status, orderNo, tradeNo, errorDescription);
    }

    private PayChannelReturnModel(PayType payType, PayStatus status, String orderNo, String tradeNo, String errorDescription) {
        this.payType = payType.name();
        this.status = status.name();
        this.orderNo = orderNo;
        this.tradeNo = tradeNo;
        this.errorDescription = errorDescription;
        this.createdDate = new Date();
        this.updatedDate = this.createdDate;
    }


    public PayChannelReturnModel() {

    }


    public void setPayType(String payType) {
        this.payType = payType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getPayType() {
        return payType;
    }

    public String getStatus() {
        return status;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }
}
