package com.spring.mvc.resource;

import com.google.gson.Gson;
import com.spring.mvc.model.PriceSheetModel;
import com.spring.mvc.model.gson.PriceSheetGson;
import com.spring.mvc.repository.PriceSheetRepository;
import com.spring.mvc.utils.Logger;
import com.spring.mvc.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liluoqi on 15/9/21.
 */
@Component
@Resource
@Path("priceSheet")
public class PriceSheetResource {

    private Logger logger = Logger.getLogger(PriceSheetResource.class);

    @Autowired
    private PriceSheetRepository priceSheetRepository;

    @POST
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllValidPriceSheets() {
        try {
            List<PriceSheetModel> priceSheets = priceSheetRepository.getAllValidPriceSheets();
            if (priceSheets != null && priceSheets.size() > 0) {
                logger.info("获取有效的报价单");
                List<PriceSheetGson> priceSheetGsons = new ArrayList<PriceSheetGson>();
                for (PriceSheetModel priceSheet : priceSheets) {
                    priceSheetGsons.add(new PriceSheetGson(priceSheet));
                }
                return new Gson().toJson(priceSheetGsons);
            }
        } catch (Exception e) {
            logger.error(String.format("获取有效的报价单异常:%s", e.getMessage()), e);
        }
        return new Gson().toJson(StringUtils.emptyString());
    }
}
