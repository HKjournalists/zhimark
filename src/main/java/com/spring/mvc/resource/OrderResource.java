package com.spring.mvc.resource;

import com.google.gson.Gson;
import com.spring.mvc.model.OrderStatus;
import com.spring.mvc.service.OrderService;
import com.spring.mvc.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by liluoqi on 15/9/19.
 */
@Component
@Resource
@Path("order")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    private static Logger logger = Logger.getLogger(OrderResource.class);

    @Path("set-status")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String setOrderFinish(@FormParam("orderNo") String orderNo, @FormParam("status") String status, @FormParam("remark") String remark) {
        logger.info(String.format("更新订单号:%s的状态为:%s", orderNo, status));
        OrderStatus orderStatus = OrderStatus.getStatusByName(status);
        if (orderStatus != null && orderService.updateOrderStatusByOrderNo(orderNo, orderStatus, remark)) {
            logger.info(String.format("更新订单号状态为:%s成功:%s", orderNo, status));
            return new Gson().toJson(true);
        }
        return new Gson().toJson(false);
    }
}
