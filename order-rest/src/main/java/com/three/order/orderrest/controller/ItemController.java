package com.three.order.orderrest.controller;

import com.three.order.orderapi.api.IItemService;
import com.three.order.orderapi.enums.ResultCode;
import com.three.order.orderapi.result.OrderResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ItemController {

    @Autowired
    private IItemService iItemService;

    //private static Logger logger= LoggerFactory.getLogger(ItemController.class);
    @RequestMapping("/{itemNo}")
    public OrderResult showItem(@PathVariable String itemNo) {
        try {
            log.info("商品信息:{},查询成功",itemNo);
            return iItemService.getItemBase(itemNo);
        }catch (Exception e){
            log.info("商品信息:{},查询异常",itemNo);
            return OrderResult.newError(ResultCode.FAIL);
        }

    }

    @RequestMapping(value = "/desc/{itemNo}")
    public OrderResult getItemDesc(@PathVariable String itemNo) {
        try {
            OrderResult itemResult = iItemService.getItemDesc(itemNo);
            log.info("商品描述:{},查询成功",itemNo);
            return itemResult;
        }catch (Exception e){
            log.info("商品描述:{},查询异常",itemNo);
            return OrderResult.newError(ResultCode.FAIL);
        }

    }

    @RequestMapping(value = "/param/{itemNo}")
    public OrderResult getItemParam(@PathVariable String itemNo) {
        try{
            OrderResult itemResult = iItemService.getItemParam(itemNo);
            log.info("商品参数:{},查询成功",itemNo);
            return itemResult;
        }catch (Exception e){
            log.info("商品参数:{},查询异常",itemNo);
            return OrderResult.newError(ResultCode.FAIL);
        }

    }
}
