package com.spring.mvc.resource;

import com.google.gson.Gson;
import com.spring.mvc.model.gson.InventoryUpdateGson;
import com.spring.mvc.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by liluoqi on 15/9/6.
 */
@Component
@Path("inventory")
public class InventoryResource {
    @Autowired
    private InventoryService inventoryService;

    @POST
    @Path("product-info-accept")
    @Produces(MediaType.APPLICATION_JSON)
    public String productInfoAccept(@FormParam("productInfo")String productInfo) {

        return "";
    }
}
