package com.spring.mvc.repository;

import com.spring.mvc.model.PayChannelReturnModel;
import com.spring.mvc.model.PayStatus;
import com.spring.mvc.model.PayType;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liluoqi on 15/9/2.
 */
@Repository
public class PayChannelReturnRepository extends BaseRepository<PayChannelReturnModel> {
    private static final String INSERT_PAY_CHANNEL_RETURN = "insertPayChannelReturn";
    private static final String GET_SUCCESS_PAY_CHANNEL_RETURN_BY_ORDER_NO_AND_CHANNEL = "getSuccessPayChannelReturnByOrderNoAndChannel";
    private static final String GET_FAIL_PAY_CHANNEL_RETURN_BY_ORDER_NO_AND_CHANNEL = "getFailPayChannelReturnByOrderNoAndChannel";

    public int insertPayChannelReturn(PayChannelReturnModel payChannelReturn) {
        return insert(INSERT_PAY_CHANNEL_RETURN, payChannelReturn);
    }

    public PayChannelReturnModel getSuccessPayChannelReturnByOrderNoAndChannel(String orderNo, PayType payType) {
        return querySingle(GET_SUCCESS_PAY_CHANNEL_RETURN_BY_ORDER_NO_AND_CHANNEL, MapUtils.convertToMap("orderNo", orderNo,
                "status", PayStatus.TRADE_FINISHED.name(), "payType", payType.name()));
    }

    public List<PayChannelReturnModel> getFailPayChannelReturnByOrderNoAndChannel(String orderNo, PayType payType) {
        return queryList(GET_FAIL_PAY_CHANNEL_RETURN_BY_ORDER_NO_AND_CHANNEL, MapUtils.convertToMap("orderNo", orderNo,
                "status", PayStatus.TRADE_FAILED.name(), "payType", payType.name()));
    }
}
