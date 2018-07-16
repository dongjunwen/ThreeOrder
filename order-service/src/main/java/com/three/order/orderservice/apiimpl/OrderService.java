package com.three.order.orderservice.apiimpl;

import com.google.common.collect.Lists;
import com.three.order.orderapi.api.IOrderService;
import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.TbItemVo;
import com.three.order.orderapi.vo.TbOrderShippingVo;
import com.three.order.orderapi.vo.TbOrderVo;
import com.three.order.ordercommon.utils.IDUtils;
import com.three.order.orderjdbc.entity.TbOrder;
import com.three.order.orderjdbc.entity.TbOrderItem;
import com.three.order.orderjdbc.entity.TbOrderShipping;
import com.three.order.orderjdbc.respository.TbOrderItemResp;
import com.three.order.orderjdbc.respository.TbOrderResp;
import com.three.order.orderjdbc.respository.TbOrderShippingResp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/7/3 19:27
 * @Descripton:
 * @Modify :
 **/
@Service
public class OrderService implements IOrderService {
    @Autowired
    private TbOrderResp tbOrderResp;
    @Autowired
    private TbOrderItemResp tbOrderItemResp;
    @Autowired
    private TbOrderShippingResp tbOrderShippingResp;
    // 库存扣减待完成 TODO
    @Override
    public OrderResult createOrder(TbOrderVo tbOrderVo) {
        java.util.Date nowDate=new java.util.Date();

        String orderNo= IDUtils.genIdStr("O");
        TbOrder tbOrder=new TbOrder();
        tbOrder.setUserNo(tbOrderVo.getUserNo());
        tbOrder.setOrderNo(orderNo);
        BigDecimal orderAmt=new BigDecimal(tbOrderVo.getOrderAmt());
        BigDecimal coupAmt=new BigDecimal(tbOrderVo.getCoupAmt()==null?"0.00":tbOrderVo.getCoupAmt());
        BigDecimal transAmt=new BigDecimal(tbOrderVo.getTransAmt()==null?"0.00":tbOrderVo.getTransAmt());
        BigDecimal actAmt=orderAmt.add(transAmt).subtract(coupAmt).setScale(2,BigDecimal.ROUND_DOWN);
        tbOrder.setOrderAmt(orderAmt);
        tbOrder.setCoupAmt(coupAmt);
        tbOrder.setTransAmt(transAmt);
        tbOrder.setActOrderAmt(actAmt);
        tbOrder.setOrderDesc(tbOrderVo.getOrderDesc());
        tbOrder.setShopNo(tbOrderVo.getShopNo());
        tbOrder.setOrderTime(nowDate);
        tbOrder.setCreateTime(nowDate);
        tbOrder.setModiTime(nowDate);
        tbOrderResp.save(tbOrder);

        List<TbOrderItem> tbOrderItemList= Lists.newArrayList();
        for(TbItemVo tbItemVo:tbOrderVo.getTbItemVoList()){
            TbOrderItem tbOrderItem=new TbOrderItem();
            tbOrderItem.setItemNo(tbItemVo.getItemNo());
            tbOrderItem.setItemName(tbItemVo.getItemTitle());
            tbOrderItem.setAmt(tbItemVo.getPrice());
            tbOrderItem.setItemPicUrl(tbItemVo.getPicUrl());
            tbOrderItem.setNum(tbItemVo.getNum());
            tbOrderItem.setOrderNo(orderNo);
            tbOrderItem.setCreateTime(nowDate);
            tbOrderItem.setModiTime(nowDate);
            tbOrderItemList.add(tbOrderItem);
        }
        tbOrderItemResp.saveAll(tbOrderItemList);
        TbOrderShipping tbOrderShipping=new TbOrderShipping();
        TbOrderShippingVo tbOrderShippingVo=tbOrderVo.getTbOrderShippingVo();
        tbOrderShipping.setOrderNo(orderNo);
        BeanUtils.copyProperties(tbOrderShippingVo,tbOrderShipping);
        tbOrderShipping.setCreateTime(nowDate);
        tbOrderShipping.setModiTime(nowDate);
        tbOrderShippingResp.save(tbOrderShipping);

        return OrderResult.newSuccess(orderNo);
    }

    @Override
    public OrderResult payOrder() {
        return null;
    }
}
