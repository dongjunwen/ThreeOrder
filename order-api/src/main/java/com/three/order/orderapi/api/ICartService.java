package com.three.order.orderapi.api;

import com.three.order.orderapi.result.OrderResult;

import java.math.BigDecimal;

/**
 * @Author:luiz
 * @Date: 2018/7/3 19:20
 * @Descripton:
 * @Modify :
 **/
public interface ICartService {
    OrderResult addCartItem(String itemId, BigDecimal num);

    OrderResult getCartItemList(String userNo);

    OrderResult updateCartItem(String itemId, BigDecimal num);

    OrderResult deleteCartItem(String itemId);
}
