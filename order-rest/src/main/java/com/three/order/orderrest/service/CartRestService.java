package com.three.order.orderrest.service;

import com.alibaba.fastjson.JSONObject;
import com.three.order.orderapi.api.IItemService;
import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.CartItemVo;
import com.three.order.ordercommon.constant.CommonConstants;
import com.three.order.orderjdbc.entity.TbItem;
import com.three.order.orderrest.pojo.CartItem;
import com.three.order.orderrest.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/7/6 15:36
 * @Descripton:
 * @Modify :
 **/
@Component
public class CartRestService {
    @Autowired
    private IItemService iItemService;

    public OrderResult addCartItem(CartItemVo cartItemVo, HttpServletRequest request,HttpServletResponse response) {
        //从cookie中取购物车商品列表
        List<CartItem> cartItemList = getCartItemList(request);

        //购物车中是否存在商品
        CartItem cartItem = null;
        for (CartItem item : cartItemList) {
            if (item.getItemNo().equals(cartItemVo.getItemNo())) {
                cartItem = item;
                break;
            }
        }

        if (cartItem != null) {
            cartItem.setNum(cartItem.getNum().add(cartItemVo.getItemNum()));
        } else {
            cartItem = new CartItem();
            //根据商品id查询商品基本信息
            OrderResult itemResult=iItemService.getItemBase(cartItemVo.getItemNo());
            if(itemResult.isSuccess()){
                TbItem item = (TbItem) itemResult.getData();
                cartItem.setItemNo(item.getItemNo());
                cartItem.setItemTitle(item.getItemTitle());
                cartItem.setPicUrl(item.getPicUrl());
                cartItem.setNum(cartItemVo.getItemNum());
                cartItem.setPrice(item.getPrice());
            }
            cartItemList.add(cartItem);
        }

        //把购物车写入cookie
        CookieUtils.setCookie(request, response, CommonConstants.CART_COOKIE, JSONObject.toJSONString(cartItemList), true);
        return  OrderResult.newSuccess();
    }


    /**
     * 从cookie中获取购物车中的商品列表
     */
    public List<CartItem> getCartItemList(HttpServletRequest request) {
        String json = CookieUtils.getCookieValue(request, CommonConstants.CART_COOKIE, true);
        if (StringUtils.isBlank(json)) {
            return new ArrayList<>();
        }
        try {
            List<CartItem> list = ( List<CartItem>)JSONObject.parse(json);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public OrderResult updateCartItem(String  itemNo, BigDecimal itemNum, HttpServletRequest request, HttpServletResponse response) {
        //从cookie中取购物车商品列表
        List<CartItem> cartItemList = getCartItemList(request);

        //购物车中是否存在商品
        CartItem cartItem = null;
        for (CartItem item : cartItemList) {
            if (item.getItemNo().equals(itemNo)) {
                cartItem = item;
                break;
            }
        }

        if (cartItem != null) {
            cartItem.setNum(itemNum);
        } else {
            cartItem = new CartItem();

            //根据商品id查询商品基本信息
            OrderResult itemResult=iItemService.getItemBase(itemNo);
            if(itemResult.isSuccess()){
                TbItem item = (TbItem) itemResult.getData();
                cartItem.setItemNo(item.getItemNo());
                cartItem.setItemTitle(item.getItemTitle());
                cartItem.setPicUrl(item.getPicUrl());
                cartItem.setNum(itemNum);
                cartItem.setPrice(item.getPrice());
            }
                cartItemList.add(cartItem);
        }
        //把购物车写入cookie
        CookieUtils.setCookie(request, response,  CommonConstants.CART_COOKIE, JSONObject.toJSONString(cartItemList), true);
        return OrderResult.newSuccess();
    }

    public OrderResult deleteCartItem(String itemNo, HttpServletRequest request, HttpServletResponse response) {
        //从cookie中取购物车商品列表
        List<CartItem> itemList = getCartItemList(request);
        //从列表中找到此商品并删除
        for (CartItem cartItem : itemList) {
            if (cartItem.getItemNo().equals(itemNo)) {
                itemList.remove(cartItem);
                break;
            }
        }
        //把购物车列表重新写入cookie
        CookieUtils.setCookie(request, response,  CommonConstants.CART_COOKIE, JSONObject.toJSONString(itemList), true);
        return OrderResult.newSuccess();

    }

}
