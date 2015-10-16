package com.spring.mvc.model.gson.ecoPort;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by liluoqi on 15/5/1.
 */
public class EcoPortOrderInfoGson {
    private EcoPortJKFSignGson jkfSign;
    private EcoPortJKFOrderImportHeadGson jkfOrderImportHead;
    private EcoPortJKFOrderDetailListGson jkfOrderDetailList;
    private EcoPortJKFGoodsPurchaserGson jkfGoodsPurchaser;

    public EcoPortOrderInfoGson(EcoPortJKFSignGson jkfSign,
                                EcoPortJKFOrderImportHeadGson jkfOrderImportHead,
                                EcoPortJKFOrderDetailListGson jkfOrderDetailList,
                                EcoPortJKFGoodsPurchaserGson jkfGoodsPurchaser){
        this.jkfSign=jkfSign;
        this.jkfOrderImportHead=jkfOrderImportHead;
        this.jkfOrderDetailList=jkfOrderDetailList;
        this.jkfGoodsPurchaser=jkfGoodsPurchaser;
    }

    public EcoPortJKFSignGson getJkfSign() {
        return jkfSign;
    }

    public void setJkfSign(EcoPortJKFSignGson jkfSign) {
        this.jkfSign = jkfSign;
    }

    public EcoPortJKFOrderImportHeadGson getJkfOrderImportHead() {
        return jkfOrderImportHead;
    }

    public void setJkfOrderImportHead(EcoPortJKFOrderImportHeadGson jkfOrderImportHead) {
        this.jkfOrderImportHead = jkfOrderImportHead;
    }

    public EcoPortJKFOrderDetailListGson getJkfOrderDetailList() {
        return jkfOrderDetailList;
    }

    public void setJkfOrderDetailList(EcoPortJKFOrderDetailListGson jkfOrderDetailList) {
        this.jkfOrderDetailList = jkfOrderDetailList;
    }

    public EcoPortJKFGoodsPurchaserGson getJkfGoodsPurchaser() {
        return jkfGoodsPurchaser;
    }

    public void setJkfGoodsPurchaser(EcoPortJKFGoodsPurchaserGson jkfGoodsPurchaser) {
        this.jkfGoodsPurchaser = jkfGoodsPurchaser;
    }
}
