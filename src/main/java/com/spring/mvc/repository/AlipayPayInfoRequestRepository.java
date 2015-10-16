package com.spring.mvc.repository;

import com.spring.mvc.model.serviceResult.alipay.AlipayPayInfoRequestModel;
import org.springframework.stereotype.Repository;

/**
 * Created by liluoqi on 15/6/12.
 */
@Repository
public class AlipayPayInfoRequestRepository extends BaseRepository<AlipayPayInfoRequestModel> {
    private static final String INSERT_ALIPAY_PUSH_REQUEST="insertAlipayPushRequest";

    public int insertAlipayPushRequest(AlipayPayInfoRequestModel alipayPayInfoRequestModel){
        return insert(INSERT_ALIPAY_PUSH_REQUEST, alipayPayInfoRequestModel);
    }
}
