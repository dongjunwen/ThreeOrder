package com.three.order.orderrest.controller;

import com.three.order.orderapi.api.IOrderService;
import com.three.order.orderapi.enums.ResultCode;
import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.TbOrderVo;
import com.three.order.orderrest.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    private IOrderService iOrderService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ApiOperation(value="订单创建接口", notes="订单创建接口")
    public OrderResult createOrder(@RequestBody TbOrderVo tbOrderVo, HttpServletRequest request) {
        OrderResult orderResult =OrderResult.newSuccess();
        try{
            if(RequestUtils.isLogin(request)){
                orderResult.setErrorCode(ResultCode.USER_NO_LOGGED_IN);
                return orderResult;
            }
            //创建订单
            tbOrderVo.setUserNo(RequestUtils.getCurrentUser(request).getUserNo());
            orderResult = iOrderService.createOrder(tbOrderVo);
        }catch (Exception e){
            orderResult.setErrorCode(ResultCode.FAIL);
        }
        return orderResult;
    }

    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    @ApiOperation(value="订单支付接口", notes="订单支付接口")
    public OrderResult payOrder() {
        //创建订单
        OrderResult orderResult = iOrderService.payOrder();
        return orderResult;
    }

}
