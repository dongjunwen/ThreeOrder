package com.three.order.orderrest.controller;

import com.three.order.orderapi.api.IOrderService;
import com.three.order.orderapi.enums.ResultCode;
import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.TbOrderPayVo;
import com.three.order.orderapi.vo.TbOrderQueryVo;
import com.three.order.orderapi.vo.TbOrderVo;
import com.three.order.orderrest.utils.IpUtils;
import com.three.order.orderrest.utils.RequestUtils;
import com.three.order.orderrest.utils.UserThreadLocal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: luiz
 * @Date: Create in 2018/07/03 19:24
 * @Description:订单接口
 */
@Controller
@RequestMapping("/api/order")
@Api(tags = "订单",description = "订单相关api")
@Slf4j
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ApiOperation(value="订单创建接口", notes="订单创建接口")
    public OrderResult createOrder( TbOrderVo tbOrderVo, HttpServletRequest request) {
        OrderResult orderResult =OrderResult.newSuccess();
        try{
            if(!RequestUtils.isLogin(request)){
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

    @ResponseBody
    @RequestMapping(value = "/findOrder",method = RequestMethod.POST)
    @ApiOperation(value="订单查询接口", notes="订单查询接口")
    public OrderResult findOrder( TbOrderQueryVo tbOrderQueryVo, HttpServletRequest request) {
        OrderResult orderResult =OrderResult.newSuccess();
        try{
            if(!RequestUtils.isLogin(request)){
                orderResult.setErrorCode(ResultCode.USER_NO_LOGGED_IN);
                return orderResult;
            }
            //订单查询
            tbOrderQueryVo.setUserNo(RequestUtils.getCurrentUser(request).getUserNo());
            orderResult = iOrderService.findOrder(tbOrderQueryVo);
        }catch (Exception e){
            orderResult.setErrorCode(ResultCode.FAIL);
        }
        return orderResult;
    }

    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    @ApiOperation(value="订单支付接口", notes="订单支付接口")
    public ModelAndView payOrder(TbOrderPayVo tbOrderPayVo, HttpServletRequest request, HttpServletResponse response) {
        //发起支付
        ModelAndView modelAndView=new ModelAndView();
        String retMsg="";
        String respData="";
        try{
            //发起支付
            tbOrderPayVo.setUserNo(UserThreadLocal.get().getUserNo());
            tbOrderPayVo.setEquipIp(IpUtils.getIpAddr(request));
            OrderResult orderResult = iOrderService.payOrder(tbOrderPayVo);
            if(orderResult.isSuccess()){
                respData=(String) orderResult.getData();
                log.info("[订单创建]返回数据:{}",respData);
                response.setContentType("text/html; charset=utf-8");
                OutputStream respOutStream= null;
                try {
                    respOutStream = response.getOutputStream();
                    respOutStream.write(respData.getBytes("utf-8"));
                    respOutStream.flush();
                    respOutStream.close();
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    retMsg=e.getMessage();
                    modelAndView.setViewName("error");
                }
            }
        }catch (Exception e){
           log.error("[订单支付]发生异常!{}",e);
            retMsg=e.getMessage();
        }
        modelAndView.addObject("retMsg",retMsg);
        modelAndView.addObject("respData",respData);
        modelAndView.setViewName("orderSuccess");
        return modelAndView;
    }

}
