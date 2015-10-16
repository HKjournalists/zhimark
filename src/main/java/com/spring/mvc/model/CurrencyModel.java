package com.spring.mvc.model;

/**
 * 货币代码
 * Created by liluoqi on 15/5/7.
 */
public class CurrencyModel extends BaseModel {
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
