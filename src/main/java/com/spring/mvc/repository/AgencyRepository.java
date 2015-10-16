package com.spring.mvc.repository;

import com.spring.mvc.model.AgencyModel;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by liluoqi on 15/5/29.
 */
@Repository
public class AgencyRepository extends BaseRepository<AgencyModel> {

    private static final String GET_BY_AGENCY_id = "getByAgencyId";
    private static final String GET_HEAD_CORP_VIP = "getHeadCorpVipAgency";

    public AgencyModel getByAgencyId(int id) {
        return querySingleById(GET_BY_AGENCY_id, id);
    }

    public AgencyModel getHeadCorpVip(String vip) {
        return querySingle(GET_HEAD_CORP_VIP, MapUtils.convertToMap("vip", vip));
    }
}
