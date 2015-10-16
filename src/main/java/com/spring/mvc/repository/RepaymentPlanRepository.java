package com.spring.mvc.repository;

import com.spring.mvc.model.RepaymentPlanModel;
import com.spring.mvc.model.RepaymentPlanStatus;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.spring.mvc.model.RepaymentPlanStatus.UNSETTLED;
import static com.spring.mvc.model.RepaymentPlanStatus.WAITING;

/**
 * Created by liluoqi on 15/6/26.
 */
@Repository
public class RepaymentPlanRepository extends BaseRepository<RepaymentPlanModel> {
    private static final String INSERT_REPAYMENT_PLAN = "insertRepaymentPlan";
    private static final String GET_REPAYMENT_PLANS_BY_PAGINATION = "getRepaymentPlansByPagination";
    private static final String COUNT_ALL_REPAYMENT_PLANS_BY_RULES = "countAllRepaymentPlansByRules";
    private static final String GET_ALL_PROFIT_BRIEF_BY_STATUS = "getAllProfitBriefByStatus";
    private static final String COUNT_ALL_WAITING_REPAYMENT_PLANS = "countAllWaitingRepaymentPlans";
    private static final String UPDATE_REPAYMENT_PLAN_BY_ID = "updateRepaymentPlanById";
    private static final String UPDATE_REPAYMENT_PLAN_APPLY_NO_BY_ID = "updateRepaymentPlanApplyNoById";
    private static final String GET_ALL_REPAYMENT_PLANS = "getAllRepaymentPlans";
    private static final String QUERY_APPLIED_REPAYMENT_PLANS_PAGINATION = "queryAppliedRepaymentPlansPagination";
    private static final String COUNT_APPLIED_REPAYMENT_PLANS = "countAppliedRepaymentPlans";
    private static final String UPDATE_REPAYMENT_PLANS_STATUS_BY_APPLY_ID = "updateRepaymentPlansStatusByApplyId";
    private static final String GET_REPAYMENT_PLANS_WITHIN_IDS = "getRepaymentPlansWithinIds";
    private static final String GET_SUB_CORP_REPAYMENT_AMOUNT_BY_CONDITIONS = "getSubCorpRepaymentAmountByConditions";
    private static final String GET_REPAYMENT_PLANS_BY_ORDER_NO = "getRepaymentPlansByOrderNo";

    public int insertRepaymentPlan(RepaymentPlanModel repaymentPlan) {
        return insert(INSERT_REPAYMENT_PLAN, repaymentPlan);
    }

    public List<RepaymentPlanModel> getRepaymentPlansByPagination(Map map) {
        return queryList(GET_REPAYMENT_PLANS_BY_PAGINATION, map);
    }

    public long countAllRepaymentPlans(Map map) {
        return count(COUNT_ALL_REPAYMENT_PLANS_BY_RULES, map);
    }

    public double getAllProfitBriefByStatus(Map map) {
        return sum(GET_ALL_PROFIT_BRIEF_BY_STATUS, map);
    }

    public long countAllWaitingRepaymentPlans() {
        return count(COUNT_ALL_WAITING_REPAYMENT_PLANS, MapUtils.convertToMap("status", WAITING.getCode()));
    }

    public void updateRepaymentPlanStatus(int id, String status) {
        update(UPDATE_REPAYMENT_PLAN_BY_ID, MapUtils.convertToMap("id", id, "status", status, "updatedTime",
                new Date()));
    }

    public void updateRepaymentPlanApplyIdStatus(int id, int applyId, String status) {
        update(UPDATE_REPAYMENT_PLAN_APPLY_NO_BY_ID, MapUtils.convertToMap("id", id, "applyId", applyId,
                "status", status, "updatedTime", new Date()));
    }

    public List<RepaymentPlanModel> getAllRepaymentPlansByCriteria(Map map) {
        return queryList(GET_ALL_REPAYMENT_PLANS, map);
    }

    public List<RepaymentPlanModel> queryAppliedPlansPagination(Map map) {
        return queryList(QUERY_APPLIED_REPAYMENT_PLANS_PAGINATION, map);
    }

    public long countAppliedRepaymentPlans(int applyId) {
        return count(COUNT_APPLIED_REPAYMENT_PLANS, MapUtils.convertToMap("applyId", applyId));
    }

    public int updateRepaymentPlanStatusByApplyId(int repaymentApplyId, String status) {
        return update(UPDATE_REPAYMENT_PLANS_STATUS_BY_APPLY_ID, MapUtils.convertToMap("applyId", repaymentApplyId, "status", status));
    }

    public List<RepaymentPlanModel> getRepaymentPlansWithinIds(List<Integer> repaymentPlanIdFromHistories) {
        return queryList(GET_REPAYMENT_PLANS_WITHIN_IDS, MapUtils.convertToMap("repaymentPlanIds", repaymentPlanIdFromHistories));
    }

    public double getSubCorpRepaymentAmountByConditions(Map map) {
        return sum(GET_SUB_CORP_REPAYMENT_AMOUNT_BY_CONDITIONS, map);
    }

    public List<RepaymentPlanModel> getRepaymentPlanByOrderNo(String orderNo) {
        return queryList(GET_REPAYMENT_PLANS_BY_ORDER_NO, MapUtils.convertToMap("orderNo", orderNo));
    }
}
