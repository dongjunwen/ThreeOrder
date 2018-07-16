package com.three.order.orderapi.api;

import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.TbOrderVo;

/**
 * @Author:luiz
 * @Date: 2018/7/3 19:24
 * @Descripton:
 * @Modify :
 **/
public interface IOrderService {
    OrderResult createOrder(TbOrderVo tbOrderVo);

    OrderResult payOrder();
}
