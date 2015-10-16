package com.spring.mvc.model;

import java.util.Date;

/**
 * 库存表
 * Created by liluoqi on 15/9/6.
 */
public class InventoryModel extends BaseModel {
    //芝麻客内部产品ID
    private int innerProductId;
    private String outerProductId;
    private int amount;
    private int priority;
    private String source;//产品来源类型
    private Date createdDate;
    private Date updatedDate;

    public InventoryModel() {

    }

    public InventoryModel(int innerProductId, String outerProductId, int amount, int priority, String source) {
        this.innerProductId = innerProductId;
        this.outerProductId = outerProductId;
        this.amount = amount;
        this.priority = priority;
        this.source = source;
        this.createdDate = new Date();
        this.updatedDate = this.createdDate;
    }

    public int getInnerProductId() {
        return innerProductId;
    }

    public void setInnerProductId(int innerProductId) {
        this.innerProductId = innerProductId;
    }

    public String getOuterProductId() {
        return outerProductId;
    }

    public void setOuterProductId(String outerProductId) {
        this.outerProductId = outerProductId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
