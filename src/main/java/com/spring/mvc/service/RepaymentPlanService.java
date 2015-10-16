package com.spring.mvc.service;

import com.spring.mvc.model.*;
import com.spring.mvc.model.gson.PaginationGson;
import com.spring.mvc.model.gson.RepaymentPlanGson;
import com.spring.mvc.model.gson.RepaymentProfitBriefGson;
import com.spring.mvc.repository.*;
import com.spring.mvc.utils.DateUtils;
import com.spring.mvc.utils.Logger;
import com.spring.mvc.utils.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

import static com.spring.mvc.helper.ConstantsHelper.PAGE_SIZE;
import static com.spring.mvc.model.RepaymentPlanStatus.*;
import static com.spring.mvc.utils.MathUtils.add;
import static com.spring.mvc.utils.MathUtils.minus;
import static com.spring.mvc.utils.MathUtils.multiple;

/**
 * Created by liluoqi on 15/6/26.
 */
@Service
public class RepaymentPlanService {
    private Logger logger = Logger.getLogger(RepaymentPlanService.class);

    @Autowired
    private RepaymentPlanRepository repaymentPlanRepository;
    @Autowired
    private RepaymentApplyService repaymentApplyService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderGoodRepository orderGoodRepository;
    @Autowired
    private AgencyRepository agencyRepository;
    @Autowired
    private GroupGoodRepository groupGoodRepository;
    @Autowired
    private AgencyGoodRepository agencyGoodRepository;
    @Autowired
    private AgencyGoodPriceRepository agencyGoodPriceRepository;

    public PaginationGson queryPlansByConditions(String currentPage, String headCorpId, String subCorpId, String retailerCorpId, String status, String startTime, String endTime) {
        try {
            List<RepaymentPlanModel> repaymentPlanList = repaymentPlanRepository.getRepaymentPlansByPagination(MapUtils.convertToMap(
                    "startIndex", (Integer.valueOf(currentPage) - 1) * PAGE_SIZE, "offset", PAGE_SIZE, "headCorpId", headCorpId, "subCorpId", subCorpId,
                    "retailerCorpId", retailerCorpId, "status", status, "startTime", DateUtils.convertToDateFromString(startTime), "endTime", DateUtils.convertToDateFromString(endTime)
                    , "pageSize", PAGE_SIZE
            ));

            long totalCount = repaymentPlanRepository.countAllRepaymentPlans(MapUtils.convertToMap(
                    "headCorpId", headCorpId, "subCorpId", subCorpId, "retailerCorpId", retailerCorpId,
                    "status", status, "startTime", DateUtils.convertToDateFromString(startTime), "endTime", DateUtils.convertToDateFromString(endTime)
            ));
            List<RepaymentPlanGson> repaymentPlanGsonList = new ArrayList<RepaymentPlanGson>();
            if (totalCount > 0) {
                for (RepaymentPlanModel repaymentPlan : repaymentPlanList) {
                    repaymentPlanGsonList.add(new RepaymentPlanGson(repaymentPlan));
                }
            }

            logger.info(String.format("total count is :%s", totalCount));
            return new PaginationGson(totalCount, Integer.valueOf(currentPage), PAGE_SIZE, repaymentPlanGsonList);
        } catch (ParseException e) {
            logger.error(String.format("日期格式化异常:%s", e.getMessage()), e);
            return new PaginationGson();
        }
    }

