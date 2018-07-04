package com.three.order.orderrest.controller;

import com.three.order.orderapi.api.ICartService;
import com.three.order.orderapi.api.IOrderService;
import com.three.order.orderapi.result.OrderResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: luiz
 * @Date: Create in 2018/07/03 19:24
 * @Description:订单接口
 */
@RestController
@RequestMapping("/api/order")
@Api(tags = "订单",description = "订单相关api")
public class OrderController {

    @Autowired
    private ICartService iCartService;
    @Autowired
    private IOrderService iOrderService;

    @RequestMapping("/order-cart")
    public OrderResult showOrderCart() {
        String userNo="";
        OrderResult orderResult = iCartService.getCartItemList(userNo);
        return orderResult;
    }

    @RequestMapping("/create")
    public OrderResult createOrder() {
        //创建订单
        OrderResult orderResult = iOrderService.createOrder();
        return orderResult;
    }

}
