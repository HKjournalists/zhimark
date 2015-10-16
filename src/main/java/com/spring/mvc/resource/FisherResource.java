package com.spring.mvc.resource;

import com.spring.mvc.service.FisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by liluoqi on 15/9/14.
 */
@Component
@Path("fisher")
public class FisherResource {

    @Autowired
    private FisherService fisherService;

    @Path("get-fisher-product")
    @POST
    @Produces()
    /**
     * for now this is not used cause we have no access authority
     */
    public void getOrUpdateFisherProduct() {
        fisherService.getOrUpdateFisherProductInfos();
    }

}
