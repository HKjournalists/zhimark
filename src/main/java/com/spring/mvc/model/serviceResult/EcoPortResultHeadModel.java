package com.spring.mvc.model.serviceResult;

/**
 * Created by liluoqi on 15/5/4.
 */
public class EcoPortResultHeadModel {
    private String businessType;

    public EcoPortResultHeadModel(){

    }

    public EcoPortResultHeadModel(String businessType){
        this.businessType=businessType;
    }
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
