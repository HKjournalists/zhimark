package com.spring.mvc.model.gson;


import com.spring.mvc.model.RepaymentApplyModel;
import com.spring.mvc.utils.DateUtils;

import static com.spring.mvc.model.RepaymentApplyStatus.getDescriptionByCode;

/**
 * Created by liluoqi on 15/7/2.
 */
public class RepaymentApplyGson {
    private int id;
    private String applyNo;
    private double retailerApplyAmount;
    private double subCorpApplyAmount;
    private long subCorpId;
    private long applicantId;
    private String subCorpName;
    private String retailerName;
    private String status;
    private String createdDate;
    private String updatedDate;

    public RepaymentApplyGson() {

    }

    public RepaymentApplyGson(RepaymentApplyModel repaymentApply) {
        this.id = repaymentApply.getId();
        this.applyNo = repaymentApply.getApplyNo();
        this.retailerApplyAmount = repaymentApply.getRetailerApplyAmount();
        this.subCorpApplyAmount = repaymentApply.getSubCorpApplyAmount();
        this.subCorpId = repaymentApply.getSubCorpId();
        this.applicantId = repaymentApply.getApplicantId();
        this.subCorpName = repaymentApply.getSubCorpName();
        this.retailerName = repaymentApply.getRetailerName();
        this.status = getDescriptionByCode(repaymentApply.getStatus());
        this.createdDate = DateUtils.formatDateToString(repaymentApply.getCreatedDate());
        this.updatedDate = DateUtils.formatDateToString(repaymentApply.getUpdatedDate());
    }

    public int getId() {
        return id;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public double getRetailerApplyAmount() {
        return retailerApplyAmount;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public long getSubCorpId() {
        return subCorpId;
    }

    public String getSubCorpName() {
        return subCorpName;
    }

    public String getRetailerName() {
        return retailerName;
    }

    public double getSubCorpApplyAmount() {
        return subCorpApplyAmount;
    }
}
