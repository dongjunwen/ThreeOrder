package com.three.order.orderrest.controller;

import com.three.order.orderapi.api.IItemService;
import com.three.order.orderapi.result.OrderResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: luiz
 * @Date: Create in 2018/07/03 19:19
 * @Description:商品服务接口
 */
@RestController
@RequestMapping("/api/item")
@Api(tags = "商品",description = "商品相关api")
public class ItemController {

    @Autowired
    private IItemService iItemService;

    @RequestMapping("/{itemId}")
    public OrderResult showItem(@PathVariable String itemNo) {
        OrderResult itemResult = iItemService.getItemBase(itemNo);
        return itemResult;
    }

    @RequestMapping(value = "/desc/{itemId}")
    public OrderResult getItemDesc(@PathVariable String itemNo) {
        OrderResult itemResult = iItemService.getItemDesc(itemNo);
        return itemResult;
    }

    @RequestMapping(value = "/param/{itemId}")
    public OrderResult getItemParam(@PathVariable String itemNo) {
        OrderResult itemResult = iItemService.getItemParam(itemNo);
        return itemResult;
    }
}
