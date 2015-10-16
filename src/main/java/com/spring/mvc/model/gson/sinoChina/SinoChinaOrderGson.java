package com.spring.mvc.model.gson.sinoChina;

import com.spring.mvc.model.BaseModel;

/**
 * Created by liluoqi on 15/4/22.
 */
public class SinoChinaOrderGson extends BaseModel {

    //商品序号
    private long goodsOrder;//必填
    //物品名称
    private String goodsName;//必填
    //行邮税号
    private String codeTs;//必填
    //商品规格、型号
    private String goodsModel;//非必填
    //产销国
    private String originCountry;//必填
    //申报单价
    private double unitPrice;//必填
    //申报数量
    private double goodsCount;//必填
    //申报计量单位
    private String goodsUnit;//必填
    //商品毛重
    private double grossWeight;//必填
    //商品净重
    private double netWeight;//必填
    //物料号
    private String goodsItemNo;


    public long getGoodsOrder() {
        return goodsOrder;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getCodeTs() {
        return codeTs;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getGoodsCount() {
        return goodsCount;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public void setGoodsOrder(long goodsOrder) {
        this.goodsOrder = goodsOrder;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setCodeTs(String codeTs) {
        this.codeTs = codeTs;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setGoodsCount(double goodsCount) {
        this.goodsCount = goodsCount;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getGoodsItemNo() {
        return goodsItemNo;
    }

    public void setGoodsItemNo(String goodsItemNo) {
        this.goodsItemNo = goodsItemNo;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }
}
