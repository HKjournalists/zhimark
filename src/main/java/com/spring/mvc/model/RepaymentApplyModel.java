package com.spring.mvc.model;

import com.spring.mvc.utils.BusinessNoGenerator;

import java.util.Date;

import static com.spring.mvc.model.RepaymentApplyStatus.NEW;

/**
 * 佣金申请记录
 * Created by liluoqi on 15/7/1.
 */
public class RepaymentApplyModel extends BaseModel {
    private String applyNo;
    private double retailerApplyAmount;
    private double subCorpApplyAmount;
    private long subCorpId;
    private long applicantId;
    private String subCorpName;
    private String retailerName;
    private String status;
    private Date createdDate;
    private Date updatedDate;

    public RepaymentApplyModel() {

    }

    public RepaymentApplyModel(double retailerApplyAmount, double subCorpApplyAmount, long subCorpId, long applicantId, String subCorpName, String retailerName) {
        this.applyNo = BusinessNoGenerator.generateAlipayRequestNo();
        this.retailerApplyAmount = retailerApplyAmount;
        this.subCorpApplyAmount = subCorpApplyAmount;
        this.subCorpId = subCorpId;
        this.applicantId = applicantId;
        this.subCorpName = subCorpName;
        this.retailerName = retailerName;
        this.status = NEW.getCode();
        this.createdDate = new Date();
        this.updatedDate = this.createdDate;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyeNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public double getRetailerApplyAmount() {
        return retailerApplyAmount;
    }

    public long getSubCorpId() {
        return subCorpId;
    }

    public void setSubCorpId(long subCorpId) {
        this.subCorpId = subCorpId;
    }

    public String getSubCorpName() {
        return subCorpName;
    }

    public void setSubCorpName(String subCorpName) {
        this.subCorpName = subCorpName;
    }

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }

    public double getSubCorpApplyAmount() {
        return subCorpApplyAmount;
    }
}
