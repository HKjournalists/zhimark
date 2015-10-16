package com.spring.mvc.model.gson.fisher.base;

/**
 * Created by liluoqi on 15/9/11.
 */
public class FisherResultGson<T extends FisherResultDataGson> {
    private String ResultCode;
    private String ResultMsg;
    private T ResultData;
    public String getResultCode() {
        return ResultCode;
    }

    public String getResultMsg() {
        return ResultMsg;
    }

    public T getResultData() {
        return ResultData;
    }
}
