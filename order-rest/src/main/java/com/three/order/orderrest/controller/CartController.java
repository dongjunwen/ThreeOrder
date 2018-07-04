package com.three.order.orderrest.controller;

import com.three.order.orderapi.api.ICartService;
import com.three.order.orderapi.result.OrderResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: luiz
 * @Date: Create in 2018/07/03 19:10
 * @Description: 购物车
 */
@RestController
@RequestMapping("/api/cart")
@Api(tags = "购物车",description = "购物车相关api")
public class CartController {

    @Autowired
    private ICartService iCartService;

    @RequestMapping("/add/{itemId}")
    public OrderResult addCartItem(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num) {
        OrderResult orderResult = iCartService.addCartItem(itemId, num);
        return orderResult;
    }

    @RequestMapping("/cart")
    public OrderResult getCartItemList(HttpServletRequest request) {
        String userNo="";
        OrderResult orderResult = iCartService.getCartItemList(userNo);
        return orderResult;
    }

    @RequestMapping("/update/num/{itemId}/{num}")
    public OrderResult updateCartItem(@PathVariable Long itemId, @PathVariable Integer num) {
        return  iCartService.updateCartItem(itemId, num);
    }

    @RequestMapping("/delete/{itemId}")
    public OrderResult deleteCartItem(@PathVariable Long itemId) {
        return  iCartService.deleteCartItem(itemId);
    }

}
