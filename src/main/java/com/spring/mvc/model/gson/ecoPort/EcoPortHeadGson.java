package com.spring.mvc.model.gson.ecoPort;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by liluoqi on 15/5/1.
 */
@XStreamAlias("head")
public class EcoPortHeadGson {

    private String businessType="IMPORTORDER";

    public EcoPortHeadGson(){

    }

    public EcoPortHeadGson(String businessType){
        this.businessType=businessType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
