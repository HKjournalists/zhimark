package com.spring.mvc.model.area;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liluoqi on 15/7/31.
 */
public class ProvinceModel {
    @XStreamAsAttribute
    private String name;
    @XStreamImplicit(itemFieldName = "city")
    private List<CityModel> city=new ArrayList<CityModel>();

    public ProvinceModel() {
    }

    public ProvinceModel(String name, List<CityModel> city) {
        this.name = name;
        this.city = city;
    }

    public List<CityModel> getCity() {
        return city;
    }

    public String getName() {
        return name;
    }
}
