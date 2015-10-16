package com.spring.mvc.model;

import java.util.Date;

/**
 * Created by liluoqi on 15/6/26.
 */
public class RepaymentPlanModel extends BaseModel {
    private int orderId;
    private String orderNo;
    private int headCorpID;
    private int subCorpID;
    private int retailerCorpID;
    private double headCorpPrice;
    private double subCorpPriceFromHeadCorp;
    private double retailerPriceFromHeadCorp;
    private double retailerPrice;
    private double headCorpProfit;
    private double subCorpProfit;
    private double retailerProfit;
    private String status;
    private String belongsToCorp;
    private String applyId;
    private Date createdTime;
    private Date updatedTime;

    public RepaymentPlanModel() {

    }

    public RepaymentPlanModel(int orderId, String orderNo, int headCorpID, int subCorpID, int retailerCorpID,
                              double headCorpPrice, double subCorpPriceFromHeadCorp,
                              double retailerPriceFromHeadCorp, double retailerPrice,
                              double subCorpProfit, double retailerProfit, String belongsToCorp, RepaymentPlanStatus status) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.headCorpID = headCorpID;
        this.subCorpID = subCorpID;
        this.retailerCorpID = retailerCorpID;
        this.headCorpPrice = headCorpPrice;
        this.subCorpPriceFromHeadCorp = subCorpPriceFromHeadCorp;
        this.retailerPriceFromHeadCorp = retailerPriceFromHeadCorp;
        this.retailerPrice = retailerPrice;
        this.headCorpProfit = 0;
        this.subCorpProfit = subCorpProfit;
        this.retailerProfit = retailerProfit;
        this.status = status.getCode();
        this.belongsToCorp = belongsToCorp;
        this.createdTime = new Date();
        this.updatedTime = this.createdTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getHeadCorpID() {
        return headCorpID;
    }

    public void setHeadCorpID(int headCorpID) {
        this.headCorpID = headCorpID;
    }

    public int getSubCorpID() {
        return subCorpID;
    }

    public void setSubCorpID(int subCorpID) {
        this.subCorpID = subCorpID;
    }

    public int getRetailerCorpID() {
        return retailerCorpID;
    }

    public void setRetailerCorpID(int retailerCorpID) {
        this.retailerCorpID = retailerCorpID;
    }

    public double getHeadCorpPrice() {
        return headCorpPrice;
    }

    public void setHeadCorpPrice(double headCorpPrice) {
        this.headCorpPrice = headCorpPrice;
    }

    public double getSubCorpPriceFromHeadCorp() {
        return subCorpPriceFromHeadCorp;
    }

    public void setSubCorpPriceFromHeadCorp(double subCorpPriceFromHeadCorp) {
        this.subCorpPriceFromHeadCorp = subCorpPriceFromHeadCorp;
    }

    public double getRetailerPriceFromHeadCorp() {
        return retailerPriceFromHeadCorp;
    }

    public void setRetailerPriceFromHeadCorp(double retailerPriceFromHeadCorp) {
        this.retailerPriceFromHeadCorp = retailerPriceFromHeadCorp;
    }

    public double getHeadCorpProfit() {
        return headCorpProfit;
    }

    public void setHeadCorpProfit(double headCorpProfit) {
        this.headCorpProfit = headCorpProfit;
    }

    public double getSubCorpProfit() {
        return subCorpProfit;
    }

    public void setSubCorpProfit(double subCorpProfit) {
        this.subCorpProfit = subCorpProfit;
    }

    public double getRetailerProfit() {
        return retailerProfit;
    }

    public void setRetailerProfit(double retailerProfit) {
        this.retailerProfit = retailerProfit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public double getRetailerPrice() {
        return retailerPrice;
    }

    public void setRetailerPrice(double retailerPrice) {
        this.retailerPrice = retailerPrice;
    }

    public String getBelongsToCorp() {
        return belongsToCorp;
    }

    public void setBelongsToCorp(String belongsToCorp) {
        this.belongsToCorp = belongsToCorp;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public int getOrderId() {
        return orderId;
    }
}
