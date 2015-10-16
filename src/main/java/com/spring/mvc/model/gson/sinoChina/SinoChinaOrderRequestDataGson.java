package com.spring.mvc.model.gson.sinoChina;

import java.util.List;

/**
 * Created by liluoqi on 15/4/27.
 */
public class SinoChinaOrderRequestDataGson {

    private SinoChinaOrderHeaderGson orderHead;
    private List<SinoChinaOrderGson> orderDetailList;
    private SinoGoodsPurchaserGson goodsPurchaser;

    public SinoChinaOrderRequestDataGson() {
    }

    public SinoChinaOrderRequestDataGson(SinoChinaOrderHeaderGson sinoChinaOrderHeaderGson, List<SinoChinaOrderGson> sinoChinaOrderGsonList,
                                         SinoGoodsPurchaserGson sinoGoodsPurchaserGson) {
        this.orderHead = sinoChinaOrderHeaderGson;
        this.orderDetailList = sinoChinaOrderGsonList;
        this.goodsPurchaser = sinoGoodsPurchaserGson;
    }

    public SinoChinaOrderHeaderGson getOrderHead() {
        return orderHead;
    }

    public void setOrderHead(SinoChinaOrderHeaderGson orderHead) {
        this.orderHead = orderHead;
    }

    public SinoGoodsPurchaserGson getGoodsPurchaser() {
        return goodsPurchaser;
    }

    public void setGoodsPurchaser(SinoGoodsPurchaserGson goodsPurchaser) {
        this.goodsPurchaser = goodsPurchaser;
    }

    public List<SinoChinaOrderGson> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<SinoChinaOrderGson> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
