package com.spring.mvc.service;

import com.spring.mvc.model.*;
import com.spring.mvc.model.gson.PaginationGson;
import com.spring.mvc.model.gson.RepaymentApplyGson;
import com.spring.mvc.repository.AgencyRepository;
import com.spring.mvc.repository.RepaymentApplyHistoryRepository;
import com.spring.mvc.repository.RepaymentApplyRepository;
import com.spring.mvc.repository.RepaymentPlanRepository;
import com.spring.mvc.utils.DateUtils;
import com.spring.mvc.utils.Logger;
import com.spring.mvc.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.spring.mvc.helper.ConstantsHelper.PAGE_SIZE;
import static com.spring.mvc.model.RepaymentApplyStatus.DECLINED;
import static com.spring.mvc.model.RepaymentApplyStatus.PAID;
import static com.spring.mvc.model.RepaymentPlanStatus.PROCESSING;
import static com.spring.mvc.model.RepaymentPlanStatus.UNSETTLED;
import static com.spring.mvc.model.RepaymentPlanStatus.WAITING;

/**
 * Created by liluoqi on 15/7/1.
 */
@Service
public class RepaymentApplyService {

    private Logger logger = Logger.getLogger(RepaymentApplyService.class);

    @Autowired
    private RepaymentApplyRepository repaymentApplyRepository;
    @Autowired
    private RepaymentPlanRepository repaymentPlanRepository;
    @Autowired
    private AgencyRepository agencyRepository;
    @Autowired
    private RepaymentApplyHistoryRepository repaymentApplyHistoryRepository;

    @Transactional
    public boolean createRepaymentApply(double applyAmount, int applicantId, String startTime, String endTime) {
        try {
            Map map = MapUtils.convertToMap(
                    "retailerCorpId", applicantId,
                    "status", UNSETTLED.getCode(), "startTime", DateUtils.convertToDateFromString(startTime),
                    "endTime", DateUtils.convertToDateFromString(endTime)
            );
            AgencyModel agency = agencyRepository.getByAgencyId(applicantId);
            AgencyModel fatherAgency = agencyRepository.getByAgencyId(Integer.valueOf(agency.getOid()));
            int id = repaymentApplyRepository.insertRepaymentApply(new RepaymentApplyModel(applyAmount,
                    repaymentPlanRepository.getSubCorpRepaymentAmountByConditions(map), Integer.valueOf(agency.getOid()), applicantId, fatherAgency.getName(), agency.getName()));
            logger.info(String.format("经销商ID:%s申请的返佣金额是:%s", applicantId, applyAmount));
            logger.info(String.format("根据经销商的ID:%s,开始时间:%s,结束时间:%s获取未结算的全部佣金计划,更新全部佣金计划的申请编号为:%s,状态为正在结算", applicantId, startTime, endTime, id));
            List<RepaymentPlanModel> applySettleRepaymentPlans = repaymentPlanRepository.getAllRepaymentPlansByCriteria(map);
            for (RepaymentPlanModel repaymentPlan : applySettleRepaymentPlans) {
                repaymentPlanRepository.updateRepaymentPlanApplyIdStatus(repaymentPlan.getId(), id, PROCESSING.getCode());
                repaymentApplyHistoryRepository.insertRepaymentApplyHistory(new RepaymentApplyHistoryModel(id, repaymentPlan.getId()));
            }
            return true;
        } catch (ParseException e) {
            logger.error(String.format("格式化时间字符串:%s为时间Date格式异常:%s", startTime, e.getMessage()), e);
        } catch (Exception e) {
            logger.error(String.format("经销商ID:%s,申请返佣:%s元异常:%s", applicantId, applyAmount, e.getMessage()), e);
        }
        return false;
    }


