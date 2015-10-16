package com.spring.mvc.repository;

import com.spring.mvc.model.AgencyGoodPriceModel;
import org.springframework.stereotype.Repository;

/**
 * Created by liluoqi on 15/6/2.
 */
@Repository
public class AgencyGoodPriceRepository extends BaseRepository<AgencyGoodPriceModel>{
    private static final String GET_AGENCY_GOOD_PRICE_BY_ID="getAgencyGoodPriceModelById";

    public AgencyGoodPriceModel getAgencyGoodPriceModelById(int id){
        return querySingleById(GET_AGENCY_GOOD_PRICE_BY_ID,id);
    }
}
