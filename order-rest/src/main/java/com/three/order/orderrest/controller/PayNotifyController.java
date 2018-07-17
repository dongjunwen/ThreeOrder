package com.three.order.orderrest.controller;

import com.three.order.orderapi.api.IOrderService;
import com.three.order.orderapi.enums.ResultCode;
import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.TbOrderPayNotifyVo;
import com.three.order.orderapi.vo.TbOrderVo;
import com.three.order.orderrest.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:luiz
 * @Date: 2018/7/17 15:17
 * @Descripton:
 * @Modify :
 **/
@RestController
@RequestMapping("/api/pay")
@Api(tags = "订单支付相关",description = "订单支付相关")
@Slf4j
public class PayNotifyController {
    @Autowired
    private IOrderService iOrderService;

    @RequestMapping(value = "/notifyResult",method = RequestMethod.POST)
    @ApiOperation(value="结果通知接口", notes="结果通知接口")
    public String createOrder(@RequestBody TbOrderPayNotifyVo tbOrderPayNotifyVo) {
        String resultMsg="00";
        try{
            log.info("[订单支付通知]接受参数:{}",tbOrderPayNotifyVo);
            //1.签名校验
                //todo 待调整
            //2.处理结果
            OrderResult orderResult=iOrderService.notifyOrder(tbOrderPayNotifyVo);
            if(!orderResult.isSuccess())
            resultMsg=orderResult.getRetMsg();
        }catch (Exception e){
            resultMsg="99-通知发生异常!";
            log.error("[订单支付通知]发生异常:{}",e);
        }
        return resultMsg;
    }

}
