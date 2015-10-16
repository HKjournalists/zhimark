package com.spring.mvc.resource;

import com.spring.mvc.component.ContextComponents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by liluoqi on 15/7/22.
 */
@Component
@Path("users")
public class UsersResource {

    @Autowired
    private ContextComponents contextComponents;

    @Path("{userId}")
    public UserResource getUserResource(@PathParam("userId") int userId) {
        return new UserResource(userId,contextComponents);
    }
}
