package com.spring.mvc.repository;

import com.spring.mvc.model.OrderSentHistoryModel;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liluoqi on 15/5/14.
 */
@Repository
public class OrderSentHistoryRepository extends BaseRepository<OrderSentHistoryModel> {
    private static final String INSERT_ORDER_SENT_HISTORY = "insertOrderSentHistory";
    private static final String GET_BY_BUSINESS_NO = "getOrderByBusinessNo";
    private static final String GET_BY_ORDER_NO = "getOrderSendHistoryByOrderNo";

    public int insertOrderSentHistory(OrderSentHistoryModel orderSentHistoryModel) {
        return insert(INSERT_ORDER_SENT_HISTORY, orderSentHistoryModel);
    }

    public List<OrderSentHistoryModel> getByBusinessNo(String businessNo) {
        return queryList(GET_BY_BUSINESS_NO, MapUtils.convertToMap("businessNo", businessNo));
    }

    public List<OrderSentHistoryModel> getByOrderNo(String orderNo) {
        return queryList(GET_BY_ORDER_NO, MapUtils.convertToMap("orderNo", orderNo));
    }
}
