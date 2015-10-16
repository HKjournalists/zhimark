package com.spring.mvc.service;

import com.google.gson.Gson;
import com.spring.mvc.component.Properties;
import com.spring.mvc.model.*;
import com.spring.mvc.model.gson.sinoChina.*;
import com.spring.mvc.proxy.sinoChina.SinoChinaProxy;
import com.spring.mvc.repository.*;
import com.spring.mvc.model.serviceResult.SinoChinaServiceResultModel;
import com.spring.mvc.model.serviceResult.SinoChinaResultModel;
import com.spring.mvc.service.base.DeclareCustomService;
import com.spring.mvc.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.spring.mvc.model.OrderStatus.DONE;
import static com.spring.mvc.model.OrderStatus.FAIL;


/**
 * 同中外运对接的业务逻辑代码
 * Created by liluoqi on 15/4/24.
 */
@Service
public class SinoChinaService extends DeclareCustomService {

    private Logger logger = Logger.getLogger(SinoChinaService.class);
    private static final String DECLARE_SUCCESS = "1";

    @Autowired
    private Properties properties;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderSentHistoryRepository orderSentHistoryRepository;
    @Autowired
    private BusinessNoRepository businessNoRepository;
    @Autowired
    private SinoResultRecordRepository sinoResultRecordRepository;
    @Autowired
    private RepaymentPlanService repaymentPlanService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private SinoChinaOderSyncDataConstructorService sinoChinaOderSyncDataConstructorService;
    @Autowired
    private SinoChinaProxy sinoChinaProxy;


    /**
     * 发送订单数据到中外运
     * service方法会被job调用
     * todo 此方法会被废弃掉
     */
    @Deprecated
    public void sendOrdersToSinoTrans(OrderModel order) throws Exception {
        try {
            if (isOrderFitForDeclare(order) && isAllFromHaiWaiGou(order)) {
                String businessNo = BusinessNoGenerator.generateSinoBusinessNo(order.getOrdersn(), properties.getAppId());
                Map map = MapUtils.convertToMap("app_id", properties.getAppId(), "auth_token", getTokenFromSinoChina(properties.getAppId(), properties.getAuthCode()), "custom_code", "2991",
                        "function", properties.getSendOrderFunction(), "business_no", businessNo,
                        "type", "0", "data", new Gson().toJson(new SinoChinaOrderRequestDataGson(sinoChinaOderSyncDataConstructorService.constructOrderHeader(order),
                                sinoChinaOderSyncDataConstructorService.constructOrderDetailList(order), sinoChinaOderSyncDataConstructorService.constructGoodsPurchaser(order))));
                logger.info(String.format("发送到海外购的订单信息是:%s", new Gson().toJson(map)));
                SinoChinaResultModel result = sinoChinaProxy.declareOrderToHaiWaiGou(map, SinoChinaResultModel.class);
                orderSentHistoryRepository.insertOrderSentHistory(new OrderSentHistoryModel(businessNo, order.getOrdersn()));
                businessNoRepository.insertBusinessNo(new BusinessNoHistoryModel(businessNo));
                logger.info(String.format("针对中外运返回的结果做处理，更新系统内部订单号:%s的状态", order.getOrdersn()));
                handleSyncResultFromSinoChina(result, order.getOrdersn());
            } else {
                logger.info(String.format("订单号:%s不满足推送的条件", order.getOrdersn()));
            }
        } catch (Exception e) {
            logger.error(String.format("发送中外运订单号:%s数据异常:%s", order.getOrdersn(), e.getMessage()), e);
            throw e;
        }
    }

    /**
     * spring @Transactional注解开启的事物发生异常不能catch住而不硬编码处理，不然spring不会捕获这个异常并发生回滚操作
     * 处理中外运的即时回执,目前是单个订单发送，所以返回的结果只包含一个订单的回执结果，循环次数为1
     */
    @Transactional
    public void handleSyncResultFromSinoChina(SinoChinaResultModel result, String orderNo) {
        logger.info(String.format("处理海外购返回的订单号:%s,同步回执:%s", orderNo, new Gson().toJson(result)));
        for (SinoChinaServiceResultModel singleResult : result.getService_result()) {
            orderRepository.updateOrderStatus(orderNo, DECLARE_SUCCESS.equals(singleResult.getChk_mark()) ? DONE.getCode() : FAIL.getCode());//待发货
            if (!StringUtils.isEmptyOrNull(singleResult.getWay_bills())) {
                orderRepository.updateOrderBillWays(orderNo, singleResult.getWay_bills());//更新运单号，将运单号写进去;
            }
            if (DECLARE_SUCCESS.equals(singleResult.getChk_mark())) {
                repaymentPlanService.addRepaymentPlan(orderNo);
                smsService.sendDeliverySms(orderNo);
            }
            sinoResultRecordRepository.insertSinoResultRecord(new SinoResultRecordModel(singleResult.getBusiness_no(), singleResult.getChk_mark(),
                    singleResult.getNotice_time(), singleResult.getNotice_content(), singleResult.getBusiness_type(), singleResult.getWay_bills()));
        }

    }

