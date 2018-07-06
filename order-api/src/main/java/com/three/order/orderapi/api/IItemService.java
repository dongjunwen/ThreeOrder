package com.three.order.orderapi.api;

import com.three.order.orderapi.result.OrderResult;

/**
 * @Author:luiz
 * @Date: 2018/7/3 19:17
 * @Descripton:
 * @Modify :
 **/
public interface IItemService {
    OrderResult getItemBase(String itemNo);

    OrderResult getItemDesc(String itemNo);

    OrderResult getItemParam(String itemNo);
}
