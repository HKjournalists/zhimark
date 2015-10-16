package com.spring.mvc.schedule;

import com.spring.mvc.model.OrderModel;
import com.spring.mvc.repository.OrderRepository;
import com.spring.mvc.service.EcoPortService;
import org.quartz.JobExecutionContext;

import java.util.List;

/**
 * Created by liluoqi on 15/5/5.
 */
public class SendOrderInfoToEcoPortJob extends BaseJob {

    private OrderRepository orderRepository;
    private EcoPortService ecoPortService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        List<OrderModel> allUnsentOrders=orderRepository.getAllOrdersUnsentToEcoPort();
        for(OrderModel order:allUnsentOrders){
            ecoPortService.receiveEcoPortDeclare(order);
        }
    }

    protected void injectDependency() {
        orderRepository = (OrderRepository) applicationContext.getBean("orderRepository");
        ecoPortService = (EcoPortService) applicationContext.getBean("ecoPortService");
    }
}
