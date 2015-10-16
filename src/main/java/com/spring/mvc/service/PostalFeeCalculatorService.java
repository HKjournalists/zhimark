package com.spring.mvc.service;

import com.spring.mvc.component.Properties;
import com.spring.mvc.model.*;
import com.spring.mvc.repository.GoodRepository;
import com.spring.mvc.repository.GroupGoodRepository;
import com.spring.mvc.repository.TaxCodeRepository;
import com.spring.mvc.utils.WeightUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.spring.mvc.utils.MathUtils.divide;
import static com.spring.mvc.utils.MathUtils.multiple;
import static java.lang.Math.max;

/**
 * Created by liluoqi on 15/6/16.
 */
@Service
public class PostalFeeCalculatorService {

    @Autowired
    private Properties properties;
    @Autowired
    private TaxCodeRepository taxCodeRepository;
    @Autowired
    private GroupGoodRepository groupGoodRepository;
    @Autowired
    private GoodRepository goodRepository;

    public double calculatePostFee(AgencyGoodPriceModel agencyGoodPrice) {
        GroupGoodModel groupGood = groupGoodRepository.getGroupGoodById(Integer.valueOf(agencyGoodPrice.getFz()));
        GoodModel good = goodRepository.getGoodByGoodId(Integer.valueOf(groupGood.getGid()));
        double singleRetailPrice = divide(Double.valueOf(agencyGoodPrice.getLsj()), Integer.valueOf(groupGood.getN()));
        double singleRetailPricePerKilo = divide(singleRetailPrice, WeightUtils.formatGramToKiloGramDouble(good.getWeight()));
        TaxCodeModel taxCode = taxCodeRepository.getTaxCodeById(Integer.valueOf(good.getShuilv()));
        return multiple(multiple(max(singleRetailPricePerKilo, Double.valueOf(properties.getPostalFeePerKilo())), WeightUtils.formatGramToKiloGramDouble(good.getWeight())), multiple(taxCode.getTaxRate(), Integer.valueOf(groupGood.getN())));
    }

    public double getRetailPriceFromAgencyGoodPrice(AgencyGoodPriceModel agencyGoodPrice) {
        return Double.valueOf(agencyGoodPrice.getLsj());
    }


    public double getUnitRetailPriceFromAgencyGoodPrice(AgencyGoodPriceModel agencyGoodPrice) {
        GroupGoodModel groupGood = groupGoodRepository.getGroupGoodById(Integer.valueOf(agencyGoodPrice.getFz()));
        return divide(agencyGoodPrice.getLsj(), groupGood.getN());
    }

    public boolean isSingleProduct(AgencyGoodPriceModel agencyGoodPrice) {
        return "1".equals(groupGoodRepository.getGroupGoodById(Integer.valueOf(agencyGoodPrice.getFz())).getN());
    }
}
