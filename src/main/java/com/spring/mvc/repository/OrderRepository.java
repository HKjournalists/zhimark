package com.spring.mvc.repository;

import com.spring.mvc.model.OrderModel;
import com.spring.mvc.model.OrderStatus;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by liluoqi on 15/4/22.
 */
@Repository
public class OrderRepository extends BaseRepository<OrderModel> {

    private static final String GET_ALL_ORDERS_UNSENT_ECO_PORT = "getAllOrdersUnsentEcoPort";
    private static final String GET_ALL_ORDERS_UNSENT_SIN0_TRANS = "getAllOrdersUnsentSinoTrans";
    private static final String COUNT_ALL_UNSENT_DATA = "countAllUnsentData";
    private static final String GET_BY_ORDER_NO = "getByOrderNo";
    private static final String UPDATE_ORDER_STATUS = "updateOrderStatus";
    private static final String UPDATE_ORDER_BILL_WAYS = "updateOrderBillWays";
    private static final String GET_BY_ID = "getById";
    private static final String GET_ALL_UNPAID_ORDERS = "getAllUnpaidOrders";
    private static final String UPDATE_ORDER_BY_MAP = "updateOrderByMap";

    //获取所有没有发送给电子口岸的订单
    public List<OrderModel> getAllOrdersUnsentToEcoPort() {
        return queryList(GET_ALL_ORDERS_UNSENT_ECO_PORT);
    }

    //获取所有没有发送给中外运的订单
    public List<OrderModel> getAllOrdersUnsentToSinoTrans() {
        return queryList(GET_ALL_ORDERS_UNSENT_SIN0_TRANS, MapUtils.convertToMap("status", 1));
    }

    public long countOrderModelsUnsentToSinoTrans() {
        return count(COUNT_ALL_UNSENT_DATA);
    }

    public OrderModel getByOrderNo(String orderNo) {
        return querySingle(GET_BY_ORDER_NO, MapUtils.convertToMap("orderNo", orderNo));
    }

    public OrderModel getById(int id) {
        return querySingleById(GET_BY_ID, id);
    }

    public int updateOrderStatus(String orderNo, int status) {
        return update(UPDATE_ORDER_STATUS, MapUtils.convertToMap("orderNo", orderNo, "status", status));
    }

    public int updateOrderBillWays(String orderNo, String billWays) {
        return update(UPDATE_ORDER_BILL_WAYS, MapUtils.convertToMap("orderNo", orderNo, "billWays", billWays));
    }

    public List<OrderModel> getAllUnpaidOrders() {
        return queryList(GET_ALL_UNPAID_ORDERS, MapUtils.convertToMap("status", OrderStatus.REMAIN_PAY.getCode()));
    }

    public int updateOrder(Map<Object, Object> map) {
        return update(UPDATE_ORDER_BY_MAP, map);
    }
}
