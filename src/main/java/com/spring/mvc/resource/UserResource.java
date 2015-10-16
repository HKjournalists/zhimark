package com.spring.mvc.resource;

import com.spring.mvc.component.ContextComponents;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;

/**
 * Created by liluoqi on 15/7/22.
 */
@Component
public class UserResource {
    private int userId;
    private ContextComponents contextComponents;

    public UserResource() {
    }

    public UserResource(int userId, ContextComponents contextComponents) {
        this.userId = userId;
        this.contextComponents = contextComponents;
    }

    @Path("test")
    public TestResource getTestResource() {
        return new TestResource(userId,contextComponents);
    }
}