    public RepaymentProfitBriefGson getProfitGeneral(String headCorpId, String subCorpId, String retailerCorpId, String startTime, String endTime) {
        try {
            double unsettledProfit = repaymentPlanRepository.getAllProfitBriefByStatus(MapUtils.convertToMap("headCorpId", headCorpId, "subCorpId", subCorpId,
                    "retailerCorpId", retailerCorpId, "status", UNSETTLED.getCode(), "startTime", DateUtils.convertToDateFromString(startTime),
                    "endTime", DateUtils.convertToDateFromString(endTime)));
            double waitingProfit = repaymentPlanRepository.getAllProfitBriefByStatus(MapUtils.convertToMap("headCorpId", headCorpId, "subCorpId", subCorpId,
                    "retailerCorpId", retailerCorpId, "status", WAITING.getCode(), "startTime", DateUtils.convertToDateFromString(startTime),
                    "endTime", DateUtils.convertToDateFromString(endTime)));
            double processingProfit = repaymentPlanRepository.getAllProfitBriefByStatus(MapUtils.convertToMap("headCorpId", headCorpId, "subCorpId", subCorpId,
                    "retailerCorpId", retailerCorpId, "status", PROCESSING.getCode(), "startTime", DateUtils.convertToDateFromString(startTime),
                    "endTime", DateUtils.convertToDateFromString(endTime)));
            double settledProfit = repaymentPlanRepository.getAllProfitBriefByStatus(MapUtils.convertToMap("headCorpId", headCorpId, "subCorpId", subCorpId,
                    "retailerCorpId", retailerCorpId, "status", SETTLED.getCode(), "startTime", DateUtils.convertToDateFromString(startTime),
                    "endTime", DateUtils.convertToDateFromString(endTime)));

            long unsettledOrderCount = repaymentPlanRepository.countAllRepaymentPlans(MapUtils.convertToMap("headCorpId", headCorpId, "subCorpId", subCorpId,
                    "retailerCorpId", retailerCorpId, "status", UNSETTLED.getCode(), "startTime", DateUtils.convertToDateFromString(startTime),
                    "endTime", DateUtils.convertToDateFromString(endTime)));
            long waitingOrderCount = repaymentPlanRepository.countAllRepaymentPlans(MapUtils.convertToMap("headCorpId", headCorpId, "subCorpId", subCorpId,
                    "retailerCorpId", retailerCorpId, "status", WAITING.getCode(), "startTime", DateUtils.convertToDateFromString(startTime),
                    "endTime", DateUtils.convertToDateFromString(endTime)));
            long processingOrderCount = repaymentPlanRepository.countAllRepaymentPlans(MapUtils.convertToMap("headCorpId", headCorpId, "subCorpId", subCorpId,
                    "retailerCorpId", retailerCorpId, "status", PROCESSING.getCode(), "startTime", DateUtils.convertToDateFromString(startTime),
                    "endTime", DateUtils.convertToDateFromString(endTime)));
            long settledOrderCount = repaymentPlanRepository.countAllRepaymentPlans(MapUtils.convertToMap("headCorpId", headCorpId, "subCorpId", subCorpId,
                    "retailerCorpId", retailerCorpId, "status", SETTLED.getCode(), "startTime", DateUtils.convertToDateFromString(startTime),
                    "endTime", DateUtils.convertToDateFromString(endTime)));

            return new RepaymentProfitBriefGson(settledProfit, processingProfit, unsettledProfit, waitingProfit,
                    settledOrderCount, processingOrderCount, unsettledOrderCount, waitingOrderCount);
        } catch (Exception e) {
            logger.error(String.format("获取公司利润异常:%s", e.getMessage()), e);
            return new RepaymentProfitBriefGson();
        }
    }

    //todo 需要重构，当用户一个订单包含多个商品组（商品组还可能不同）,这个算法需要重新调整
    @Transactional
    public void addRepaymentPlan(String orderNo) {
        logger.info(String.format("创建订单号:%s的返佣计划", orderNo));
        OrderModel order = orderRepository.getByOrderNo(orderNo);
        List<RepaymentPlanModel> repaymentPlans = repaymentPlanRepository.getRepaymentPlanByOrderNo(orderNo);
        if (repaymentPlans != null && repaymentPlans.size() > 0) {
            logger.warn(String.format("该笔订单:%s已经有了返佣计划,不能重复创建", orderNo));
            return;
        }
        double headCorpPrice = 0;
        double subCorpPriceFromHeadCorp = 0;
        double retailerPriceFromHeadCorp = 0;
        double retailerPrice = 0;
        double subCorpProfit = 0;
        double retailerProfit = 0;
        List<OrderGoodModel> orderGoods = orderGoodRepository.getOrderGoodsByOrderId(order.getId());
        AgencyGoodModel agencyGoodModel = agencyGoodRepository.getById(Integer.valueOf(
                agencyGoodPriceRepository.getAgencyGoodPriceModelById(Integer.valueOf(orderGoods.get(0).getLid())).getBid()));
        int retailerCorpId = agencyGoodModel.getMid();
        logger.info(String.format("订单：%s返佣计划里面零售商是:%s", orderNo, retailerCorpId));
        AgencyModel retailerCorp = agencyRepository.getByAgencyId(retailerCorpId);
        AgencyModel belongsToCorp = agencyRepository.getByAgencyId(Integer.valueOf(retailerCorp.getOid()));//这个是所示分公司的信息
        AgencyModel headCorp = agencyRepository.getByAgencyId(Integer.valueOf(belongsToCorp.getOid()));
        for (OrderGoodModel orderGood : orderGoods) {
            int orderGoodTotal = orderGood.getTotal();
            AgencyGoodPriceModel agencyGoodPrice = agencyGoodPriceRepository.getAgencyGoodPriceModelById(Integer.valueOf(orderGood.getLid()));
            GroupGoodModel groupGood = groupGoodRepository.getGroupGoodById(Integer.valueOf(agencyGoodPrice.getFz()));
            String groupGoodJe = groupGood.getJe();
            headCorpPrice = add(headCorpPrice, multiple(Double.valueOf(groupGoodJe == null ? "0" : groupGoodJe), orderGoodTotal));
            subCorpPriceFromHeadCorp = add(subCorpPriceFromHeadCorp, multiple(Double.valueOf(agencyGoodPrice.getFgsj()), orderGoodTotal));
            retailerPriceFromHeadCorp = add(retailerPriceFromHeadCorp, multiple(Double.valueOf(agencyGoodPrice.getJxsj()), orderGoodTotal));
            retailerPrice = add(retailerPriceFromHeadCorp, multiple(Double.valueOf(agencyGoodPrice.getLsj()), orderGoodTotal));
            subCorpProfit = add(subCorpProfit, multiple(minus(agencyGoodPrice.getJxsj(), agencyGoodPrice.getFgsj()), orderGoodTotal));
            retailerProfit = add(retailerProfit, multiple(minus(agencyGoodPrice.getLsj(), agencyGoodPrice.getJxsj()), orderGoodTotal));
        }
        repaymentPlanRepository.insertRepaymentPlan(new RepaymentPlanModel(order.getId(), orderNo, headCorp.getId(), Integer.valueOf(retailerCorp.getOid()),
                retailerCorp.getId(), headCorpPrice, subCorpPriceFromHeadCorp, retailerPriceFromHeadCorp, retailerPrice, subCorpProfit, retailerProfit,
                belongsToCorp.getName(), WAITING));
    }

