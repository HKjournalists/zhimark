package com.spring.mvc.resource.sinoChina;

import com.google.gson.Gson;
import com.spring.mvc.service.SinoChinaService;
import com.spring.mvc.utils.Logger;
import com.spring.mvc.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

/**
 * Created by liluoqi on 15/7/27.
 */
@Component
@Path("/sino")
public class SinoChinaResource {
    @Autowired
    private SinoChinaService sinoChinaService;

    private Logger logger = Logger.getLogger(SinoChinaResource.class);

    @Path("get-logis-info")
    @GET
    @Produces("application/json")
    public String getLogisInfoByBillNo(@QueryParam("logisBillNo") String logisBillNo, @QueryParam("callback") String callback) {
        logger.info(String.format("收到查询物流号:%s轨迹的请求", logisBillNo));
        return StringUtils.isEmptyOrNull(callback) ? new Gson().toJson(sinoChinaService.getLogisInfoByBillNoFromSinoChina(logisBillNo)) :
                String.format("%s(%s)", callback, new Gson().toJson(sinoChinaService.getLogisInfoByBillNoFromSinoChina(logisBillNo)));
    }
}
