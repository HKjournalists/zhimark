package com.spring.mvc.repository;

import com.spring.mvc.model.SmsTemplateModel;
import com.spring.mvc.utils.MapUtils;
import org.springframework.stereotype.Repository;

/**
 * Created by liluoqi on 15/7/9.
 */
@Repository
public class SmsTemplateRepository extends BaseRepository<SmsTemplateModel> {

    private static final String GET_SMS_TEMPLATE_BY_TYPE = "getSmsTemplateByType";

    public SmsTemplateModel getSmsTemplateByType(String smsType) {
        return querySingle(GET_SMS_TEMPLATE_BY_TYPE, MapUtils.convertToMap("smsType", smsType));
    }
}
