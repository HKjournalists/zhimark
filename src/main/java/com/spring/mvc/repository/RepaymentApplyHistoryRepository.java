package com.spring.mvc.repository;

import com.spring.mvc.model.RepaymentApplyHistoryModel;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by liluoqi on 15/7/5.
 */
@Repository
public class RepaymentApplyHistoryRepository extends BaseRepository {
    private static final String INSERT_REPAYMENT_APPLY_HISTORY = "insertRepaymentApplyHistory";
    private static final String GET_REPAYMENT_APPLY_HISTORY_BY_APPLY_ID = "getRepaymentApplyHistoryByApplyIdPagination";
    private static final String COUNT_ALL_REPAYMENT_APPLY_HISTORY_BY_APPLY_ID = "countAllRepaymentApplyHistoryByApplyId";

    public int insertRepaymentApplyHistory(RepaymentApplyHistoryModel repaymentApplyHistory) {
        return insert(INSERT_REPAYMENT_APPLY_HISTORY, repaymentApplyHistory);
    }

    public List<RepaymentApplyHistoryModel> getRepaymentApplyHistoryByApplyIdPagination(Map map) {
        return queryList(GET_REPAYMENT_APPLY_HISTORY_BY_APPLY_ID, map);
    }

    public long countAllRepaymentPlanHistoryByApplyId(int applyId) {
        return count(COUNT_ALL_REPAYMENT_APPLY_HISTORY_BY_APPLY_ID, MapUtils.convertToMap("applyId", applyId));
    }
}
