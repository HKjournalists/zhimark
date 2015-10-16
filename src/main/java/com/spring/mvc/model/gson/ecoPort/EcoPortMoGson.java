package com.spring.mvc.model.gson.ecoPort;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by liluoqi on 15/5/1.
 */
@XStreamAlias("mo")
public class EcoPortMoGson {
    @XStreamAsAttribute
    private String version = "1.0.0";
    private EcoPortHeadGson head;
    private EcoPortBodyGson body;

    public EcoPortMoGson(){

    }

    public EcoPortMoGson(EcoPortHeadGson head, EcoPortBodyGson body) {
        this.head = head;
        this.body = body;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public EcoPortHeadGson getHead() {
        return head;
    }

    public void setHead(EcoPortHeadGson head) {
        this.head = head;
    }

    public EcoPortBodyGson getBody() {
        return body;
    }

    public void setBody(EcoPortBodyGson body) {
        this.body = body;
    }
}
