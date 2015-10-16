package com.spring.mvc.model;

import java.util.Date;

/**
 * Created by liluoqi on 15/7/5.
 */
public class RepaymentApplyHistoryModel extends BaseModel {
    private int applyId;
    private int repaymentPlanId;
    private Date createdDate;
    private Date updatedDate;

    public RepaymentApplyHistoryModel() {

    }

    public RepaymentApplyHistoryModel(int applyId, int repaymentPlanId) {
        this.applyId = applyId;
        this.repaymentPlanId = repaymentPlanId;
        this.createdDate = new Date();
        this.updatedDate = this.createdDate;
    }

    public int getApplyId() {
        return applyId;
    }

    public int getRepaymentPlanId() {
        return repaymentPlanId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }
}
