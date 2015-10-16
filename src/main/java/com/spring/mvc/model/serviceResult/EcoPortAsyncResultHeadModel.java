package com.spring.mvc.model.serviceResult;

/**
 * Created by liluoqi on 15/5/5.
 */
public class EcoPortAsyncResultHeadModel {

    private String businessType;

    public EcoPortAsyncResultHeadModel() {

    }

    public EcoPortAsyncResultHeadModel(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
