package com.spring.mvc.model.gson.ecoPort;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by liluoqi on 15/5/1.
 */
public class EcoPortJKFOrderDetailListGson {
    @XStreamImplicit(itemFieldName = "jkfOrderDetail")
    private List<EcoPortJKFOrderDetailGson> jkfOrderDetail;

    public EcoPortJKFOrderDetailListGson(){

    }

    public EcoPortJKFOrderDetailListGson(List<EcoPortJKFOrderDetailGson> jkfOrderDetail){
        this.jkfOrderDetail=jkfOrderDetail;
    }

    public List<EcoPortJKFOrderDetailGson> getJkfOrderDetail() {
        return jkfOrderDetail;
    }

    public void setJkfOrderDetail(List<EcoPortJKFOrderDetailGson> jkfOrderDetail) {
        this.jkfOrderDetail = jkfOrderDetail;
    }
}
