package com.spring.mvc.service;

import com.spring.mvc.component.Properties;
import com.spring.mvc.model.*;
import com.spring.mvc.model.gson.SmsReturnGson;
import com.spring.mvc.proxy.sms.SmsProxy;
import com.spring.mvc.repository.*;
import com.spring.mvc.utils.BusinessNoGenerator;
import com.spring.mvc.utils.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.spring.mvc.model.SmsType.DELIVERED_NOTIFICATION;
import static com.spring.mvc.model.SmsType.PASSWORD_RESET;
import static com.spring.mvc.model.SmsType.VALIDATION_CODE;
import static com.spring.mvc.proxy.sms.SmsRegisterReturn.SUCCESS;

/**
 * Created by liluoqi on 15/7/7.
 */
@Service
public class SmsService {

    @Autowired
    private SmsRepository smsRepository;
    @Autowired
    private SmsTemplateRepository smsTemplateRepository;
    @Autowired
    private Properties properties;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AddressRepository addressRepository;

    private Logger logger = Logger.getLogger(SmsService.class);

    private static boolean isRegistered = false;
    private static final String TRUE = "true";
    private static final String FAIL = "fail";

    public boolean register(String enterpriseName, String enterpriseShortName, String address, String telephoneNo, String contactName,
                            String email, String fax, String postCode, String mobileNo) {
        if (!isRegistered) {
            if (SUCCESS.getCode().equals(SmsProxy.register(properties.getSmsRegCode(), properties.getSmsPassword(), enterpriseName,
                    enterpriseShortName, address, telephoneNo, contactName, email, fax, postCode, mobileNo))) {
                isRegistered = true;
            }
        }
        return isRegistered;
    }

    @Transactional
    public SmsReturnGson sendValidationSms(String mobileNo) {
        SmsReturnGson smsReturnGson = new SmsReturnGson(FAIL, SmsReturn.SYSTEM_ERROR.getMessage());
        try {
            String validateCode = BusinessNoGenerator.generateSmsValidateCode();
            logger.info(String.format("发送短信验证码:%s给手机用户:%s", validateCode, mobileNo));
            SmsTemplateModel smsTemplate = smsTemplateRepository.getSmsTemplateByType(VALIDATION_CODE.name());
            if (!checkMobileNo(mobileNo, smsTemplate, smsReturnGson)) {
                return smsReturnGson;
            }
            String content = String.format(smsTemplate.getTemplateContent(), validateCode, smsTemplate.getValidMinutes());
            boolean isSendSuccess = SUCCESS.getCode().equals(SmsProxy.sendSms(properties.getSmsRegCode(), properties.getSmsPassword(), content, mobileNo));
            if (isSendSuccess) {
                smsRepository.insertSms(new SmsModel(smsTemplate.getId(), mobileNo, content,
                        validateCode, VALIDATION_CODE.name(), new DateTime().plusMinutes(smsTemplate.getValidMinutes()).toDate()));
                smsReturnGson.setResult(TRUE);
                smsReturnGson.setErrorMessage(SmsReturn.SUCCESS.getMessage());
                logger.info(String.format("给用户手机:%s发送短信验证码:%s成功", mobileNo, validateCode));
            } else {
                logger.info(String.format("给用户手机:%s发短信验证码短信失败", mobileNo));
            }
            return smsReturnGson;
        } catch (Exception e) {
            logger.error(String.format("发送验证码给手机用户:%s异常:%s", mobileNo, e.getMessage()), e);
            return smsReturnGson;
        }

    }


    @Transactional
    public SmsReturnGson sendDeliverySms(String orderNo) {
        SmsReturnGson smsReturnGson = new SmsReturnGson(FAIL, SmsReturn.SYSTEM_ERROR.getMessage());
        try {
            logger.info(String.format("发送订单号:%s发货通知短信", orderNo));
            OrderModel order = orderRepository.getByOrderNo(orderNo);
//            String mobileNo = mcMemberRepository.getMcMemberByUid(Integer.valueOf(order.getFrom_user())).getMobile();
            AddressModel address = addressRepository.getAddressById(order.getAddressid());
            if (address == null) {
                smsReturnGson.setResult(SmsReturn.MOBILE_NOT_EXIST.getCode());
                smsReturnGson.setErrorMessage(SmsReturn.MOBILE_NOT_EXIST.getMessage());
                return smsReturnGson;
            }
            String mobileNo = address.getMobile();
            String deliveryCode = order.getLogisBillNo();
            logger.info(String.format("发送物流单号:%s给用户手机%s", deliveryCode, mobileNo));
            SmsTemplateModel smsTemplate = smsTemplateRepository.getSmsTemplateByType(DELIVERED_NOTIFICATION.name());
            if (!checkMobileNo(mobileNo, smsTemplate, smsReturnGson)) {
                return smsReturnGson;
            }
            String content = String.format(smsTemplate.getTemplateContent(), deliveryCode);
            boolean isSendSuccess = SUCCESS.getCode().equals(SmsProxy.sendSms(properties.getSmsRegCode(), properties.getSmsPassword(), content, mobileNo));
            if (isSendSuccess) {
                smsRepository.insertSms(new SmsModel(smsTemplate.getId(), mobileNo, content,
                        deliveryCode, DELIVERED_NOTIFICATION.name(), new DateTime().plusMinutes(smsTemplate.getValidMinutes()).toDate()));
                smsReturnGson.setResult(TRUE);
                smsReturnGson.setErrorMessage(SmsReturn.SUCCESS.getMessage());
                logger.info(String.format("给用户手机:%s发货通知短信成功", mobileNo));
            } else {
                logger.info(String.format("给用户手机:%s发货通知短信失败s", mobileNo));
            }
            return smsReturnGson;
        } catch (Exception e) {
            logger.error(String.format("发送订单号:%s发货通知短信异常:%s", orderNo, e.getMessage()));
            return smsReturnGson;
        }
    }


