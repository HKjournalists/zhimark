package com.spring.mvc.model.serviceResult;

/**
 * Created by liluoqi on 15/5/5.
 */
public class AsyncJKFSignModel {
    private String companyCode;
    private String businessNo;

    public AsyncJKFSignModel() {

    }

    public AsyncJKFSignModel(String companyCode, String businessNo) {
        this.companyCode = companyCode;
        this.businessNo = businessNo;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }
}
