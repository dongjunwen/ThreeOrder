package com.three.order.orderservice.apiimpl;

import com.three.order.orderapi.api.ICartService;
import com.three.order.orderapi.result.OrderResult;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/7/3 19:20
 * @Descripton:
 * @Modify :
 **/
@Service
public class CartService implements ICartService {
    @Override
    public OrderResult addCartItem(Long itemId, Integer num) {
        return null;
    }

    @Override
    public OrderResult getCartItemList(String userNo) {
        return null;
    }

    @Override
    public OrderResult updateCartItem(Long itemId, Integer num) {
        return null;
    }

    @Override
    public OrderResult deleteCartItem(Long itemId) {
        return null;
    }
}
