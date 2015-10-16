package com.spring.mvc.model;

import java.util.Date;

/**
 * 快递和订单关联表
 * Created by liluoqi on 15/9/10.
 */
public class OrderDeliveryModel extends BaseModel {
    private int orderId;
    private String deliveryNo;
    private String deliveryName;
    private Date createdDate;
    private Date updatedDate;

    public OrderDeliveryModel() {
    }

    public OrderDeliveryModel(int orderId, String deliveryNo, String deliveryName) {
        this.orderId = orderId;
        this.deliveryNo = deliveryNo;
        this.deliveryName = deliveryName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