    /**
     * 处理电子口岸返回给中外运的异步回执,中外运在返回给我们,目前是单个订单发送，所以返回的结果只包含一个订单的回执结果，循环次数为1
     */
    @Transactional
    public String handleAsyncResultFromSinoChina(SinoChinaResultModel result) {
        for (SinoChinaServiceResultModel singleResult : result.getService_result()) {
            String businessNo = singleResult.getBusiness_no();
            List<OrderSentHistoryModel> orderSentHistoryModels = orderSentHistoryRepository.getByBusinessNo(businessNo);
            if (orderSentHistoryModels != null && orderSentHistoryModels.size() > 0) {
                for (OrderSentHistoryModel sentHistoryModel : orderSentHistoryModels) {
                    logger.info(String.format("异步回执,更新订单编号:%s的状态", sentHistoryModel.getOrderNo()));
                    orderRepository.updateOrderStatus(sentHistoryModel.getOrderNo(), DECLARE_SUCCESS.equals(singleResult.getChk_mark()) ? DONE.getCode() : FAIL.getCode());
                }
            }
            int insertResult = sinoResultRecordRepository.insertSinoResultRecord(new SinoResultRecordModel(singleResult.getBusiness_no(), singleResult.getChk_mark(),
                    singleResult.getNotice_time(), singleResult.getNotice_content(), singleResult.getBusiness_type(), singleResult.getWay_bills()));
            if (insertResult > 0) {
                return "success";
            }
        }
        return "fail";
    }

    /**
     * 根据物流的订单号获取物流轨迹
     *
     * @param logisBillNo 物流快递单号
     * @return 返回的物流轨迹信息Gson对象
     */
    public SinoChinaLogisBillInfoGson getLogisInfoByBillNoFromSinoChina(String logisBillNo) {
        try {
            return sinoChinaProxy.invokeLogisInfoInterfaceFromSinoChina(logisBillNo);
        } catch (Exception e) {
            logger.error(String.format("查询物流单号:%s的轨迹信息异常:%s", logisBillNo, e.getMessage()), e);
            return null;
        }
    }

    @Override
    protected void declareOrderToCustom(OrderModel order) {
        try {
            if (isOrderFitForDeclare(order) && isAllFromHaiWaiGou(order)) {
                //订单推送给海外购
                sendOrderToHaiWaiGou(order);
            } else {
                logger.warn(String.format("订单号:%s不满足推送的条件", order.getOrdersn()));
            }
        } catch (Exception e) {
            logger.error(String.format("报送订单号:%s数据异常:%s", order.getOrdersn(), e.getMessage()), e);
        }
    }

    /**
     * 发送订单给海外购
     *
     * @param order 订单
     */
    private void sendOrderToHaiWaiGou(OrderModel order) {
        try {
            String businessNo = BusinessNoGenerator.generateSinoBusinessNo(order.getOrdersn(), properties.getAppId());
            Map map = MapUtils.convertToMap("app_id", properties.getAppId(), "auth_token", getTokenFromSinoChina(properties.getAppId(), properties.getAuthCode()), "custom_code", "2991",
                    "function", properties.getSendOrderFunction(), "business_no", businessNo,
                    "type", "0", "data", new Gson().toJson(new SinoChinaOrderRequestDataGson(sinoChinaOderSyncDataConstructorService.constructOrderHeader(order),
                            sinoChinaOderSyncDataConstructorService.constructOrderDetailList(order), sinoChinaOderSyncDataConstructorService.constructGoodsPurchaser(order))));
            logger.info(String.format("发送到海外购的订单信息是:%s", new Gson().toJson(map)));
            SinoChinaResultModel result = sinoChinaProxy.declareOrderToHaiWaiGou(map, SinoChinaResultModel.class);
            orderSentHistoryRepository.insertOrderSentHistory(new OrderSentHistoryModel(businessNo, order.getOrdersn()));
            businessNoRepository.insertBusinessNo(new BusinessNoHistoryModel(businessNo));
            logger.info(String.format("针对中外运返回的结果做处理，更新系统内部订单号:%s的状态", order.getOrdersn()));
            handleSyncResultFromSinoChina(result, order.getOrdersn());
        } catch (Exception e) {
            logger.error(String.format("海外购报关订单号:%s异常:%s", order.getOrdersn(), e.getMessage()), e);
        }
    }
}
