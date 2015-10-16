package com.spring.mvc.repository;

import com.spring.mvc.model.BusinessNoHistoryModel;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

/**
 * Created by liluoqi on 15/5/14.
 */
@Repository
public class BusinessNoRepository extends BaseRepository<BusinessNoHistoryModel> {
    private static final String INSERT_BUSINESS_NO = "insertBusinessNo";
    private static final String GET_BY_BUSINESS_NO = "getByBusinessNo";

    public int insertBusinessNo(BusinessNoHistoryModel businessNoHistoryModel) {
        return insert(INSERT_BUSINESS_NO, businessNoHistoryModel);
    }

    public BusinessNoHistoryModel getByBusinessNo(String businessNo) {
        return querySingle(GET_BY_BUSINESS_NO, MapUtils.convertToMap("businessNo", businessNo));
    }
}
