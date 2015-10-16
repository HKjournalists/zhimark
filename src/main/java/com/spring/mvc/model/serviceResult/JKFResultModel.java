package com.spring.mvc.model.serviceResult;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import javax.swing.*;
import java.util.List;

/**
 * Created by liluoqi on 15/5/4.
 */
@XStreamAlias("jkfResult")
public class JKFResultModel {
    private String companyCode;
    private String businessNo;
    private String businessType;
    private String declareType;
    private String chkMark;
    private String noticeDate;
    private String noticeTime;
    private String note="";
//    @XStreamImplicit(itemFieldName = "resultList")
    private JKFResultListModel resultList;

    public JKFResultModel() {

    }

    public JKFResultModel(String companyCode, String businessNo, String businessType,
                          String declareType, String chkMark, String noticeDate,
                          String noticeTime) {
        this.companyCode = companyCode;
        this.businessNo = businessNo;
        this.businessType = businessType;
        this.declareType = declareType;
        this.chkMark = chkMark;
        this.noticeDate = noticeDate;
        this.noticeTime = noticeTime;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getDeclareType() {
        return declareType;
    }

    public void setDeclareType(String declareType) {
        this.declareType = declareType;
    }

    public String getChkMark() {
        return chkMark;
    }

    public void setChkMark(String chkMark) {
        this.chkMark = chkMark;
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }

    public JKFResultListModel getResultList() {
        return resultList;
    }

    public void setResultList(JKFResultListModel resultList) {
        this.resultList = resultList;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
