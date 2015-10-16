package com.spring.mvc.model.gson.sinoChina;

/**
 * Created by liluoqi on 15/4/29.
 */
public class SinoGoodsPurchaserGson {
    //购买人ID
    private String id;//必填
    //姓名
    private String name;//必填
    //注册邮箱
    private String email;//非必填
    //联系电话
    private String telNumber;//必填
    //证件类型代码
    private String paperType;//非必填
    //证件号码
    private String paperNumber;//非必填
    //地址
    private String address;//非必填

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getPaperType() {
        return paperType;
    }

    public String getPaperNumber() {
        return paperNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public void setPaperNumber(String paperNumber) {
        this.paperNumber = paperNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
