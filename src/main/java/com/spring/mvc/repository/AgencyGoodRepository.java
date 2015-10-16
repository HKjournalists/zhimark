package com.spring.mvc.repository;

import com.spring.mvc.model.AgencyGoodModel;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

/**
 * Created by liluoqi on 15/5/29.
 */
@Repository
public class AgencyGoodRepository extends BaseRepository<AgencyGoodModel> {
    private static final String GET_GOOD_INFO_BY_AGENCY_ID = "getGoodInfoByAgencyIdAndGoodId";
    private static final String GET_BY_ID = "getAgencyGoodById";

    public AgencyGoodModel getGoodInfoByAgencyAndGoodId(int agencyId, int goodId) {
        return querySingle(GET_GOOD_INFO_BY_AGENCY_ID, MapUtils.convertToMap("mid", agencyId, "gid", goodId));
    }

    public AgencyGoodModel getById(int id) {
        return querySingleById(GET_BY_ID, id);
    }
}
