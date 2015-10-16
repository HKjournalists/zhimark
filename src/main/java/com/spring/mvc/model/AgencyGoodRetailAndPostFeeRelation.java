package com.spring.mvc.model;

import com.spring.mvc.utils.MathUtils;

import static com.spring.mvc.utils.MathUtils.greater;
import static com.spring.mvc.utils.MathUtils.lower;

/**
 * Created by liluoqi on 15/6/17.
 */
public class AgencyGoodRetailAndPostFeeRelation implements Comparable<AgencyGoodRetailAndPostFeeRelation> {
    private AgencyGoodPriceModel agencyGoodPriceModel;
    private double retailPrice;
    private double postalFee;

    public AgencyGoodRetailAndPostFeeRelation() {
    }

    public AgencyGoodRetailAndPostFeeRelation(AgencyGoodPriceModel agencyGoodPrice,
                                              double retailPrice, double postalFee) {
        this.agencyGoodPriceModel = agencyGoodPrice;
        this.retailPrice = retailPrice;
        this.postalFee = postalFee;
    }

    public AgencyGoodPriceModel getAgencyGoodPriceModel() {
        return agencyGoodPriceModel;
    }

    public void setAgencyGoodPriceModel(AgencyGoodPriceModel agencyGoodPriceModel) {
        this.agencyGoodPriceModel = agencyGoodPriceModel;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getPostalFee() {
        return postalFee;
    }

    public void setPostalFee(double postalFee) {
        this.postalFee = postalFee;
    }

    @Override
    public int compareTo(AgencyGoodRetailAndPostFeeRelation other) {
        if (lower(this.getRetailPrice(), other.getRetailPrice())) {
            return -1;
        }
        if (greater(this.getRetailPrice(), other.getRetailPrice())) {
            return 1;
        }
        return 0;
    }
}
