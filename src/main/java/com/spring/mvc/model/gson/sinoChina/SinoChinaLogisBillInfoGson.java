package com.spring.mvc.model.gson.sinoChina;

import java.util.List;

/**
 * Created by liluoqi on 15/7/27.
 */
public class SinoChinaLogisBillInfoGson {
    private List<SinoChinaLogisSingleBillInfoGson> result;
    private String retcode;
    private String retmsg;

    public List<SinoChinaLogisSingleBillInfoGson> getResult() {
        return result;
    }

    public String getRetcode() {
        return retcode;
    }

    public String getRetmsg() {
        return retmsg;
    }
}
