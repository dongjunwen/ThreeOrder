package com.three.order.orderservice.apiimpl;

import com.three.order.orderapi.api.IItemService;
import com.three.order.orderapi.result.OrderResult;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/7/3 19:19
 * @Descripton:
 * @Modify :商品服务类
 **/
@Service
public class ItemService implements IItemService {
    @Override
    public OrderResult getItemBase(Long itemId) {
        return null;
    }

    @Override
    public OrderResult getItemDesc(Long itemId) {
        return null;
    }

    @Override
    public OrderResult getItemParam(Long itemId) {
        return null;
    }
}
