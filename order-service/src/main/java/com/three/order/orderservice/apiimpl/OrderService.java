package com.three.order.orderservice.apiimpl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.three.order.orderapi.api.IOrderService;
import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.TbItemVo;
import com.three.order.orderapi.vo.TbOrderPayVo;
import com.three.order.orderapi.vo.TbOrderShippingVo;
import com.three.order.orderapi.vo.TbOrderVo;
import com.three.order.orderapi.vo.pay.CommonReqParam;
import com.three.order.orderapi.vo.pay.MerPaySeqPo;
import com.three.order.orderapi.vo.pay.MerUnionOrderPo;
import com.three.order.ordercommon.utils.*;
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
import java.util.ArrayList;
import java.util.Date;
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

    private static final String payUrl="http://localhost:9005/api/trade";

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
    public OrderResult payOrder(TbOrderPayVo tbOrderPayVo) {

        TbOrder tbOrder=tbOrderResp.findByOrderNo(tbOrderPayVo.getOrderNo());
        //1.根据订单号查询订单信息
        CommonReqParam commonReqParam=new CommonReqParam();
        commonReqParam.setMerNo("6002111111119");
        commonReqParam.setCharsetCode("utf-8");
        commonReqParam.setForwardUrl("http://www.baidu.com");
        commonReqParam.setNotifyUrl("http://www.baidu.com");
        commonReqParam.setServiceName("UNION_CREATE_ORDER");
        commonReqParam.setRequestTime(DateUtil.getDateTimeFormat(new Date()));
        commonReqParam.setSignType("MD5");
        commonReqParam.setVersion("1.0");

        MerUnionOrderPo merUnionOrderPo =new MerUnionOrderPo();
        merUnionOrderPo.setProductNo(tbOrderPayVo.getPayWay());//支付方式 待调整
        merUnionOrderPo.setDiscountAmt("0.00");
        merUnionOrderPo.setOrderAmt(tbOrder.getActOrderAmt().toString());
        merUnionOrderPo.setPayAmt(tbOrder.getActOrderAmt().toString());
        merUnionOrderPo.setEquipIp(tbOrderPayVo.getEquipIp());
        merUnionOrderPo.setEquipType("WEB");
        merUnionOrderPo.setGoodsName(tbOrder.getOrderDesc());
        merUnionOrderPo.setUserNo(tbOrderPayVo.getUserNo());
        MerPaySeqPo merPaySeqPo=new MerPaySeqPo();
        merPaySeqPo.setMerOrderNo(tbOrder.getOrderNo());
        merPaySeqPo.setMerPaySeq(tbOrder.getOrderNo());//支付流水表 todo  待调整
        List<MerPaySeqPo> merPaySeqPoList=new ArrayList<MerPaySeqPo>();
        merPaySeqPoList.add(merPaySeqPo);
        merUnionOrderPo.setOrderList(merPaySeqPoList);


        commonReqParam.setReqContent(JSONObject.toJSONString(merUnionOrderPo));
        String signStr= PairString.createLinkString(JSONObject.parseObject(JSONObject.toJSONString(commonReqParam)));
        String signValue= MD5Util.getMD5(signStr,"01234567890");
        commonReqParam.setSignValue(signValue);
        String respStr= HttpClientUtil.doPost(payUrl,JSONObject.toJSONString(commonReqParam));
        JSONObject respJson=JSONObject.parseObject(respStr);
        String retCode=respJson.getString("retCode");
        String retMsg=respJson.getString("retMsg");
        if("200".equals(retCode)){
            return OrderResult.newSuccess(respJson);
        }else{
            return OrderResult.newError(retCode,retMsg);
        }
    }
}
