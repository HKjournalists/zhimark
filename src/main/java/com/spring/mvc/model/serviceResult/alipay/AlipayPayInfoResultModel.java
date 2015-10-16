package com.spring.mvc.model.serviceResult.alipay;

import com.spring.mvc.model.BaseModel;
import com.spring.mvc.utils.DateUtils;

import java.util.Date;

/**
 * Created by liluoqi on 15/6/9.
 */
public class AlipayPayInfoResultModel extends BaseModel {
    private String orderNo;
    private String isSuccess;
    private String error;
    private double amount;
    private String customsPlace;
    private String alipayTradeNo;//支付宝支付交易号;
    private String alipayDeclareNo;//支付宝报关交易号;
    private String resultCode;
    private String detailResultDescription;
    private String createTime;
    private String updateTime;

    public AlipayPayInfoResultModel(){

    }

    public AlipayPayInfoResultModel(String orderNo, String isSuccess, String error, double amount,
                                    String customsPlace, String alipayTradeNo, String alipayDeclareNo,
                                    String resultCode, String detailResultDescription) {
        this.orderNo = orderNo;
        this.isSuccess = isSuccess;
        this.error = error;
        this.amount = amount;
        this.customsPlace = customsPlace;
        this.alipayTradeNo = alipayTradeNo;
        this.alipayDeclareNo = alipayDeclareNo;
        this.resultCode = resultCode;
        this.detailResultDescription = detailResultDescription;
        this.createTime = DateUtils.formatDateToSeconds(new Date());
        this.updateTime = this.createTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    public String getTradeNo() {
        return alipayTradeNo;
    }

    public void setTradeNo(String alipayDeclareNo) {
        this.alipayDeclareNo = alipayDeclareNo;
    }

    public String getAlipayDeclareNo() {
        return alipayDeclareNo;
    }

    public void setAlipayDeclareNo(String alipayDeclareNo) {
        this.alipayDeclareNo = alipayDeclareNo;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getDetailResultDescription() {
        return detailResultDescription;
    }

    public void setDetailResultDescription(String detailResultDescription) {
        this.detailResultDescription = detailResultDescription;
    }

    public String getAlipayTradeNo() {
        return alipayTradeNo;
    }

    public void setAlipayTradeNo(String alipayTradeNo) {
        this.alipayTradeNo = alipayTradeNo;
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
