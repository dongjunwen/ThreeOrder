package com.three.order.orderrest.controller;

import com.three.order.orderapi.enums.ResultCode;
import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.CartItemVo;
import com.three.order.orderrest.service.CartRestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: luiz
 * @Date: Create in 2018/07/03 19:10
 * @Description: 购物车
 */
@RestController
@RequestMapping("/api/cart")
@Api(tags = "购物车",description = "购物车相关api")
public class CartController {
    private static final Logger logger= LoggerFactory.getLogger(CartController.class);

    @Autowired
    CartRestService cartRestService;

    @PostMapping("/add")
    @ApiOperation(value="购物车添加商品", notes="购物车添加商品")
    @ApiParam(name = "cartItemVo", value = "商品实体 cartItemVo",required = true)
    public OrderResult addCartItem(CartItemVo cartItemVo,HttpServletRequest request,HttpServletResponse response) {
        try{
            return cartRestService.addCartItem(cartItemVo,request,response);
        }catch (Exception e){
            logger.error("[购物车]添加商品发生异常:{}",e);
            return OrderResult.newError(ResultCode.FAIL);
        }
    }

    @GetMapping("/findList")
    @ApiOperation(value="购物车列表查询", notes="购物车列表查询")
    public OrderResult getCartItemList(HttpServletRequest request) {
        try{
            OrderResult orderResult=OrderResult.newSuccess();
            orderResult.setData(cartRestService.getCartItemList(request));
            return orderResult;
        }catch (Exception e){
            logger.error("[购物车]查询购物车列表发生异常:{}",e);
            return OrderResult.newError(ResultCode.FAIL);
        }
    }

    @PostMapping("/update")
    @ApiOperation(value="购物车商品数量更新", notes="购物车商品数量更新")
    @ApiParam(name = "cartItemVo", value = "商品实体 cartItemVo",required = true)
    public OrderResult updateCartItem(CartItemVo cartItemVo,HttpServletRequest request, HttpServletResponse response) {
        try{
           return  cartRestService.updateCartItem(cartItemVo.getItemNo(), cartItemVo.getItemNum(),request,response);
        }catch (Exception e){
            logger.error("[购物车]更新购物车发生异常:{}",e);
            return OrderResult.newError(ResultCode.FAIL);
        }
    }

    @PostMapping("/delete/{itemNo}")
    @ApiOperation(value="删除购物车内商品", notes="删除购物车内商品")
    @ApiImplicitParam(name = "itemNo", value = "商品编号 itemNo",required = true)
    public OrderResult deleteCartItem(@PathVariable String itemNo,HttpServletRequest request, HttpServletResponse response) {
        try{
            return  cartRestService.deleteCartItem(itemNo,request,response);
        }catch (Exception e){
            logger.error("[购物车]删除购物车发生异常:{}",e);
            return OrderResult.newError(ResultCode.FAIL);
        }
    }

}
