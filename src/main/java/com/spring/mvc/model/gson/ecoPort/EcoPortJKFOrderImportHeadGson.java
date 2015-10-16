package com.spring.mvc.model.gson.ecoPort;

/**
 * Created by liluoqi on 15/5/1.
 */
public class EcoPortJKFOrderImportHeadGson {
    private String companyCode;//必填
    private String companyName;//必填
    private String consignee;//必填
    private String consigneeAddress;//必填
    private String consigneeEmail;//非必填
    private String consigneeTel;//必填
    private String consigneeProvince;//必填
    private String consigneeCity;//必填
    private String consigneeCounty;//必填
    private String currCode;//必填
    private String eCommerceCode;//必填
    private String eCommerceName;//必填
    private double feeAmount;//非必填
    private String ieFlag = "I";//必填
    private String logisCompanyName;//必填
    private String logisCompanyCode;//必填
    private String note;//非必填
    private double orderGoodsAmount;//必填
    private String orderNo;//必填
    private double orderTaxAmount;//必填
    private double orderTotalAmount;//必填
    private String payCompanyCode;//必填
    private String payNumber;//必填
    private String payType = "1";//必填
    private String postMode;//非必填，发货方式
    private String purchaserId;//必填
    private String senderCountry;//必填
    private String senderName;//必填
    private double totalAmount;//必填
    private int totalCount;//必填
    private String TradeTime;//必填
    private String zipCode;//非必填
    private String wayBills;//非必填
    private String rate = "1";//

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneeEmail() {
        return consigneeEmail;
    }

    public void setConsigneeEmail(String consigneeEmail) {
        this.consigneeEmail = consigneeEmail;
    }

    public String getConsigneeTel() {
        return consigneeTel;
    }

    public void setConsigneeTel(String consigneeTel) {
        this.consigneeTel = consigneeTel;
    }

    public String getConsigneeProvince() {
        return consigneeProvince;
    }

    public void setConsigneeProvince(String consigneeProvince) {
        this.consigneeProvince = consigneeProvince;
    }

    public String getConsigneeCity() {
        return consigneeCity;
    }

    public void setConsigneeCity(String consigneeCity) {
        this.consigneeCity = consigneeCity;
    }

    public String getConsigneeCounty() {
        return consigneeCounty;
    }

    public void setConsigneeCounty(String consigneeCounty) {
        this.consigneeCounty = consigneeCounty;
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    public String geteCommerceCode() {
        return eCommerceCode;
    }

    public void seteCommerceCode(String eCommerceCode) {
        this.eCommerceCode = eCommerceCode;
    }

    public String geteCommerceName() {
        return eCommerceName;
    }

    public void seteCommerceName(String eCommerceName) {
        this.eCommerceName = eCommerceName;
    }

    public double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getIeFlag() {
        return ieFlag;
    }

    public void setIeFlag(String ieFlag) {
        this.ieFlag = ieFlag;
    }

    public String getLogisCompanyName() {
        return logisCompanyName;
    }

    public void setLogisCompanyName(String logisCompanyName) {
        this.logisCompanyName = logisCompanyName;
    }

    public String getLogisCompanyCode() {
        return logisCompanyCode;
    }

    public void setLogisCompanyCode(String logisCompanyCode) {
        this.logisCompanyCode = logisCompanyCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getOrderGoodsAmount() {
        return orderGoodsAmount;
    }

    public void setOrderGoodsAmount(double orderGoodsAmount) {
        this.orderGoodsAmount = orderGoodsAmount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public double getOrderTaxAmount() {
        return orderTaxAmount;
    }

    public void setOrderTaxAmount(double orderTaxAmount) {
        this.orderTaxAmount = orderTaxAmount;
    }

    public double getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(double orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public String getPayCompanyCode() {
        return payCompanyCode;
    }

    public void setPayCompanyCode(String payCompanyCode) {
        this.payCompanyCode = payCompanyCode;
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPostMode() {
        return postMode;
    }

    public void setPostMode(String postMode) {
        this.postMode = postMode;
    }

    public String getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(String purchaserId) {
        this.purchaserId = purchaserId;
    }

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getTradeTime() {
        return TradeTime;
    }

    public void setTradeTime(String tradeTime) {
        TradeTime = tradeTime;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getWayBills() {
        return wayBills;
    }

    public void setWayBills(String wayBills) {
        this.wayBills = wayBills;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
