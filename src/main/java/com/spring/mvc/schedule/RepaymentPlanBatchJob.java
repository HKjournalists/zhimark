package com.spring.mvc.schedule;

import com.spring.mvc.model.RepaymentPlanModel;
import com.spring.mvc.repository.RepaymentPlanRepository;
import com.spring.mvc.service.RepaymentPlanService;
import com.spring.mvc.utils.MapUtils;

import java.util.List;

import static com.spring.mvc.model.RepaymentPlanStatus.WAITING;

/**
 * Created by liluoqi on 15/6/27.
 */
public class RepaymentPlanBatchJob extends BaseBatchJob<RepaymentPlanModel> {

    private RepaymentPlanRepository repaymentPlanRepository;
    private RepaymentPlanService repaymentPlanService;

    @Override
    protected List<RepaymentPlanModel> loadData(long startIndex, int batchSize) {
        return repaymentPlanRepository.getRepaymentPlansByPagination(MapUtils.convertToMap("offset", batchSize, "startIndex",
                startIndex, "status", WAITING.getCode()));
    }

    @Override
    protected long count() {
        return repaymentPlanRepository.countAllWaitingRepaymentPlans();
    }

    @Override
    protected void executeInternal(List<RepaymentPlanModel> data) {
        repaymentPlanService.tryUpdateUnsettledRepaymentPlansToWaiting(data);
    }

    protected void injectDependency() {
        repaymentPlanRepository = (RepaymentPlanRepository) applicationContext.getBean("repaymentPlanRepository");
        repaymentPlanService = (RepaymentPlanService) applicationContext.getBean("repaymentPlanService");
    }
}
