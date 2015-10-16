package com.spring.mvc.schedule;

import com.spring.mvc.service.OrderService;
import com.spring.mvc.utils.Logger;
import org.quartz.JobExecutionContext;

/**
 * Created by liluoqi on 15/7/21.
 */
public class AutoSetExpiredOrderClosedJob extends BaseJob {

    private OrderService orderService;
    private Logger logger = Logger.getLogger(AutoSetExpiredOrderClosedJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        logger.info("开始执行设置超时未支付订单为结束的job");
        orderService.setExpiredOrderClosed();
    }

    protected void injectDependency() {
        orderService = (OrderService) applicationContext.getBean("orderService");
    }
}
