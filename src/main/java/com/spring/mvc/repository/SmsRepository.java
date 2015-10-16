package com.spring.mvc.repository;

import com.spring.mvc.model.SmsModel;
import com.spring.mvc.utils.DateUtils;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by liluoqi on 15/7/9.
 */
@Repository
public class SmsRepository extends BaseRepository<SmsModel> {
    private static final String INSERT_SMS = "insertSms";
    private static final String GET_LAST_SMS_BY_MOBILE_NO = "getLastSmsByMobileNo";
    private static final String COUNT_TOTAL_SMS_TODAY_BY_MOBILE_AND_TYPE = "countTotalSmsTodayByMobileAndType";

    public int insertSms(SmsModel sms) {
        return insert(INSERT_SMS, sms);
    }

    public SmsModel getLastSmsByMobileNo(String mobileNo) {
        return querySingle(GET_LAST_SMS_BY_MOBILE_NO, MapUtils.convertToMap("mobileNo", mobileNo));
    }

    public long countTotalSmsTodayByMobileAndType(String mobileNo, String smsType) {
        return count(COUNT_TOTAL_SMS_TODAY_BY_MOBILE_AND_TYPE,
                MapUtils.convertToMap("mobileNo", mobileNo, "smsType", smsType, "startOfToday",
                        DateUtils.startOfDate(new Date()), "endOfToday", DateUtils.endOfDate(new Date())));
    }
}
