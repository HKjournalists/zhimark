package com.spring.mvc.model.serviceResult;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by liluoqi on 15/5/4.
 */
@XStreamAlias("mo")
public class EcoPortResultMoModel {
    @XStreamAsAttribute
    private String version="1.0.0";
    private EcoPortResultHeadModel head;
    private EcoPortResultBodyModel body;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public EcoPortResultHeadModel getHead() {
        return head;
    }

    public void setHead(EcoPortResultHeadModel head) {
        this.head = head;
    }

    public EcoPortResultBodyModel getBody() {
        return body;
    }

    public void setBody(EcoPortResultBodyModel body) {
        this.body = body;
    }
}
