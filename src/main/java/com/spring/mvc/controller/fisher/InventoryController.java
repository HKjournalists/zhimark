package com.spring.mvc.controller.fisher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by liluoqi on 15/9/6.
 */
@Controller
public class InventoryController {

    @RequestMapping("inventory")
    public ModelAndView index() {
        return new ModelAndView("fisher/fisherManage");
    }
}
