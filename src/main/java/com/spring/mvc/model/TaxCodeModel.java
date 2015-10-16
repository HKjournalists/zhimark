package com.spring.mvc.model;

/**对应物品行邮税和税率配置字典表
 * Created by liluoqi on 15/5/9.
 */
public class TaxCodeModel extends BaseModel{
    private String taxCode;
    private double taxRate;

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
}
