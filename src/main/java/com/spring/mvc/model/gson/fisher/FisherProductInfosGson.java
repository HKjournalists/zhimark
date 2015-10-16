package com.spring.mvc.model.gson.fisher;

import com.spring.mvc.model.gson.fisher.base.FisherResultDataGson;

import java.util.List;

/**
 * Created by liluoqi on 15/9/10.
 */
public class FisherProductInfosGson extends FisherResultDataGson {
    private String record_count;
    private String has_next;
    private List<FisherProductInfoGson> pros;

    public String getRecord_count() {
        return record_count;
    }

    public String getHas_next() {
        return has_next;
    }

    public List<FisherProductInfoGson> getPros() {
        return pros;
    }
}
