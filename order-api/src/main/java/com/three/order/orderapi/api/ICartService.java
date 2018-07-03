package com.three.order.orderapi.api;

import com.three.order.orderapi.result.OrderResult;

/**
 * @Author:luiz
 * @Date: 2018/7/3 19:20
 * @Descripton:
 * @Modify :
 **/
public interface ICartService {
    OrderResult addCartItem(Long itemId, Integer num);

    OrderResult getCartItemList(String userNo);

    OrderResult updateCartItem(Long itemId, Integer num);

    OrderResult deleteCartItem(Long itemId);
}
