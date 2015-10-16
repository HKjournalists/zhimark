package com.spring.mvc.resource;

import com.google.gson.Gson;
import com.spring.mvc.component.Properties;
import com.spring.mvc.model.serviceResult.SinoChinaResultModel;
import com.spring.mvc.service.SinoChinaService;
import com.spring.mvc.utils.AESUtils;
import com.spring.mvc.utils.Logger;
import com.spring.mvc.utils.RSAUtils;
import com.spring.mvc.utils.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by liluoqi on 15/5/1.
 */
@Component
@Path("receive")
public class OrderResultReceiverResource {

    @Autowired
    private Properties properties;
    @Autowired
    private SinoChinaService sinoChinaService;

    private Logger logger = Logger.getLogger(OrderResultReceiverResource.class);

    //todo 电子口岸异步返回接受订单数据结果
    @Path("ecoPortErrorOrderReceiver")
    @POST
    public String receiver(@FormParam("content") String content,
                           @FormParam("msg_type") String msg_type,
                           @FormParam("data_digest") String data_digest) {
        try {
            String originPlainContent = new String(AESUtils.decryptToBytes(Base64.decodeBase64(content), Base64.decodeBase64(properties.getEcoPortAesKey())));
            //验签
            if (!RSAUtils.verify(originPlainContent, properties.getEcoPortPublicKey(), data_digest)) {
                logger.error(String.format("电子口岸返回订单发送结果签名校验失败"));
                return StringUtils.emptyString();
            }
            logger.info(String.format("验签成功,获取的明文是:%s", originPlainContent));
        } catch (Exception e) {
            logger.error(String.format("exception message is : %s,stackTrace is : %s  happens", e.getMessage(), e.getStackTrace()));
        }
        return StringUtils.emptyString();
    }

    /**
     * 中外运调用的异步回执接口
     *
     * @param result with json data format
     * @return
     */
    @Path("receiveOrderCallbackFromSinoAsync")
    @POST
    public String receiveAsyncResultFromSinoChina(@FormParam("result") String result) {
        try {
            if (!StringUtils.isEmptyOrNull(result)) {
                logger.info(String.format("收到中外运返回的异步回执是:%s", result));
                return sinoChinaService.handleAsyncResultFromSinoChina(new Gson().fromJson(result, SinoChinaResultModel.class));
            }
            logger.error(String.format("收到中外运返回的异步回执为空或是null:%s", result));
        } catch (Exception e) {
            logger.error(String.format("exception e message:%s ,stack trace :%s happens when handle async result from sino china",
                    e.getMessage(), e.getStackTrace()));
        }
        return "fail";
    }
}
