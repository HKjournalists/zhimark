package com.spring.mvc.resource;

import com.google.gson.Gson;
import com.spring.mvc.component.ContextComponents;
import com.spring.mvc.component.Properties;
import com.spring.mvc.model.gson.sinoChina.SinoChinaOrderRequestDataGson;
import com.spring.mvc.model.serviceResult.SinoChinaResultModel;
import com.spring.mvc.proxy.sinoChina.SinoChinaProxy;
import com.spring.mvc.repository.FisherProductRepository;
import com.spring.mvc.service.FisherService;
import com.spring.mvc.service.RepaymentPlanService;
import com.spring.mvc.service.SinoChinaService;
import com.spring.mvc.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created by liluoqi on 15/6/6.
 */
@Component
public class TestResource {

    @Autowired
    private RepaymentPlanService repaymentPlanService;
    @Autowired
    private FisherService fisherService;
    @Autowired
    private FisherProductRepository fisherProductRepository;
    @Autowired
    private Properties properties;
    @Autowired
    private SinoChinaService sinoChinaService;
    @Autowired
    private SinoChinaProxy sinoChinaProxy;

    private int userId;

    public TestResource() {
    }

    public TestResource(int userId, ContextComponents contextComponents) {
        this.userId = userId;
        this.repaymentPlanService = contextComponents.getRepaymentPlanService();
        this.fisherProductRepository = contextComponents.getFisherProductRepository();
        this.properties=contextComponents.getProperties();
        this.sinoChinaService=contextComponents.getSinoChinaService();
        this.sinoChinaProxy=contextComponents.getSinoChinaProxy();
    }

    @GET
    @Path("print")
    @Produces(MediaType.APPLICATION_JSON)
    public String test() throws Exception {
//
        return "";
    }
}
