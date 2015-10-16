package com.spring.mvc.service;

import com.spring.mvc.cache.FisherAccessTokenCache;
import com.spring.mvc.model.OrderModel;
import com.spring.mvc.model.ThreePartyProduct.FisherProductModel;
import com.spring.mvc.model.gson.fisher.FisherAccessTokenGson;
import com.spring.mvc.model.gson.fisher.FisherProductInfoGson;
import com.spring.mvc.model.gson.fisher.FisherProductInfosGson;
import com.spring.mvc.proxy.FisherProxy.FisherServiceInvoker;
import com.spring.mvc.repository.FisherProductRepository;
import com.spring.mvc.service.base.DeclareCustomService;
import com.spring.mvc.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liluoqi on 15/9/10.
 */
@Service
public class FisherService extends DeclareCustomService {

    @Autowired
    private FisherProductRepository fisherProductRepository;

    private static final String HAS_NEXT = "1";
    private static final String REDIRECT_URL = "";
    private static final int GET_TOKEN_RETRY_TIMES = 3;
    private Logger logger = Logger.getLogger(FisherService.class);

    @Override
    protected void declareOrderToCustom(OrderModel order) {
        if (!isAllFromHaiWaiGou(order) && isOrderFitForDeclare(order)) {
            sendOrderToFisher(order);
        }
    }

    private void sendOrderToFisher(OrderModel orderModel) {

    }

    /**
     * 调用费舍尔需要access token的接口时统一由此获取，这里是获取和管理的token的唯一入口
     * 但是仍然有可能返回null的token，如果返回的是null的token则需要放弃后续的操作
     *
     * @return fisher token
     */
    public String getFisherAccessToken() {
        try {
            logger.info("尝试获取fisher的token");
            String validAccessTokenFromCache = FisherAccessTokenCache.getValidAccessToken();
            if (validAccessTokenFromCache != null) {
                return validAccessTokenFromCache;
            }
            FisherAccessTokenGson fisherAccessTokenGson = getFisherAccessTokenFromFisherRemote();
            if (fisherAccessTokenGson != null) {
                FisherAccessTokenCache.updateAccessToken(fisherAccessTokenGson.getToken(), fisherAccessTokenGson.getExpire());
                return fisherAccessTokenGson.getToken();
            }
        } catch (Exception e) {
            logger.error(String.format("获取fisher的token异常:%s", e.getMessage()));
        }
        return null;
    }

    @Transactional
    @Deprecated
    /**
     * no access authority temporarily so that this interface is useless for now
     */
    public void getOrUpdateFisherProductInfos() {
        try {
            logger.info("从费舍尔请求获取可圈商品列表");
            List<FisherProductModel> fisherProductsFromDatabase = fisherProductRepository.getAllFisherProducts();
            List<Integer> productIdsFromDataBase = new ArrayList<Integer>();
            for (FisherProductModel fisherProduct : fisherProductsFromDatabase) {
                productIdsFromDataBase.add(fisherProduct.getProductId());
            }
            getOrUpdateFisherProductInfos(1, productIdsFromDataBase);
        } catch (Exception e) {
            logger.error(String.format("从费舍尔获取商品列表异常:%s", e.getMessage()));
        }
    }


    /**
     * 如果远程获取fisher token次数超过三次仍是空则不在获取
     *
     * @return 返回fisher token
     */
    private FisherAccessTokenGson getFisherAccessTokenFromFisherRemote() {
        int triedTimes = 0;
        FisherAccessTokenGson fisherAccessTokenGson = null;
        fisherAccessTokenGson = FisherServiceInvoker.getAccessTokenUsingLoginInterface(properties.geteBaoAppKey(), properties.geteBaoAppSecret(), properties.geteBaoMemberCode(),
                properties.geteBaoMemberPassword(), properties.geteBaoBusinessUrl());
        while (fisherAccessTokenGson == null && triedTimes < GET_TOKEN_RETRY_TIMES) {
            logger.info(String.format("尝试第:%s次获取fisher的token", triedTimes));
            fisherAccessTokenGson = FisherServiceInvoker.getAccessTokenUsingLoginInterface(properties.geteBaoAppKey(), properties.geteBaoAppSecret(), properties.geteBaoMemberCode(),
                    properties.geteBaoMemberPassword(), properties.geteBaoBusinessUrl());
            triedTimes++;
            if (fisherAccessTokenGson != null) break;
        }
        return fisherAccessTokenGson;
    }

    private void getOrUpdateFisherProductInfos(int requestPage, List<Integer> productIdsFromDataBase) {
        String fisherAccessToken = getFisherAccessToken();
        if (fisherAccessToken == null) {
            logger.error("获取fisher的access token为null,后续获取商品列表的操作不再进行");
            return;
        }
        FisherProductInfosGson fisherProductInfosGson = FisherServiceInvoker.getProductInfosWithPagination(properties.geteBaoAppKey(),
                properties.geteBaoAppSecret(), properties.geteBaoBusinessUrl(), fisherAccessToken, requestPage);
        List<FisherProductModel> fisherProductModelList = new ArrayList<FisherProductModel>();
        if (fisherProductInfosGson != null) {
            List<FisherProductInfoGson> fisherProductInfoGsonList = fisherProductInfosGson.getPros();
            if (productIdsFromDataBase == null || productIdsFromDataBase.size() == 0) {
                fisherProductRepository.batchInsertFisherProduct(fisherProductModelList);
            } else {
                for (FisherProductInfoGson fisherProductInfoGson : fisherProductInfoGsonList) {
                    if (!productIdsFromDataBase.contains(Integer.valueOf(fisherProductInfoGson.getPro_id()))) {
                        fisherProductModelList.add(new FisherProductModel(fisherProductInfoGson));
                    }
                }
                fisherProductRepository.batchInsertFisherProduct(fisherProductModelList);
            }
            if (HAS_NEXT.equals(fisherProductInfosGson.getHas_next())) {
                int nextRequestPage = requestPage + 1;
                logger.info(String.format("当前页:%s不是最后一页,下页:%s还有商品列表可以获取", requestPage, nextRequestPage));
                getOrUpdateFisherProductInfos(nextRequestPage, productIdsFromDataBase);
            }
        } else {
            logger.warn(String.format("查询第:%s页费舍尔的可圈商品列表的时候返回结果为空", requestPage));
        }
    }
}