    public PaginationGson queryAppliesPagination(String applicantId, String subCorpId, String status, int currentPage, String startTime, String endTime) {
        try {
            long totalCount = repaymentApplyRepository.countAllRepaymentAppliesByConditions(MapUtils.convertToMap(
                    "applicantId", applicantId, "subCorpId", subCorpId, "status", status, "startDate", DateUtils.convertToDateFromString(startTime)
                    , "endDate", DateUtils.convertToDateFromString(endTime)
            ));
            logger.info(String.format("返佣申请记录共:%s条", totalCount));

            if (totalCount > 0) {
                List<RepaymentApplyGson> repaymentApplyGsons = new ArrayList<RepaymentApplyGson>();
                List<RepaymentApplyModel> repaymentApplyModelList = repaymentApplyRepository.getRepaymentAppliesPagination(MapUtils.convertToMap(
                        "applicantId", applicantId, "subCorpId", subCorpId, "status", status, "startDate", DateUtils.convertToDateFromString(startTime)
                        , "endDate", DateUtils.convertToDateFromString(endTime), "startIndex", (currentPage - 1) * PAGE_SIZE, "offset", PAGE_SIZE
                ));
                for (RepaymentApplyModel repaymentApply : repaymentApplyModelList) {
                    repaymentApplyGsons.add(new RepaymentApplyGson(repaymentApply));
                }
                return new PaginationGson(totalCount, currentPage, PAGE_SIZE, repaymentApplyGsons);
            }
        } catch (ParseException e) {
            logger.error(String.format("格式化开始时间字符串:%s,结束时间字符串:%s为Date格式异常", startTime, endTime), e);
        }
        return new PaginationGson();
    }

    /**
     * @param repaymentApplyId 佣金申请ID,支付
     * @return
     */
    @Transactional
    public boolean approveRepaymentApplyVerity(int repaymentApplyId) {
        try {
            logger.info(String.format("处理返佣申请ID为支付:%s", repaymentApplyId));
            return repaymentApplyRepository.updateRepaymentApplyStatusById(repaymentApplyId, PAID.getCode()) > 0;
        } catch (Exception e) {
            logger.error(String.format("通过支付返佣申请ID:%s异常:%s", repaymentApplyId, e.getMessage()), e);
            return false;
        }
    }

    /**
     * @param repaymentApplyId 佣金申请拒绝
     * @return
     */
    @Transactional
    public boolean declineRepaymentApplyVerity(int repaymentApplyId) {
        try {
            logger.info(String.format("处理返佣申请ID为拒绝:%s", repaymentApplyId));
            repaymentPlanRepository.updateRepaymentPlanStatusByApplyId(repaymentApplyId, UNSETTLED.getCode());
            return repaymentApplyRepository.updateRepaymentApplyStatusById(repaymentApplyId, DECLINED.getCode()) > 0;
        } catch (Exception e) {
            logger.error(String.format("拒绝审核通过返佣申请ID:%s异常:%s", repaymentApplyId, e.getMessage()), e);
            return false;
        }
    }

    public List<RepaymentApplyHistoryModel> getAppliedRepaymentPlansByApplyIdPagination(int applyId, int currentPage) {
        try {
            logger.info(String.format("根据收到的applyId:%s查询申请返佣的历史记录", applyId));
            return repaymentApplyHistoryRepository.getRepaymentApplyHistoryByApplyIdPagination(MapUtils.convertToMap("applyId", applyId,
                    "startIndex", (currentPage - 1) * PAGE_SIZE, "offset", PAGE_SIZE));
        } catch (Exception e) {
            logger.info(String.format("根据收到的applyId:%s查询申请返佣的历史记录异常:%s", applyId, e.getMessage()), e);
            return new ArrayList<RepaymentApplyHistoryModel>();
        }
    }

    public long countAllRepaymentPlansByApplyId(int applyId) {
        try {
            logger.info(String.format("收到返佣申请ID为:%s计算全部的返佣计划历史", applyId));
            return repaymentApplyHistoryRepository.countAllRepaymentPlanHistoryByApplyId(applyId);
        } catch (Exception e) {
            logger.info(String.format("收到返佣申请ID为:%s计算全部的返佣计划历史异常:%s", applyId, e.getMessage()), e);
            return 0;
        }
    }
}
