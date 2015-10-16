package com.spring.mvc.model.serviceResult;

/**
 * Created by liluoqi on 15/5/5.
 */
public class EcoPortAsyncResultBodyModel {
    private AsyncJKFSignModel jkfSign;
    private AsyncJKFGoodsDeclare jkfGoodsDeclar;

    public EcoPortAsyncResultBodyModel() {

    }

    public EcoPortAsyncResultBodyModel(AsyncJKFSignModel jkfSign, AsyncJKFGoodsDeclare jkfGoodsDeclar) {
        this.jkfSign = jkfSign;
        this.jkfGoodsDeclar = jkfGoodsDeclar;
    }

    public AsyncJKFSignModel getJkfSign() {
        return jkfSign;
    }

    public void setJkfSign(AsyncJKFSignModel jkfSign) {
        this.jkfSign = jkfSign;
    }

    public AsyncJKFGoodsDeclare getJkfGoodsDeclar() {
        return jkfGoodsDeclar;
    }

    public void setJkfGoodsDeclar(AsyncJKFGoodsDeclare jkfGoodsDeclar) {
        this.jkfGoodsDeclar = jkfGoodsDeclar;
    }
}
