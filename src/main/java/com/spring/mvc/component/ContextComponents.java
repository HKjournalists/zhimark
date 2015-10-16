package com.spring.mvc.component;

import com.spring.mvc.proxy.sinoChina.SinoChinaProxy;
import com.spring.mvc.repository.FisherProductRepository;
import com.spring.mvc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.Context;

/**
 * Created by liluoqi on 15/7/23.
 */
@Component
public class ContextComponents {
    @Autowired
    private RepaymentPlanService repaymentPlanService;
    @Autowired
    private RepaymentApplyService repaymentApplyService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;
    @Autowired
    private SinoChinaOderSyncDataConstructorService sinoChinaOderSyncDataConstructorService;
    @Autowired
    private SinoChinaService sinoChinaService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private FisherProductRepository fisherProductRepository;
    @Autowired
    private FisherService fisherService;
    @Autowired
    private AutoSplitPackageService autoSplitPackageService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private PostalFeeCalculatorService postalFeeCalculatorService;
    @Autowired
    private PushPayInfoToAlipayService pushPayInfoToAlipayService;
    @Autowired
    private Properties properties;
    @Autowired
    private SinoChinaProxy sinoChinaProxy;

    public RepaymentPlanService getRepaymentPlanService() {
        return repaymentPlanService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public PayService getPayService() {
        return payService;
    }

    public SinoChinaOderSyncDataConstructorService getSinoChinaOderSyncDataConstructorService() {
        return sinoChinaOderSyncDataConstructorService;
    }

    public SinoChinaService getSinoChinaService() {
        return sinoChinaService;
    }

    public SmsService getSmsService() {
        return smsService;
    }

    public RepaymentApplyService getRepaymentApplyService() {
        return repaymentApplyService;
    }

    public FisherProductRepository getFisherProductRepository() {
        return fisherProductRepository;
    }

    public FisherService getFisherService() {
        return fisherService;
    }

    public AutoSplitPackageService getAutoSplitPackageService() {
        return autoSplitPackageService;
    }

    public InventoryService getInventoryService() {
        return inventoryService;
    }

    public PostalFeeCalculatorService getPostalFeeCalculatorService() {
        return postalFeeCalculatorService;
    }

    public PushPayInfoToAlipayService getPushPayInfoToAlipayService() {
        return pushPayInfoToAlipayService;
    }

    public Properties getProperties() {
        return properties;
    }

    public SinoChinaProxy getSinoChinaProxy() {
        return sinoChinaProxy;
    }
}
