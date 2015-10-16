package com.spring.mvc.model;

import java.util.Date;

/**
 * Created by liluoqi on 15/7/8.
 * this model mapping to ims_eso_sale_sms_template table,it shall need to insert data manually
 */
public class SmsTemplateModel extends BaseModel {
    private String templateName;
    private String templateContent;
    private String smsType;
    private int validMinutes;//有效期,分钟
    private int intervalMinutes;//发送间隔
    private int maxSendCount;//最大发送次数
    private Date createdDate;
    private Date updatedDate;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
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

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public int getValidMinutes() {
        return validMinutes;
    }

    public void setValidMinutes(int validMinutes) {
        this.validMinutes = validMinutes;
    }

    public int getIntervalMinutes() {
        return intervalMinutes;
    }

    public void setIntervalMinutes(int intervalMinutes) {
        this.intervalMinutes = intervalMinutes;
    }

    public int getMaxSendCount() {
        return maxSendCount;
    }

    public void setMaxSendCount(int maxSendCount) {
        this.maxSendCount = maxSendCount;
    }
}
