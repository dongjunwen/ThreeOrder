package com.three.order.orderrest.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author:luiz
 * @Date: 2018/7/17 18:24
 * @Descripton:
 * @Modify :
 **/
@Controller
@RequestMapping("/show")
@Api(tags = "页面跳转",description = "页面跳转")
public class IndexController {

    @RequestMapping("/goPay")
    public ModelAndView createOrder(ModelMap modelMap){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("goPay");
        return modelAndView;
    }


}
