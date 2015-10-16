package com.spring.mvc.service.base;

import com.spring.mvc.component.Properties;
import com.spring.mvc.model.*;
import com.spring.mvc.repository.*;
import com.spring.mvc.utils.HttpClientUtils;
import com.spring.mvc.utils.Logger;
import com.spring.mvc.utils.MapUtils;
import com.spring.mvc.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by liluoqi on 15/9/5.
 */
public abstract class DeclareCustomService {

    private static Logger logger = Logger.getLogger(DeclareCustomService.class);

    @Autowired
    protected OrderSentHistoryRepository orderSentHistoryRepository;
    @Autowired
    protected GoodRepository goodRepository;
    @Autowired
    protected OrderGoodRepository orderGoodRepository;
    @Autowired
    protected Properties properties;
    @Autowired
    protected InventoryRepository inventoryRepository;

    private static final String HAI_WAI_GOU_SOURCE = "1";

    /**
     * 从中外运获取token
     *
     * @param appId    海外购的程序ID
     * @param authCode 海外购的认证码
     * @return token as String
     */
    public String getTokenFromSinoChina(String appId, String authCode) {
        try {
            logger.info(String.format("try to get token from sino china interface : %s using appId:%s,auth_code:%s",
                    properties.getTokenFromSinoChinaUrl(), appId, authCode));
            Map map;
            if (StringUtils.isEmptyOrNull(appId) || StringUtils.isEmptyOrNull(authCode)) {
                map = MapUtils.convertToMap("app_id", properties.getAppId(), "auth_code", properties.getAuthCode());
            } else {
                map = MapUtils.convertToMap("app_id", appId, "auth_code", authCode);
            }
            return HttpClientUtils.postForm(properties.getTokenFromSinoChinaUrl(), map, String.class);
        } catch (Exception e) {
            logger.error("exception happens when get token from sino china", e);
            return StringUtils.emptyString();
        }
    }

    protected boolean isOrderFitForDeclare(OrderModel order) {
        List<OrderSentHistoryModel> orderSentHistoryModels = orderSentHistoryRepository.getByOrderNo(order.getOrdersn());
        if (orderSentHistoryModels != null && orderSentHistoryModels.size() > 0) {
            logger.warn(String.format("订单号:%s已经发送给海外购了，不能重复发送", order.getOrdersn()));
            return false;
        }
        if (StringUtils.isEmptyOrNull(order.getJyh())) {
            logger.error(String.format("订单号%s没有支付交易号，取消推送", order.getOrdersn()));
            return false;
        }
        return true;
    }

    protected boolean isAllFromHaiWaiGou(OrderModel order) {
        List<OrderGoodModel> orderGoods = orderGoodRepository.getOrderGoodsByOrderId(order.getId());
        for (OrderGoodModel orderGood : orderGoods) {
            GoodModel good = goodRepository.getGoodByGoodId(orderGood.getGoodsid());
            List<InventoryModel> inventories = inventoryRepository.getInventoryByProductId(good.getId());
            for (InventoryModel inventory : inventories) {
                if (!HAI_WAI_GOU_SOURCE.equals(inventory.getSource())) {
                    return false;
                }
            }
        }
        return true;
    }

    protected abstract void declareOrderToCustom(OrderModel order);
}
