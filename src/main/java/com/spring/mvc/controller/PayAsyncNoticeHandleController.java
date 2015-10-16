package com.spring.mvc.controller;

import com.google.gson.Gson;
import com.spring.mvc.component.Properties;
import com.spring.mvc.model.OrderModel;
import com.spring.mvc.model.OrderStatus;
import com.spring.mvc.model.PayType;
import com.spring.mvc.model.gson.AlipayAsyncNoticeResultGson;
import com.spring.mvc.model.gson.LcPayAsyncNoticeResultGson;
import com.spring.mvc.repository.OrderRepository;
import com.spring.mvc.service.PayService;
import com.spring.mvc.utils.Logger;
import com.spring.mvc.utils.MD5Utils;
import com.spring.mvc.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static com.spring.mvc.utils.StringUtils.emptyString;

/**
 * Created by liluoqi on 15/8/3.
 */
@Controller
@RequestMapping("/pay")
public class PayAsyncNoticeHandleController {
    /**
     * 连连支付异步回执处理
     *
     * @return 处理的结果0000代表成功, 其他代表失败
     */

    @Autowired
    private PayService payService;

    private Logger logger = Logger.getLogger(PayAsyncNoticeHandleController.class);

    @RequestMapping(value = "/lcPay-notice", method = RequestMethod.POST)
    @ResponseBody
    public String handleLcPay(HttpServletRequest request, HttpServletResponse response) {
        logger.info("收到连连支付的异步回执请求");
        try {
            return payService.lcPayNoticeHandler(request.getInputStream());
        } catch (IOException e) {
            logger.error(String.format("get input steam from request error with an IO exception:%s", e.getMessage()), e);
        }
        return new Gson().toJson(MapUtils.convertStringToMap("retcode", "1111", "retmsg", "Fail"));
    }

    @RequestMapping(value = "/alipay-notice", method = RequestMethod.POST)
    @ResponseBody
    public void handleAlipay(String out_trade_no, String trade_no, String trade_status) {
        logger.info(String.format("收到支付宝支付的异步回执请求,订单是:%s,支付交易号:%s,交易状态:%s", out_trade_no, trade_no, trade_status));
        try {
            payService.alipayNoticeHandler(out_trade_no, trade_no, trade_status);
        } catch (Exception e) {
            logger.error(String.format("get input steam from request error with an exception:%s", e.getMessage()), e);
        }
    }
}
