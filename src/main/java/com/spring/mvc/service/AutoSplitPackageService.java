package com.spring.mvc.service;

import com.spring.mvc.model.AgencyGoodPriceModel;
import com.spring.mvc.model.AgencyGoodRetailAndPostFeeRelation;
import com.spring.mvc.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.spring.mvc.utils.MathUtils.greater;
import static com.spring.mvc.utils.MathUtils.greaterOrEqual;
import static com.spring.mvc.utils.MathUtils.lower;

/**
 * 自动拆单
 * Created by liluoqi on 15/6/16.
 */
@Service
public class AutoSplitPackageService {
    private static final double SINGLE_UPPER_PRICE = 1000;//可拆分商品的上限价格
    private static final double POSTAL_FEE_UPPER = 50;

    @Autowired
    private PostalFeeCalculatorService postalFeeCalculatorService;

    public List<List<AgencyGoodPriceModel>> autoSplitPackageFromAgencyGoods(List<AgencyGoodPriceModel> agencyGoodPriceList) {
        //construct a map which contains a map,the key of the inner map stands for retail price and the value of the inner map stands for the postal fee
        List<AgencyGoodRetailAndPostFeeRelation> agencyGoodRetailAndPostFeeRelations = new ArrayList<AgencyGoodRetailAndPostFeeRelation>();
        for (AgencyGoodPriceModel agencyGoodPrice : agencyGoodPriceList) {
            agencyGoodRetailAndPostFeeRelations.add(new AgencyGoodRetailAndPostFeeRelation(agencyGoodPrice,
                    postalFeeCalculatorService.getRetailPriceFromAgencyGoodPrice(agencyGoodPrice),
                    postalFeeCalculatorService.calculatePostFee(agencyGoodPrice)));
        }
        List<List<AgencyGoodPriceModel>> splitPackageList = new ArrayList<List<AgencyGoodPriceModel>>();
        Collections.sort(agencyGoodRetailAndPostFeeRelations);
        List<List<AgencyGoodPriceModel>> splitOrdersIntoPackages = new ArrayList<List<AgencyGoodPriceModel>>();
        handleOrdersToSplit(agencyGoodRetailAndPostFeeRelations, splitOrdersIntoPackages);
        return splitPackageList;
    }

    private void handleOrdersToSplit(List<AgencyGoodRetailAndPostFeeRelation> agencyGoodRetailAndPostFeeRelations,
                                     List<List<AgencyGoodPriceModel>> splitOrdersIntoPackages) {
        List<AgencyGoodPriceModel> list = new ArrayList<AgencyGoodPriceModel>();
        double retailPriceSum = 0;
        double postalFeeSum = 0;
        //the end condition and point of recursive call
        if (agencyGoodRetailAndPostFeeRelations.size() == 0) return;
        for (AgencyGoodRetailAndPostFeeRelation agencyGoodRetailAndPostFeeRelation : agencyGoodRetailAndPostFeeRelations) {
            if (greaterOrEqual(agencyGoodRetailAndPostFeeRelation.getRetailPrice(), SINGLE_UPPER_PRICE)) {
                list.add(agencyGoodRetailAndPostFeeRelation.getAgencyGoodPriceModel());
                agencyGoodRetailAndPostFeeRelations.remove(agencyGoodRetailAndPostFeeRelation);
                continue;
            }
            if (greaterOrEqual(agencyGoodRetailAndPostFeeRelation.getPostalFee(), POSTAL_FEE_UPPER)) {
                list.add(agencyGoodRetailAndPostFeeRelation.getAgencyGoodPriceModel());
                agencyGoodRetailAndPostFeeRelations.remove(agencyGoodRetailAndPostFeeRelation);
                continue;
            }
            retailPriceSum += agencyGoodRetailAndPostFeeRelation.getRetailPrice();
            postalFeeSum += agencyGoodRetailAndPostFeeRelation.getPostalFee();
            if (lower(retailPriceSum, SINGLE_UPPER_PRICE) && lower(postalFeeSum, POSTAL_FEE_UPPER)) {
                list.add(agencyGoodRetailAndPostFeeRelation.getAgencyGoodPriceModel());
                agencyGoodRetailAndPostFeeRelations.remove(agencyGoodRetailAndPostFeeRelation);
            }
        }
        splitOrdersIntoPackages.add(list);
        handleOrdersToSplit(agencyGoodRetailAndPostFeeRelations, splitOrdersIntoPackages);
    }

}
