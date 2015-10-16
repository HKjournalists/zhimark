package com.spring.mvc.repository;

import com.spring.mvc.model.OrderGoodModel;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liluoqi on 15/5/7.
 */
@Repository
public class OrderGoodRepository extends BaseRepository<OrderGoodModel> {
    private static final String GET_ORDER_GOODS_BY_ORDER_ID = "getOrderGoodsByOrderId";
    private static final String COUNT_ALL_ORDER_GOODS_BY_ORDER_ID = "countAllOrderGoodsByOrderId";

    public List<OrderGoodModel> getOrderGoodsByOrderId(int orderId) {
        return queryList(GET_ORDER_GOODS_BY_ORDER_ID, MapUtils.convertToMap("orderId", orderId));
    }

    public long countAllGoodsByOrderId(int orderId) {
        return count(COUNT_ALL_ORDER_GOODS_BY_ORDER_ID, MapUtils.convertToMap("orderId", orderId));
    }
}
