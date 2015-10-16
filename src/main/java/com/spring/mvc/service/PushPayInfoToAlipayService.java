package com.spring.mvc.service;

import com.spring.mvc.component.Properties;
import com.spring.mvc.model.OrderModel;
import com.spring.mvc.model.serviceResult.alipay.AlipayPayInfoRequestModel;
import com.spring.mvc.model.serviceResult.alipay.AlipayPayInfoResultModel;
import com.spring.mvc.repository.AlipayPayInfoRequestRepository;
import com.spring.mvc.repository.AlipayPayInfoResultRepository;
import com.spring.mvc.repository.OrderRepository;
import com.spring.mvc.utils.*;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by liluoqi on 15/6/8.
 */
@Service
public class PushPayInfoToAlipayService {

    @Autowired
    private Properties properties;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AlipayPayInfoRequestRepository alipayPayInfoRequestRepository;
    @Autowired
    private AlipayPayInfoResultRepository alipayPayInfoResultRepository;

    private static final String PUSH_PAY_INFO_SERVICE_NAME = "alipay.acquire.customs";
    private static final String CHARSET = "utf-8";
    private static final String SIGN_TYPE = "MD5";
    private static final String HANG_ZHOU_CUSTOMS_PLACE_CODE = "HANGZHOU";
    private static final String SUCCESS = "T";
    private static final String FAIL = "F";
    private static final String SUCCESS_CODE = "SUCCESS";

    private Logger logger = Logger.getLogger(PushPayInfoToAlipayService.class);

    public boolean pushPayInfoAlipay(String orderId, String orderNo) {
        try {
            if (StringUtils.isEmptyOrNull(orderNo) && StringUtils.isEmptyOrNull(orderId)) {
                logger.warn("订单ID和订单号不能同时为空");
                return false;
            }
            OrderModel order = StringUtils.isEmptyOrNull(orderNo) ? orderRepository.getById(Integer.valueOf(orderId)) : orderRepository.getByOrderNo(orderNo);
            if (order == null) {
                logger.warn(String.format("根据订单ID：%s找不到对应的订单信息", orderId));
                return false;
            }
            AlipayPayInfoResultModel alipayPayInfoResult = alipayPayInfoResultRepository.getSuccessAlipayPushResult(order.getOrdersn());
            if (alipayPayInfoResult != null) {
                logger.warn(String.format("该笔订单号:%s的支付信息已经被支付宝成功报关过", order.getOrdersn()));
                return true;
            }
            Map<String, String> params = buildRequestParams(order);
            CloseableHttpResponse response = HttpClientUtils.post(properties.getAlipayGateway(), params);
            if (response == null) {
                logger.error("请求支付宝的报关接口返回结果为空null");
                return false;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null && 200 == (response.getStatusLine().getStatusCode())) {
                String result = IOUtils.toString(entity.getContent(), "GBK");
                logger.info(String.format("通过支付宝的报关接口给将订单的支付信息发给海关返回结果是:%s", result));
                return handleResultFromAlipay(result);
            } else {
                logger.error(String.format("请求支付宝的报关接口失败,返回状态为:%s",
                        response.getStatusLine().getStatusCode()));
            }
        } catch (Exception e) {
            logger.error(String.format("通过支付宝的报关接口发送订单ID:%s的支付信息给海关异常信息:%s", orderId, e.getMessage()), e);
        }
        return false;
    }

