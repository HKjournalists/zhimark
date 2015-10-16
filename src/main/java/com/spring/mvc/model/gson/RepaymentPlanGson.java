package com.spring.mvc.model.gson;


import com.spring.mvc.model.RepaymentPlanModel;
import com.spring.mvc.model.RepaymentPlanStatus;
import com.spring.mvc.utils.DateUtils;

import java.util.Date;

import static com.spring.mvc.model.RepaymentPlanStatus.getDescriptionByCode;

/**
 * Created by liluoqi on 15/6/29.
 */
public class RepaymentPlanGson {
    private int id;
    private String orderNo;
    private int orderId;
    private int headCorpID;
    private int subCorpID;
    private int retailerCorpID;
    private double headCorpPrice;
    private double subCorpPriceFromHeadCorp;
    private double retailerPriceFromHeadCorp;
    private double retailerPrice;
    private double headCorpProfit;
    private double subCorpProfit;
    private double retailerProfit;
    private String status;
    private String belongsToCorp;
    private String applyNo;
    private String createdTime;
    private String updatedTime;

    public RepaymentPlanGson() {

    }

    public RepaymentPlanGson(RepaymentPlanModel repaymentPlan) {
        this.id = repaymentPlan.getId();
        this.orderId = repaymentPlan.getOrderId();
        this.orderNo = repaymentPlan.getOrderNo();
        this.headCorpID = repaymentPlan.getHeadCorpID();
        this.subCorpID = repaymentPlan.getSubCorpID();
        this.retailerCorpID = repaymentPlan.getRetailerCorpID();
        this.headCorpPrice = repaymentPlan.getHeadCorpPrice();
        this.subCorpPriceFromHeadCorp = repaymentPlan.getSubCorpPriceFromHeadCorp();
        this.retailerPriceFromHeadCorp = repaymentPlan.getRetailerPriceFromHeadCorp();
        this.retailerPrice = repaymentPlan.getRetailerPrice();
        this.headCorpProfit = 0;
        this.subCorpProfit = repaymentPlan.getSubCorpProfit();
        this.retailerProfit = repaymentPlan.getRetailerProfit();
        this.status = getDescriptionByCode(repaymentPlan.getStatus());
        this.belongsToCorp = repaymentPlan.getBelongsToCorp();
        this.createdTime = DateUtils.formatDateToString(repaymentPlan.getCreatedTime());
        this.updatedTime = DateUtils.formatDateToString(repaymentPlan.getUpdatedTime());
        this.applyNo = repaymentPlan.getApplyId();
    }
}
