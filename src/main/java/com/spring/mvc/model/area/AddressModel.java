package com.spring.mvc.model.area;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liluoqi on 15/7/31.
 */
@XStreamAlias("address")
public class AddressModel {
    @XStreamImplicit(itemFieldName = "province")
    private List<ProvinceModel> province=new ArrayList<ProvinceModel>();

    public AddressModel() {
    }

    public AddressModel(List<ProvinceModel> province) {
        this.province = province;
    }

    public List<ProvinceModel> getProvince() {
        return province;
    }
}
