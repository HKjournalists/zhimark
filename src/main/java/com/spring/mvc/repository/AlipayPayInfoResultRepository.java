package com.spring.mvc.repository;

import com.spring.mvc.model.serviceResult.alipay.AlipayPayInfoResultModel;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

/**
 * Created by liluoqi on 15/6/12.
 */
@Repository
public class AlipayPayInfoResultRepository extends BaseRepository<AlipayPayInfoResultModel> {
    private static final String INSERT_ALIPAY_PUSH_RESULT = "insertAlipayPushResult";
    private static final String GET_SUCCESS_ALIPAY_PUSH_RESULT_BY_ORDER_NO = "getSuccessAlipayPushResultByOrderNo";

    public int insertAlipayPushResult(AlipayPayInfoResultModel alipayPayInfoResultModel) {
        return insert(INSERT_ALIPAY_PUSH_RESULT, alipayPayInfoResultModel);
    }

    public AlipayPayInfoResultModel getSuccessAlipayPushResult(String orderNo) {
        return querySingle(GET_SUCCESS_ALIPAY_PUSH_RESULT_BY_ORDER_NO,
                MapUtils.convertToMap("orderNo", orderNo,"isSuccess","T","resultCode","SUCCESS"));
    }
}