    @Transactional
    public SmsReturnGson sendPasswordResetSms(String mobileNo) {
        SmsReturnGson smsReturnGson = new SmsReturnGson(FAIL, SmsReturn.SYSTEM_ERROR.getMessage());
        try {
            String passwordResetCode = BusinessNoGenerator.generatePasswordResetCode();
            logger.info(String.format("发送密码重置校验码:%s给用户%s", passwordResetCode, mobileNo));
            SmsTemplateModel smsTemplate = smsTemplateRepository.getSmsTemplateByType(PASSWORD_RESET.name());
            if (!checkMobileNo(mobileNo, smsTemplate, smsReturnGson)) {
                return smsReturnGson;
            }
            String content = String.format(smsTemplate.getTemplateContent(), passwordResetCode, smsTemplate.getValidMinutes());
            boolean isSendSuccess = SUCCESS.getCode().equals(SmsProxy.sendSms(properties.getSmsRegCode(), properties.getSmsPassword(), content, mobileNo));
            if (isSendSuccess) {
                smsRepository.insertSms(new SmsModel(smsTemplate.getId(), mobileNo, content,
                        passwordResetCode, PASSWORD_RESET.name(), new DateTime().plusMinutes(smsTemplate.getValidMinutes()).toDate()));
                smsReturnGson.setResult(TRUE);
                smsReturnGson.setErrorMessage(SmsReturn.SUCCESS.getMessage());
                logger.info(String.format("给用户手机:%s发送密码重置短信验证码:%s成功", mobileNo, passwordResetCode));
            } else {
                logger.info(String.format("给用户手机:%s发送密码重置短信失败", mobileNo));
            }
            return smsReturnGson;
        } catch (Exception e) {
            logger.error(String.format("发送密码重置短信给手机用户:%s异常:%s", mobileNo, e.getMessage()), e);
            return smsReturnGson;
        }
    }

    public SmsReturnGson validateSmsCode(String mobileNo, String validationCode) {
        SmsReturnGson smsReturnGson = new SmsReturnGson(FAIL, SmsReturn.VALIDATE_FAIL.getMessage());
        try {
            logger.info(String.format("校验手机用户:%s短信验证码:%s是否正确", mobileNo, validationCode));
            SmsModel sms = smsRepository.getLastSmsByMobileNo(mobileNo);
            if (sms != null) {
                if (sms.getSmsCode().equals(validationCode) && sms.getExpiredDate().after(new Date())) {
                    smsReturnGson.setResult(TRUE);
                    smsReturnGson.setErrorMessage(SmsReturn.SUCCESS.getMessage());
                    logger.info(String.format("校验手机用户:%s短信验证码:%s通过", mobileNo, validationCode));
                } else {
                    logger.warn(String.format("校验手机用户:%s短信验证码:%s不通过", mobileNo, validationCode));
                }
            } else {
                logger.warn(String.format("找不到手机用户:%s对应的验证码:%s短信", mobileNo, validationCode));
                logger.warn(String.format("校验手机用户:%s短信验证码:%s不通过", mobileNo, validationCode));
            }
        } catch (Exception e) {
            logger.error(String.format("校验手机用户:%s短信验证码:%s的时候异常:%s", mobileNo, validationCode, e.getMessage()), e);
            smsReturnGson.setErrorMessage(SmsReturn.SYSTEM_ERROR.getMessage());
        }
        return smsReturnGson;
    }

    private boolean checkMobileNo(String mobileNo, SmsTemplateModel smsTemplate, SmsReturnGson smsReturnGson) {
        try {
            SmsModel lastSms = smsRepository.getLastSmsByMobileNo(mobileNo);
            if (lastSms != null && smsTemplate.getIntervalMinutes() > 0) {
                if (new DateTime(lastSms.getCreatedDate()).plusMinutes(smsTemplate.getIntervalMinutes()).toDate().after(new Date())) {
                    logger.warn(String.format("用户手机:%s未在指定间隔后再次请求发送", mobileNo));
                    smsReturnGson.setResult(FAIL);
                    smsReturnGson.setErrorMessage(SmsReturn.INNER_INTERVAL.getMessage());
                    return false;
                }
            }
            if (smsTemplate.getMaxSendCount() < smsRepository.countTotalSmsTodayByMobileAndType(mobileNo, smsTemplate.getSmsType())) {
                logger.warn(String.format("用户手机:%s发送过于频繁,超过当前限制次数", mobileNo));
                smsReturnGson.setResult(FAIL);
                smsReturnGson.setErrorMessage(SmsReturn.EXCEED_MAX_COUNT.getMessage());
                return false;
            }
        } catch (Exception e) {
            logger.error(String.format("校验手机用户:%s是否能够发送短信异常:%s", mobileNo, e.getMessage()), e);
            return false;
        }
        return true;
    }
}
