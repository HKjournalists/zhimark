package com.spring.mvc.model.area;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by liluoqi on 15/7/31.
 */
public class CountyModel {
    @XStreamAsAttribute
    private String name;

    public CountyModel() {
    }

    public CountyModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
