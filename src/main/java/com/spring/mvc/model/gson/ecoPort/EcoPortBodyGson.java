package com.spring.mvc.model.gson.ecoPort;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by liluoqi on 15/5/1.
 */
@XStreamAlias("body")
public class EcoPortBodyGson {
    private EcoPortOrderInfoListGson orderInfoList;

    public EcoPortBodyGson(){

    }

    public EcoPortBodyGson(EcoPortOrderInfoListGson orderInfoList) {
        this.orderInfoList = orderInfoList;
    }

    public EcoPortOrderInfoListGson getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(EcoPortOrderInfoListGson orderInfoList) {
        this.orderInfoList = orderInfoList;
    }
}
