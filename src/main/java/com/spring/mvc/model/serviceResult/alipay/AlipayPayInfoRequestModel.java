package com.spring.mvc.model.serviceResult.alipay;

import com.spring.mvc.model.BaseModel;
import com.spring.mvc.utils.DateUtils;

import java.util.Date;

/**
 * Created by liluoqi on 15/6/12.
 */
public class AlipayPayInfoRequestModel extends BaseModel {
    private String orderNo;
    private String alipayTradeNo;
    private String outRequestNo;
    private double amount;
    private String customsPlace;
    private String createTime;
    private String updateTime;

    public AlipayPayInfoRequestModel(){

    }

    public AlipayPayInfoRequestModel(String orderNo, String alipayTradeNo, String outRequestNo,
                                     double amount, String customsPlace) {
        this.orderNo = orderNo;
        this.alipayTradeNo = alipayTradeNo;
        this.outRequestNo = outRequestNo;
        this.amount = amount;
        this.customsPlace = customsPlace;
        this.createTime = DateUtils.formatDateToSeconds(new Date());
        this.createTime = updateTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAlipayTradeNo() {
        return alipayTradeNo;
    }

    public void setAlipayTradeNo(String alipayTradeNo) {
        this.alipayTradeNo = alipayTradeNo;
    }

    public String getOutRequestNo() {
        return outRequestNo;
    }

    public void setOutRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCustomsPlace() {
        return customsPlace;
    }

    public void setCustomsPlace(String customsPlace) {
        this.customsPlace = customsPlace;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
