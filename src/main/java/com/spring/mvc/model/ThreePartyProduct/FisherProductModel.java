package com.spring.mvc.model.ThreePartyProduct;

import com.spring.mvc.model.BaseModel;
import com.spring.mvc.model.gson.fisher.FisherProductInfoGson;

import java.util.Date;

import static java.lang.Integer.valueOf;

/**
 * 第三方对接平台产品表，用来记录更新我们的产品库存表
 * Created by liluoqi on 15/9/10.
 */
public class FisherProductModel extends BaseModel {
    private int productId;
    private String productNo;
    private String productName;
    private String productSpec;//规格
    private int brandId;
    private int supplierId;
    private String supplierName;
    private double taxRate;//税率
    private int countryId;//原产国id
    private String countryName;
    private double marketPrice;//市场价
    private double profit;//毛利
    private int stock;//库存
    private int boughtNum;//已圈数量
    private int enabledNum;//可圈数量
    private int exemptionPostage;//是否包邮 1包邮 0不包邮
    private int exemptionServiceCharge;//是否包服务费 1不包 0包
    private int baseNum;//圈存基量
    private double basePrice;//圈存基价
    private String productBrief;
    private String productIntroduction;
    private Date createdDate;
    private Date updatedDate;

    public FisherProductModel() {
    }

    public FisherProductModel(int productId, String productNo, String productName, String productSpec, int brandId, int supplierId,
                              String supplierName, double taxRate, int countryId, String countryName, double marketPrice, double profit,
                              int stock, int boughtNum, int enabledNum, int exemptionPostage, int exemptionServiceCharge, int baseNum,
                              double basePrice, String productBrief, String productIntroduction) {
        this.productId = productId;
        this.productNo = productNo;
        this.productName = productName;
        this.productSpec = productSpec;
        this.brandId = brandId;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.taxRate = taxRate;
        this.countryId = countryId;
        this.countryName = countryName;
        this.marketPrice = marketPrice;
        this.profit = profit;
        this.stock = stock;
        this.boughtNum = boughtNum;
        this.enabledNum = enabledNum;
        this.exemptionPostage = exemptionPostage;
        this.exemptionServiceCharge = exemptionServiceCharge;
        this.baseNum = baseNum;
        this.basePrice = basePrice;
        this.productBrief = productBrief;
        this.productIntroduction = productIntroduction;
        this.createdDate = new Date();
        this.updatedDate = this.createdDate;
    }

    public FisherProductModel(FisherProductInfoGson fisherProductInfoGson) {
        this(valueOf(fisherProductInfoGson.getPro_id()), fisherProductInfoGson.getPro_no(), fisherProductInfoGson.getPro_name(),
                fisherProductInfoGson.getPro_spec(), valueOf(fisherProductInfoGson.getBrand_id()), valueOf(fisherProductInfoGson.getSupplier_id()),
                fisherProductInfoGson.getSupplier_name(), Double.valueOf(fisherProductInfoGson.getTax_rate()), valueOf(fisherProductInfoGson.getCountry_id()),
                fisherProductInfoGson.getCountry_name(), Double.valueOf(fisherProductInfoGson.getMarket_price()), Double.valueOf(fisherProductInfoGson.getProfit()),
                valueOf(fisherProductInfoGson.getStock()), valueOf(fisherProductInfoGson.getBuyed_num()),
                valueOf(fisherProductInfoGson.getEnabled_num()), valueOf(fisherProductInfoGson.getExemption_postage()),
                valueOf(fisherProductInfoGson.getExemption_servicecharge()), valueOf(fisherProductInfoGson.getBase_num()),
                Double.valueOf(fisherProductInfoGson.getBase_price()), fisherProductInfoGson.getPro_brief(), fisherProductInfoGson.getPro_intro());
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getBoughtNum() {
        return boughtNum;
    }

    public void setBoughtNum(int boughtNum) {
        this.boughtNum = boughtNum;
    }

    public int getEnabledNum() {
        return enabledNum;
    }

    public void setEnabledNum(int enabledNum) {
        this.enabledNum = enabledNum;
    }

    public int getExemptionPostage() {
        return exemptionPostage;
    }

    public void setExemptionPostage(int exemptionPostage) {
        this.exemptionPostage = exemptionPostage;
    }

    public int getExemptionServiceCharge() {
        return exemptionServiceCharge;
    }

    public void setExemptionServiceCharge(int exemptionServiceCharge) {
        this.exemptionServiceCharge = exemptionServiceCharge;
    }

    public int getBaseNum() {
        return baseNum;
    }

    public void setBaseNum(int baseNum) {
        this.baseNum = baseNum;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getProductBrief() {
        return productBrief;
    }

    public void setProductBrief(String productBrief) {
        this.productBrief = productBrief;
    }

    public String getProductIntroduction() {
        return productIntroduction;
    }

    public void setProductIntroduction(String productIntroduction) {
        this.productIntroduction = productIntroduction;
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
