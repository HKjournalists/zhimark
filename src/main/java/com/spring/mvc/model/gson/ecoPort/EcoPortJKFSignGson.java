package com.spring.mvc.model.gson.ecoPort;

/**
 * Created by liluoqi on 15/5/1.
 */
public class EcoPortJKFSignGson {

    private String companyCode;
    private String businessNo;
    private String businessType;
    private String declareType;
    private String note;

    public EcoPortJKFSignGson(){

    }

    public EcoPortJKFSignGson(String companyCode, String businessNo, String businessType, String declareType,
                              String note) {
        this.companyCode = companyCode;
        this.businessNo = businessNo;
        this.businessType = businessType;
        this.declareType = declareType;
        this.note = note;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getDeclareType() {
        return declareType;
    }

    public void setDeclareType(String declareType) {
        this.declareType = declareType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
