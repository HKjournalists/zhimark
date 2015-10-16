package com.spring.mvc.model.serviceResult;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by liluoqi on 15/5/5.
 */
@XStreamAlias("mo")
public class EcoPortAsyncResultMoModel {

    @XStreamAsAttribute
    private String verison = "1.0.0";
    private EcoPortAsyncResultHeadModel head;
    private EcoPortAsyncResultBodyModel body;

    public EcoPortAsyncResultMoModel() {

    }

    public EcoPortAsyncResultMoModel(EcoPortAsyncResultHeadModel head,
                                     EcoPortAsyncResultBodyModel body) {
        this.head = head;
        this.body = body;
    }

    public String getVerison() {
        return verison;
    }

    public void setVerison(String verison) {
        this.verison = verison;
    }

    public EcoPortAsyncResultHeadModel getHead() {
        return head;
    }

    public void setHead(EcoPortAsyncResultHeadModel head) {
        this.head = head;
    }

    public EcoPortAsyncResultBodyModel getBody() {
        return body;
    }

    public void setBody(EcoPortAsyncResultBodyModel body) {
        this.body = body;
    }
}
