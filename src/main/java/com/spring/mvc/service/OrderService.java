package com.spring.mvc.service;

import com.spring.mvc.model.McMemberModel;
import com.spring.mvc.model.OrderModel;
import com.spring.mvc.model.OrderStatus;
import com.spring.mvc.repository.McMemberRepository;
import com.spring.mvc.repository.OrderRepository;
import com.spring.mvc.utils.DateUtils;
import com.spring.mvc.utils.Logger;
import com.spring.mvc.utils.MapUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.spring.mvc.helper.ConstantsHelper.UNPAID_ORDER_EXPIRED_MINUTES;

/**
 * Created by liluoqi on 15/7/9.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private McMemberRepository mcMemberRepository;

    private Logger logger = Logger.getLogger(OrderService.class);

    public McMemberModel getPurchaserByOrderNo(String orderNo) {
        OrderModel order = orderRepository.getByOrderNo(orderNo);
        return order == null ? null : mcMemberRepository.getMcMemberByUid(Integer.valueOf(order.getFrom_user()));
    }

    public void setExpiredOrderClosed() {
        try {
            logger.info("判断所有未支付的订单是否超时,如果超时则自动将其关闭");
            List<OrderModel> orderModelList = orderRepository.getAllUnpaidOrders();
            for (OrderModel orderModel : orderModelList) {
                if (isExpired(orderModel)) {
                    logger.info(String.format("订单号:%s未支付超时将其置为关闭状态", orderModel.getOrdersn()));
                    orderRepository.updateOrderStatus(orderModel.getOrdersn(), OrderStatus.CLOSE.getCode());
                }
            }
        } catch (Exception e) {
            logger.error(String.format("执行操作超时未支付订单关闭job异常:%s", e.getMessage()), e);
        }
    }

    public boolean updateOrderStatusByOrderNo(String orderNo, OrderStatus status, String remark) {
        int orderId = orderRepository.getByOrderNo(orderNo).getId();
        return orderRepository.updateOrder(MapUtils.convertToMap("id", orderId, "orderNo", orderNo, "status", status.getCode(), "remark", remark)) > 0;
    }

    private boolean isExpired(OrderModel order) {
        Date createdDate = DateUtils.formatUnixTimestampToDate(order.getCreatetime());
        return new DateTime(createdDate).plusMinutes(UNPAID_ORDER_EXPIRED_MINUTES).isBefore(new DateTime());
    }
}
