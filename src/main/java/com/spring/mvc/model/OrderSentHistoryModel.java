package com.spring.mvc.model;

import com.spring.mvc.utils.DateUtils;

import java.util.Date;

/**
 * Created by liluoqi on 15/5/14.
 */
public class OrderSentHistoryModel extends BaseModel {
    private String businessNo;
    private String orderNo;
    //    private String status;
    private String createTime;
    private String updateTime;

    public OrderSentHistoryModel() {

    }

    public OrderSentHistoryModel(String businessNo, String orderNo) {
        this.businessNo = businessNo;
        this.orderNo = orderNo;
//        this.status = status;
        this.createTime = DateUtils.formatDateToSeconds(new Date());
        this.updateTime = this.createTime;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
