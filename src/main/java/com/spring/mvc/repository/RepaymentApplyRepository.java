package com.spring.mvc.repository;

import com.spring.mvc.model.RepaymentApplyModel;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liluoqi on 15/7/1.
 */
@Repository
public class RepaymentApplyRepository extends BaseRepository<RepaymentApplyModel> {
    private static final String INSERT_REPAYMENT_APPLY = "insertRepaymentApply";
    private static final String UPDATE_REPAYMENT_APPLY_STATUS = "updateRepaymentApplyStatusById";
    private static final String GET_REPAYMENT_APPLIES_PAGINATION = "getRepaymentAppliesPagination";
    private static final String COUNT_REPAYMENT_APPLIES_BY_CONDITIONS = "countRepaymentAppliesByConditions";
    private static final String GET_REPAYMENT_APPLY_BY_ID = "getRepaymentApplyById";

    public int insertRepaymentApply(RepaymentApplyModel repaymentApply) {
        insert(INSERT_REPAYMENT_APPLY, repaymentApply);
        return repaymentApply.getId();
    }

    public int updateRepaymentApplyStatusById(long id, String status) {
        return update(UPDATE_REPAYMENT_APPLY_STATUS, MapUtils.convertToMap("id", id, "status", status,
                "updateDate", new Date()));
    }

    public List<RepaymentApplyModel> getRepaymentAppliesPagination(Map map) {
        return queryList(GET_REPAYMENT_APPLIES_PAGINATION, map);
    }


    public long countAllRepaymentAppliesByConditions(Map map) {
        return count(COUNT_REPAYMENT_APPLIES_BY_CONDITIONS, map);
    }

    public RepaymentApplyModel getRepaymentApplyById(int id) {
        return querySingleById(GET_REPAYMENT_APPLY_BY_ID, id);
    }
}
