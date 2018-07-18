package com.three.order.orderapi.api;

import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.TbOrderPayNotifyVo;
import com.three.order.orderapi.vo.TbOrderPayVo;
import com.three.order.orderapi.vo.TbOrderQueryVo;
import com.three.order.orderapi.vo.TbOrderVo;

/**
 * @Author:luiz
 * @Date: 2018/7/3 19:24
 * @Descripton:
 * @Modify :
 **/
public interface IOrderService {
    OrderResult createOrder(TbOrderVo tbOrderVo);

    OrderResult payOrder(TbOrderPayVo tbOrderPayVo);

    OrderResult notifyOrder(TbOrderPayNotifyVo tbOrderPayNotifyVo);

    OrderResult findOrder(TbOrderQueryVo tbOrderQueryVo);
}
