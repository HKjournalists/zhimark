package com.spring.mvc.resource;

import com.google.gson.Gson;
import com.spring.mvc.service.SmsService;
import com.spring.mvc.utils.Logger;
import com.spring.mvc.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static com.spring.mvc.helper.ConstantsHelper.DEFAULT_SMS_LEVEL;
import static com.spring.mvc.helper.ConstantsHelper.DEFAULT_SMS_REPORT_FLAG;
import static com.spring.mvc.helper.ConstantsHelper.DEFAULT_SMS_TYPE;

/**
 * Created by liluoqi on 15/7/7.
 */
@Component
@Path("sms")
public class SmsResource {
    @Autowired
    private SmsService smsService;
    
    private Logger logger = Logger.getLogger(SmsResource.class);

    @POST
    @Path("register")
    @Produces("application/json")
    public String register(@FormParam("enterpriseName") String enterpriseName, @FormParam("enterpriseShortName") String enterpriseShortName,
                           @FormParam("address") String address, @FormParam("telephoneNo") String telephoneNo,
                           @FormParam("contactName") String contactName, @FormParam("email") String email, @FormParam("fax") String fax,
                           @FormParam("postCode") String postCode, @FormParam("mobileNo") String mobileNo) {
        logger.info(String.format("注册短信使用:enterpriseName:%s," +
                        "enterpriseShortName:%s,address:%s,telephoneNo:%s", enterpriseName, enterpriseShortName,
                address, telephoneNo));
        logger.info(String.format("和使用:contactName:%s,email:%s,fax:%s," +
                "postCode:%s,mobileNo:%s", contactName, email, fax, postCode, mobileNo));
        return new Gson().toJson(smsService.register(enterpriseName,
                enterpriseShortName, address, telephoneNo, contactName, email, fax, postCode, mobileNo));
    }

    @POST
    @Path("send-validation")
    @Produces("application/json")
    public String sendValidationSms(@FormParam("mobileNo") String mobileNo) {
        logger.info(String.format("给手机号码:%s发送验证码短信", mobileNo));
        return new Gson().toJson(smsService.sendValidationSms(mobileNo));
    }

    @POST
    @Path("send-delivery")
    @Produces("application/json")
    public String sendDeliverySms(@FormParam("orderNo") String orderNo) {
        logger.info(String.format("给订单号:%s发送发货通知", orderNo));
        return new Gson().toJson(smsService.sendDeliverySms(orderNo));
    }

    @POST
    @Path("send-passwordReset")
    @Produces("application/json")
    public String sendPasswordResetSms(@FormParam("mobileNo") String mobileNo) {
        logger.info(String.format("给手机号码:%s发送验证码短信", mobileNo));
        return new Gson().toJson(smsService.sendPasswordResetSms(mobileNo));
    }

    @POST
    @Path("validate-code")
    @Produces("application/json")
    public String validateSmsCode(@FormParam("mobileNo") String mobileNo, @FormParam("smsCode") String smsCode) {
        logger.info(String.format("收到手机用户:%s的短信验证码:%s的验证请求", mobileNo, smsCode));
        return new Gson().toJson(smsService.validateSmsCode(mobileNo, smsCode));
    }
}
