package com.spring.mvc.model;

import com.spring.mvc.utils.DateUtils;

import java.util.Date;

/**
 * Created by liluoqi on 15/5/14.
 */
public class BusinessNoHistoryModel extends BaseModel {
    private String businessNo;
    private String createTime;
    private String updateTime;

    public BusinessNoHistoryModel() {
    }

    public BusinessNoHistoryModel(String businessNo) {
        this.businessNo = businessNo;
        this.createTime = DateUtils.formatDateToSeconds(new Date());
        this.updateTime = this.createTime;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
