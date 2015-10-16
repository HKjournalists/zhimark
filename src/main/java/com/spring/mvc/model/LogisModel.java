package com.spring.mvc.model;

/**
 * 物流信息
 * Created by liluoqi on 15/5/7.
 */
public class LogisModel extends BaseModel{
    private String gb;//国别;
    private String name;
    private String logisCompanyName;
    private String logisCompanyCode;
    private String bonded;

    public String getGb() {
        return gb;
    }

    public void setGb(String gb) {
        this.gb = gb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBonded() {
        return bonded;
    }

    public void setBonded(String bonded) {
        this.bonded = bonded;
    }
}
