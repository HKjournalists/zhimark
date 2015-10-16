package com.spring.mvc.service;

import com.google.gson.Gson;
import com.spring.mvc.component.Properties;
import com.spring.mvc.helper.ConstantsHelper;
import com.spring.mvc.model.*;
import com.spring.mvc.model.gson.AlipayAsyncNoticeResultGson;
import com.spring.mvc.model.gson.LcPayAsyncNoticeResultGson;
import com.spring.mvc.model.gson.PayResultWebNoticeGson;
import com.spring.mvc.repository.OrderRepository;
import com.spring.mvc.repository.PayChannelReturnRepository;
import com.spring.mvc.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static com.spring.mvc.model.PayStatus.TRADE_FAILED;
import static com.spring.mvc.model.PayStatus.TRADE_FINISHED;
import static com.spring.mvc.model.PayType.ALIPAY;
import static com.spring.mvc.model.PayType.LIAN_LIAN_PAY;
import static com.spring.mvc.utils.StringUtils.emptyString;

/**
 * Created by liluoqi on 15/7/3.
 */
@Service
public class PayService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PayChannelReturnRepository payChannelReturnRepository;
    @Autowired
    private Properties properties;

    private Logger logger = Logger.getLogger(PayService.class);

    public AlipayAsyncNoticeResultGson getAlipayInfoByApplicantId(String applicantId) {
        if (StringUtils.isEmptyOrNull(applicantId)) {
            return new AlipayAsyncNoticeResultGson();
        }
        return new AlipayAsyncNoticeResultGson();
    }

    public String lcPayNoticeHandler(InputStream inputStream) throws IOException {
        LcPayAsyncNoticeResultGson lcPayAsyncNoticeResultGson = getContentFromRequest(inputStream);
        if (checkSign(lcPayAsyncNoticeResultGson)) {
            logger.info("签名校验通过");
            lcPayHandler(lcPayAsyncNoticeResultGson);
        } else {
            logger.error("签名校验不通过");
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("ret_code", "0000");
        map.put("ret_msg", "Success");
        return new Gson().toJson(map);
    }

    private LcPayAsyncNoticeResultGson getContentFromRequest(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String stringContent = stringBuilder.toString();
        logger.info(String.format("连连支付返回的异步回执是:%s", stringContent));
        return new Gson().fromJson(stringContent, LcPayAsyncNoticeResultGson.class);
    }

    private void lcPayHandler(LcPayAsyncNoticeResultGson lcPayAsyncNoticeResultGson) {
        try {
            OrderModel order = orderRepository.getByOrderNo(lcPayAsyncNoticeResultGson.getMerchant_orderno());
            if (order != null & order.getStatus() == OrderStatus.REMAIN_PAY.getCode() && "0".equals(lcPayAsyncNoticeResultGson.getPay_status())) {
                if (!isOrderPaidSuccess(lcPayAsyncNoticeResultGson.getMerchant_orderno())) {
                    payChannelReturnRepository.insertPayChannelReturn(PayChannelReturnModel.createSuccessInstance(LIAN_LIAN_PAY, TRADE_FINISHED,
                            lcPayAsyncNoticeResultGson.getMerchant_orderno(), lcPayAsyncNoticeResultGson.getOid_billno()));
                }
                logger.info(String.format("调用商城的接口处理连连支付返回的回执信息,更新订单ID:%s,订单号:%s的状态为:%s,支付方式为:%s,更新支付交易号:%s", order.getId(), order.getOrdersn(),
                        OrderStatus.PAID.getCode(), LIAN_LIAN_PAY.getCode(), lcPayAsyncNoticeResultGson.getOid_billno()));
                orderRepository.updateOrder(MapUtils.convertToMap("payType", LIAN_LIAN_PAY.getCode(), "tradeNo", lcPayAsyncNoticeResultGson.getOid_billno(),
                        "orderNo", lcPayAsyncNoticeResultGson.getMerchant_orderno(), "status", OrderStatus.PAID.getCode(), "id", order.getId()));
            } else {
                payChannelReturnRepository.insertPayChannelReturn(PayChannelReturnModel.createFailInstance(LIAN_LIAN_PAY, PayStatus.TRADE_FAILED,
                        lcPayAsyncNoticeResultGson.getMerchant_orderno(), lcPayAsyncNoticeResultGson.getOid_billno(), lcPayAsyncNoticeResultGson.getPay_status()));
            }
        } catch (Exception e) {
            logger.error(String.format("处理连连支付异步回执信息失败:%s", e.getMessage()), e);
        }
    }

    private boolean checkSign(LcPayAsyncNoticeResultGson resultGson) {
        String originSignString = String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s", resultGson.getMerchant_id(), resultGson.getMerchant_orderno(), resultGson.getMerchant_trans_date(), resultGson.getOid_billno(),
                resultGson.getPay_status(), resultGson.getPay_create_date(), resultGson.getPay_finish_date(), resultGson.getDate_acct(), resultGson.getTrans_amt(), resultGson.getTrans_cur(),
                resultGson.getUser_name() == null ? emptyString() : resultGson.getUser_name(), resultGson.getTelephone() == null ? emptyString() : resultGson.getTelephone(), resultGson.getIdno() == null ? emptyString() : resultGson.getIdno(),
                properties.getLcPayMd5Key());
        return MD5Utils.string2MD5(originSignString).equals(resultGson.getSign());
    }

    public void alipayNoticeHandler(String orderNo, String tradeNo, String tradeStatus) {
        try {
            OrderModel order = orderRepository.getByOrderNo(orderNo);
            if (order != null && order.getStatus() == OrderStatus.REMAIN_PAY.getCode() && ConstantsHelper.ALIPAY_TRADE_FINISHED.equals(tradeStatus)) {
                if (!isOrderPaidSuccess(orderNo)) {
                    payChannelReturnRepository.insertPayChannelReturn(PayChannelReturnModel.createSuccessInstance(ALIPAY, TRADE_FINISHED,
                            orderNo, tradeNo));
                }
                logger.info(String.format("调用商城的接口处理支付宝返回的回执信息,更新订单ID:%s,订单号:%s的状态为:%s,支付方式为:%s,更新支付交易号:%s", order.getId(), order.getOrdersn(),
                        OrderStatus.PAID.getCode(), PayType.ALIPAY.getCode(), tradeNo));
                orderRepository.updateOrder(MapUtils.convertToMap("payType", PayType.ALIPAY.getCode(), "tradeNo", tradeNo,
                        "orderNo", orderNo, "status", OrderStatus.PAID.getCode(), "id", order.getId()));
            } else {
                payChannelReturnRepository.insertPayChannelReturn(PayChannelReturnModel.createFailInstance(ALIPAY, TRADE_FAILED,
                        orderNo, tradeNo, tradeStatus));
            }
        } catch (Exception e) {
            logger.error(String.format("处理支付宝异步回执信息失败:%s", e.getMessage()), e);
        }
    }

    public boolean isOrderPaidSuccess(String orderNo) {
        logger.info(String.format("根据订单号查询是否支付完成:%s", orderNo));
        OrderModel order = orderRepository.getByOrderNo(orderNo);
        if (order != null) {
//            return payChannelReturnRepository.getSuccessPayChannelReturnByOrderNoAndChannel(orderNo, PayType.getPayTypeFromPayTypeCode(order.getPaytype())) != null;
            return OrderStatus.PAID.getCode() == order.getStatus();
        }
        logger.error(String.format("根据订单号:%s找不到订单", orderNo));
        return false;
    }

    public PayResultWebNoticeGson getSuccessPayResult(String orderId, PayType payType) {
        try {
            logger.info(String.format("根据订单ID:%s和支付方式:%s查询是否已经支付", orderId, payType.name()));
            OrderModel order = orderRepository.getById(Integer.valueOf(orderId));
            if (order != null && isOrderPaidSuccess(order.getOrdersn())) {
                logger.info(String.format("订单号:%s已经按照支付方式:%s成功支付", order, payType.name()));
                PayChannelReturnModel payChannelReturn = payChannelReturnRepository.getSuccessPayChannelReturnByOrderNoAndChannel(order.getOrdersn(), payType);
                return new PayResultWebNoticeGson(true, null, DateUtils.formatDateToSeconds(payChannelReturn.getCreatedDate()));
            }
            return new PayResultWebNoticeGson(false, String.format("没有此订单ID:%s", orderId));
        } catch (Exception e) {
            logger.error(String.format("查询订单ID:%s支付结果异常:%s", orderId, e.getMessage()), e);
        }
        return new PayResultWebNoticeGson(false, "查询支付结果失败");
    }
}
