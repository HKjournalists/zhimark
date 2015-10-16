package com.spring.mvc.model;

import java.util.Date;

/**
 * Created by liluoqi on 15/7/8.
 */
public class SmsModel extends BaseModel {
    private int templateId;
    private String mobileNo;
    private String content;
    private String smsCode;
    private String smsType;
    private Date expiredDate;
    private Date createdDate;
    private Date updatedDate;

    public SmsModel() {

    }

    public SmsModel(int templateId, String mobileNo, String content, String smsCode, String smsType, Date expiredDate) {
        this.mobileNo = mobileNo;
        this.templateId = templateId;
        this.content = content;
        this.smsCode = smsCode;
        this.smsType = smsType;
        this.expiredDate = expiredDate;
        this.createdDate = new Date();
        this.updatedDate = this.createdDate;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
}