    @Transactional
    public void tryUpdateUnsettledRepaymentPlansToWaiting(List<RepaymentPlanModel> data) {
        try {
            for (RepaymentPlanModel repaymentPlan : data) {
                logger.info(String.format("处理返佣计划repayment plan id:%s,判断是否可以变更为未申请", repaymentPlan.getId()));
                if (DateUtils.intervalDays(repaymentPlan.getCreatedTime(), DateUtils.startOfDate(new Date())) >= 7) {
                    logger.info(String.format("返佣计划id:%s距今已经或超过7天，修改状态为未申请", repaymentPlan.getId()));
                    repaymentPlanRepository.updateRepaymentPlanStatus(repaymentPlan.getId(), UNSETTLED.getCode());
                }
            }
        } catch (Exception e) {
            logger.error(String.format("执行返佣计划状态检查修改的时候报错:%s", e.getMessage()), e);
        }
    }

    public PaginationGson queryAppliedPlansPagination(String applyId, String currentPage) {
        try {
            logger.info(String.format("根据返佣申请ID:%s获取全部的返佣计划", applyId));
            int page = Integer.valueOf(currentPage);
            int repaymentApplyId = Integer.valueOf(applyId);
            long totalCount = repaymentApplyService.countAllRepaymentPlansByApplyId(repaymentApplyId);
            List<RepaymentApplyHistoryModel> repaymentApplyHistories = repaymentApplyService.getAppliedRepaymentPlansByApplyIdPagination(repaymentApplyId, page);
            List<RepaymentPlanModel> appliedRepaymentPlans = repaymentPlanRepository.getRepaymentPlansWithinIds(getRepaymentPlanIdFromHistories(repaymentApplyHistories));
            List<RepaymentPlanGson> appliedRepaymentPlanGsons = new ArrayList<RepaymentPlanGson>();
            for (RepaymentPlanModel appliedRepaymentPlan : appliedRepaymentPlans) {
                appliedRepaymentPlanGsons.add(new RepaymentPlanGson(appliedRepaymentPlan));
            }
            return new PaginationGson(totalCount, page, PAGE_SIZE, appliedRepaymentPlanGsons);
        } catch (NumberFormatException e) {
            logger.error(String.format("将返佣申请ID参数:%s转为整型异常:%s", applyId, e.getMessage()), e);
        } catch (Exception e) {
            logger.error(String.format("根据返佣申请ID:%s获取佣金计划异常:%s", applyId, e.getMessage()), e);
        }
        return new PaginationGson();
    }

    private List<Integer> getRepaymentPlanIdFromHistories(List<RepaymentApplyHistoryModel> repaymentApplyHistories) {
        List<Integer> planIds = new ArrayList<Integer>();
//        repaymentApplyHistories.forEach((repaymentApplyHistory) -> planIds.add(repaymentApplyHistory.getRepaymentPlanId()));
        for (RepaymentApplyHistoryModel repaymentApplyHistory : repaymentApplyHistories) {
            planIds.add(repaymentApplyHistory.getRepaymentPlanId());
        }
//        Collections.sort(planIds, (Integer before, Integer after) -> (before.compareTo(after)));
        Collections.sort(planIds, new Comparator<Integer>() {
            @Override
            public int compare(Integer before, Integer after) {
                if (before <= after) {
                    return 1;
                }
                return -1;
            }
        });
        return planIds;
    }
}