    private boolean handleResultFromAlipay(String xmlString) {
        Document document;
        boolean isSuccess;
        try {
            document = DocumentHelper.parseText(xmlString);
            Element rootElement = document.getRootElement();
            System.out.println(String.format("根节点是:%s", rootElement.getName()));
            Element firstChildElement = rootElement.element("is_success");
            logger.info(String.format("获取的is_success子节点的值是:%s", firstChildElement.getText()));
            //返回结果为成功报文
            if (SUCCESS.equals(firstChildElement.getText())) {
                Element responseAlipayElement = rootElement.element("response").element("alipay");

                Element resultCodeElement = responseAlipayElement.element("result_code");
                logger.info(String.format("返回的结果码是:%s", resultCodeElement.getText()));
                if (SUCCESS_CODE.equals(resultCodeElement.getText())) {
                    logger.info("支付宝报关成功");
                    isSuccess = true;
                    Element tradeNoElement = responseAlipayElement.element("trade_no");
                    logger.info(String.format("获取到支付宝的支付交易号是%s", tradeNoElement.getText()));
                    Element declareNoElement = responseAlipayElement.element("alipay_declare_no");
                    logger.info(String.format("支付宝报关流水号:%s", declareNoElement.getText()));
                } else {
                    logger.warn("支付宝报关失败");
                    isSuccess = false;
                    Element detailErrorCodeElement = responseAlipayElement.element("detail_error_code");
                    logger.warn(String.format("支付宝报关失败代码:%s", detailErrorCodeElement.getText()));
                    Element detailErrorDescriptionElement = responseAlipayElement.element("detail_error_des");
                    logger.warn(String.format("支付宝报关失败的原因是:%s", detailErrorDescriptionElement.getText()));
                }
            } else {
                logger.warn("支付宝报关失败");
                isSuccess = false;
                //返回结果为F，为失败的
                Element errorElement = rootElement.element("error");
                logger.info(String.format("发送电子报关请求给支付宝失败的原因是:%s", errorElement.getText()));
            }
            alipayPayInfoResultRepository.insertAlipayPushResult(new AlipayPayInfoResultModel());
            logger.info("");
        } catch (DocumentException e) {
            isSuccess = false;
            logger.error(String.format("处理返回的支付宝报关接口xml文档异常message：%s", e.getMessage()), e);
        } catch (Exception e) {
            isSuccess = false;
            logger.error(String.format("处理返回的支付宝报关接口回执信息异常message：%s", e.getMessage()), e);
        }
        return isSuccess;
    }

    private Map<String, String> buildRequestParams(OrderModel order) {
        Map<String, String> params = MapUtils.convertStringToMap("service", PUSH_PAY_INFO_SERVICE_NAME,
                "partner", properties.getAlipayPartnerId(),
                "out_request_no", BusinessNoGenerator.generateAlipayRequestNo(),
                "trade_no", order.getJyh(),
                "merchant_customs_code", properties.getCompanyCode(),
                "amount", String.valueOf(order.getPrice()),
                "customs_place", HANG_ZHOU_CUSTOMS_PLACE_CODE,"_input_charset",CHARSET);
        params.put("sign", sign(buildSignString(params)));
        params.put("sign_type", SIGN_TYPE);
        alipayPayInfoRequestRepository.insertAlipayPushRequest(new AlipayPayInfoRequestModel(order.getOrdersn(), order.getJyh(),
                params.get("out_request_no"), Double.valueOf(params.get("amount")), params.get("customs_place")));
        return params;
    }

    private String sign(String stringToSign) {
        return MD5Utils.string2MD5(stringToSign,"utf-8");
    }

    private String buildSignString(Map<String, String> params) {
        List<String> stringForSortList = new ArrayList<String>();
        for (String key : params.keySet()) {
            stringForSortList.add(String.format("%s=%s", key, params.get(key)));
        }
        String[] paramSortArray = stringForSortList.toArray(new String[stringForSortList.size()]);
        Arrays.sort(paramSortArray);
        StringBuilder stringToSign = new StringBuilder();
        int index = 0;
        for (String keyAndValue : paramSortArray) {
            if (index == paramSortArray.length - 1) {
                stringToSign.append(keyAndValue);
            } else {
                stringToSign.append(String.format("%s&", keyAndValue));
            }
            index++;
        }
        String stringForMD5Sign = String.format("%s%s", stringToSign.toString(), properties.getAlipayPartnerKey());
        logger.info(String.format("完整的待加密的字符串是:%s", stringForMD5Sign));
        return stringForMD5Sign;
    }
}
