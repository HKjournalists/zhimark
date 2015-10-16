package com.spring.mvc.resource;

import com.google.gson.Gson;
import com.spring.mvc.service.PushPayInfoToAlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by liluoqi on 15/6/7.
 */
@Component
@Path("alipay")
public class PushPayNumberToAlipayResource {

    @Autowired
    private PushPayInfoToAlipayService pushPayInfoToAlipayService;

    /**
     * 推送支付信息给海关
     */
    @Path("push-payInfo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String pushPayInfoToAlipay(@FormParam("orderId") String orderId, @FormParam("orderNo") String orderNo) {
        return new Gson().toJson(pushPayInfoToAlipayService.pushPayInfoAlipay(orderId, orderNo));
    }

//    @Path("push-payInfo")
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    public String pushPayInfoToAlipayByOrderNo(@QueryParam("orderNo")String orderNo){
//        return new Gson().toJson(pushPayInfoToAlipayService.pushPayInfoAlipay(orderNo));
//    }
}
