package com.spring.mvc.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/velocity-test", method = RequestMethod.GET)
    public ModelAndView printWelcome(HttpServletRequest request, HttpServletResponse response) {
//        Map map = new HashMap();
//        map.put("hello", message);
//        request.setAttribute("hello", message);
        return new ModelAndView("hello");
    }
}