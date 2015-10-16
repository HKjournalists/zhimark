package com.spring.mvc.model.area;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liluoqi on 15/7/31.
 */
public class CityModel {
    @XStreamAsAttribute
    private String name;
    @XStreamImplicit(itemFieldName = "county")
    private List<CountyModel> county = new ArrayList<CountyModel>();

    public CityModel() {
    }

    public CityModel(String name, List<CountyModel> county) {
        this.name = name;
        this.county = county;
    }

    public List<CountyModel> getCounty() {
        return county;
    }

    public String getName() {
        return name;
    }
}
