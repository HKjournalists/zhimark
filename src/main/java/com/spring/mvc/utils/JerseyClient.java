package com.spring.mvc.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by liluoqi on 15/4/25.
 */
public class JerseyClient{

    private URI uri;
    private MultivaluedMapImpl multivaluedMap=new MultivaluedMapImpl();

    public JerseyClient(){

    }

    public JerseyClient withUri(String uri) throws URISyntaxException {
        this.uri=new URI(uri);
        return this;
    }

    public JerseyClient addQueryParam(String queryParamName,String queryParamValue){
        this.multivaluedMap.add(queryParamName,queryParamValue);
        return this;
    }

    public JerseyClient addPostParm(String queryParamName,String queryParamValue){
        this.multivaluedMap.add(queryParamName,queryParamValue);
        return this;
    }

}
