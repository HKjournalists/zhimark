package com.spring.mvc.model.gson.ecoPort;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by liluoqi on 15/5/1.
 */
@XStreamAlias("jkfOrderDetail")
public class EcoPortJKFOrderDetailGson {
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
    private double grossWeight;//非必填

    public long getGoodsOrder() {
        return goodsOrder;
    }

    public void setGoodsOrder(long goodsOrder) {
        this.goodsOrder = goodsOrder;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCodeTs() {
        return codeTs;
    }

    public void setCodeTs(String codeTs) {
        this.codeTs = codeTs;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(double goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }
}
