package com.spring.mvc.proxy.sms;

import java.net.*;

import com.spring.mvc.helper.ConstantsHelper;
import com.spring.mvc.utils.Logger;
import com.spring.mvc.utils.StringUtils;
import com.sun.org.apache.bcel.internal.generic.FADD;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import static com.spring.mvc.helper.ConstantsHelper.DEFAULT_SMS_LEVEL;
import static com.spring.mvc.helper.ConstantsHelper.DEFAULT_SMS_REPORT_FLAG;
import static com.spring.mvc.helper.ConstantsHelper.DEFAULT_SMS_TYPE;
import static com.spring.mvc.proxy.sms.SmsRegisterReturn.OTHER_SYS_ERROR;
import static com.spring.mvc.proxy.sms.SmsRegisterReturn.SUCCESS;

/**
 * Created by liluoqi on 15/7/7.
 */
public class SmsProxy {
    private static final String URL = "http://211.99.191.148";
    private static final String NAME_SPACE = "http://info.pica.com";
    private static final String URI_SEND = "/mms/services/info";
    private static final int SEND_SMS_MAX_TIMES = 3;
    private static Logger logger = Logger.getLogger(SmsProxy.class);

    /**
     * 使用短信帐号进行注册；激活账号。（该方法只使用一次，激活账号即可，激活账号以后不再使用）
     *
     * @param regCode             发送短信的账号 必输
     * @param password            密码 必输
     * @param enterpriseName      企业名称 必输
     * @param enterpriseShortName 企业简称 必输
     * @param address             公司地址 必输
     * @param telephoneNo         联系电话 必输
     * @param contactName         联系人姓名 必输
     * @param email               企业电子邮箱 必输
     * @param fax                 企业传真 必输
     * @param postCode            邮政编码 必输
     * @param mobileNo            联系手机 必输
     * @return 返回的结果码
     */
    public static String register(String regCode, String password, String enterpriseName,
                                  String enterpriseShortName, String address, String telephoneNo, String contactName,
                                  String email, String fax, String postCode, String mobileNo) {
        logger.info(String.format("根据收过来的参数:regCode:%s,password:%s,enterpriseName:%s,enterpriseShortName;%s," +
                        "address:%s注册短信使用", regCode, password, enterpriseName, enterpriseShortName,
                address));
        logger.info(String.format("根据收过来的参数:telephoneNo:%s,contactName:%s,email:%s,fax:%s,postCode:%s,mobileNo:%s注册短信使用", telephoneNo, contactName,
                email, fax, postCode, mobileNo));
        String resultCodeFromSms;
        try {
            Call call = getServiceCallInstance("register");
            resultCodeFromSms = (String) call.invoke(new Object[]{
                    regCode, password, enterpriseName, enterpriseShortName, address, telephoneNo, contactName, email, fax, postCode, mobileNo
            });
            logger.info(String.format("注册短信使用返回的结果码是:%s", resultCodeFromSms));
        } catch (Exception e) {
            logger.error(String.format("调用短信webService注册短信失败:%s", e.getMessage()), e);
            resultCodeFromSms = OTHER_SYS_ERROR.getCode();
        }
        return resultCodeFromSms;
    }


    /**
     * @param regCode  发送短信的账号 必输
     * @param password 密码 必输
     * @param content  短信内容(最多70个字) 必输
     * @param mobileNo 手机号码(最多一次发送100个手机号,号码间用英文逗号分隔) 必输
     * @return
     */
    public static String sendSms(String regCode, String password, String content, String mobileNo) {
        logger.info(String.format("根据收过来的参数:regCode:%s,password:%s,content:%s,mobileNo;%s," +
                "发送短信", regCode, password, content, mobileNo));
        String resultCodeFromSms = StringUtils.emptyString();
        boolean isSendSuccess = false;
        int sendTime = 0;
        try {
            Call call = getServiceCallInstance("sendSMS");
            while (!isSendSuccess && sendTime <= SEND_SMS_MAX_TIMES) {
                String rawResult = (String) call.invoke(new Object[]{
                        regCode, password, mobileNo, java.net.URLEncoder.encode(content, "gbk"), StringUtils.emptyString(), DEFAULT_SMS_LEVEL, StringUtils.emptyString(),
                        DEFAULT_SMS_REPORT_FLAG, StringUtils.emptyString(), DEFAULT_SMS_TYPE
                });
                if (null != rawResult) {
                    resultCodeFromSms = new String(rawResult.getBytes("8859_1"), "gbk");
                    logger.info(String.format("短信运营商返回的结果是:%s", resultCodeFromSms));
                    if (SUCCESS.getCode().equals(resultCodeFromSms)) {
                        isSendSuccess = true;
                    }
                }
                sendTime++;
            }
        } catch (Exception e) {
            logger.error(String.format("发送短信异常信息:%s", e.getMessage()), e);
            resultCodeFromSms = OTHER_SYS_ERROR.getCode();
        }
        return resultCodeFromSms;
    }

    private static Call getServiceCallInstance(String operationName) throws ServiceException, MalformedURLException {
        try {
            Call call = (Call) new Service().createCall();
            call.setTargetEndpointAddress(new URL(URL + URI_SEND));
            int loopCount = 0;
            if ("register".equals(operationName)) {
                loopCount = 11;
            }
            if ("sendSMS".equals(operationName)) {
                loopCount = 10;
            }
            for (int i = 0; i < loopCount; i++) {
                call.addParameter(new QName(NAME_SPACE, String.format("in%s", i)), XMLType.XSD_STRING, ParameterMode.IN);
            }
            call.setOperationName(new QName(NAME_SPACE, operationName));
            call.setReturnType(XMLType.XSD_STRING);
            call.setUseSOAPAction(true);
            call.setSOAPActionURI("http://zzwx/example");
            return call;
        } catch (ServiceException e) {
            logger.error(String.format("创建服务调用实例webService call失败:%s", e.getMessage()), e);
            throw e;
        } catch (MalformedURLException e) {
            logger.error(String.format("制定webService call实例的targetEndpoint目标端口失败:%s", e.getMessage()), e);
            throw e;
        }
    }
}
