package com.spring.mvc.model.gson.ecoPort;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by liluoqi on 15/5/1.
 */
public class EcoPortOrderInfoListGson {

    @XStreamImplicit(itemFieldName = "orderInfo")
    private List<EcoPortOrderInfoGson> orderInfo;

    public EcoPortOrderInfoListGson(){

    }

    public EcoPortOrderInfoListGson(List<EcoPortOrderInfoGson> orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<EcoPortOrderInfoGson> getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(List<EcoPortOrderInfoGson> orderInfo) {
        this.orderInfo = orderInfo;
    }
}
