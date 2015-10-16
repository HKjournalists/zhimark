package com.spring.mvc.schedule;

import com.spring.mvc.model.OrderModel;
import com.spring.mvc.repository.OrderRepository;
import com.spring.mvc.service.SinoChinaService;
import com.spring.mvc.utils.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liluoqi on 15/4/27.
 */
public class SendOrderInfoToSinoChinaJob extends BaseJob {

    private SinoChinaService sinoChinaService;
    private OrderRepository orderRepository;
    private Logger logger = Logger.getLogger(SendOrderInfoToSinoChinaJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        List<OrderModel> allUnsentOrders = orderRepository.getAllOrdersUnsentToSinoTrans();
        for (OrderModel order : allUnsentOrders) {
            try {
                logger.info(String.format("要发送给海外购的订单号:%s", order.getOrdersn()));
                sinoChinaService.sendOrdersToSinoTrans(order);
            } catch (Exception e) {
                logger.error(String.format("exception message is: %s ,stack trace :%s happens," +
                        e.getMessage(), e.getStackTrace()));
            }
        }
    }

    protected void injectDependency() {
        sinoChinaService = (SinoChinaService) applicationContext.getBean("sinoChinaService");
        orderRepository = (OrderRepository) applicationContext.getBean("orderRepository");
    }

}
