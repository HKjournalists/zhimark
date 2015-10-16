package com.spring.mvc.model;

/**
 * Created by liluoqi on 15/5/12.
 */
public class CountryModel extends BaseModel {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
