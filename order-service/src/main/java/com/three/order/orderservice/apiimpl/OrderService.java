package com.three.order.orderservice.apiimpl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.three.order.orderapi.api.IOrderService;
import com.three.order.orderapi.enums.ResultCode;
import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.*;
import com.three.order.orderapi.vo.pay.CommonReqParam;
import com.three.order.orderapi.vo.pay.MerPaySeqPo;
import com.three.order.orderapi.vo.pay.MerUnionOrderPo;
import com.three.order.ordercommon.enums.OrderStatusEnum;
import com.three.order.ordercommon.enums.PayStatusEnum;
import com.three.order.ordercommon.utils.*;
import com.three.order.orderjdbc.entity.TbOrder;
import com.three.order.orderjdbc.entity.TbOrderItem;
import com.three.order.orderjdbc.entity.TbOrderShipping;
import com.three.order.orderjdbc.entity.TbPayLog;
import com.three.order.orderjdbc.respository.TbOrderItemResp;
import com.three.order.orderjdbc.respository.TbOrderResp;
import com.three.order.orderjdbc.respository.TbOrderShippingResp;
import com.three.order.orderjdbc.respository.TbPayLogResp;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    @Autowired
    private TbPayLogResp tbPayLogResp;

    @Value("${three.order.notifyUrl}")
    private String notifyUrl;
    @Value("${three.order.forwardUrl}")
    private String forwardUrl;
    @Value("${three.order.payUrl}")
    private String payUrl;

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
        tbOrder.setPayType(Integer.valueOf(tbOrderVo.getPayType()));
        tbOrder.setOrderTime(nowDate);
        tbOrder.setCreateTime(nowDate);
        tbOrder.setModiTime(nowDate);
        tbOrderResp.save(tbOrder);

        List<TbOrderItem> tbOrderItemList= Lists.newArrayList();
        if(tbOrderItemList!=null){
            for(TbItemVo tbItemVo:tbOrderVo.getTbItemVoList()){
                TbOrderItem tbOrderItem=new TbOrderItem();
                tbOrderItem.setItemNo(tbItemVo.getItemNo());
                tbOrderItem.setItemName(tbItemVo.getItemTitle());
                tbOrderItem.setAmt(tbItemVo.getPrice());
                tbOrderItem.setItemPicUrl(tbItemVo.getPicUrl());
                tbOrderItem.setNum(tbItemVo.getNum());
                tbOrderItem.setPrice(tbItemVo.getPrice());
                tbOrderItem.setOrderNo(orderNo);
                tbOrderItem.setCreateTime(nowDate);
                tbOrderItem.setModiTime(nowDate);
                tbOrderItemList.add(tbOrderItem);
            }
            tbOrderItemResp.saveAll(tbOrderItemList);
        }
        TbOrderShippingVo tbOrderShippingVo=tbOrderVo.getTbOrderShippingVo();
        if(tbOrderShippingVo!=null){
            TbOrderShipping tbOrderShipping=new TbOrderShipping();
            tbOrderShipping.setOrderNo(orderNo);
            BeanUtils.copyProperties(tbOrderShippingVo,tbOrderShipping);
            tbOrderShipping.setCreateTime(nowDate);
            tbOrderShipping.setModiTime(nowDate);
            tbOrderShippingResp.save(tbOrderShipping);

        }

        return OrderResult.newSuccess(orderNo);
    }

    @Override
    public OrderResult payOrder(TbOrderPayVo tbOrderPayVo) {

        TbOrder tbOrder=tbOrderResp.findByOrderNo(tbOrderPayVo.getOrderNo());
        if(tbOrder==null) {
            OrderResult.newError(ResultCode.ORDER_NOT_EXISTS);
        }
        //1.根据订单号查询订单信息
        CommonReqParam commonReqParam=new CommonReqParam();
        commonReqParam.setMerNo("6002111111119");
        commonReqParam.setCharsetCode("utf-8");
        commonReqParam.setForwardUrl(forwardUrl);
        commonReqParam.setNotifyUrl(notifyUrl);
        commonReqParam.setServiceName("UNION_CREATE_ORDER");
        commonReqParam.setRequestTime(DateUtil.getDateTimeFormat(new Date()));
        commonReqParam.setSignType("MD5");
        commonReqParam.setVersion("1.0");

        MerUnionOrderPo merUnionOrderPo =new MerUnionOrderPo();
        merUnionOrderPo.setProductNo(tbOrderPayVo.getPayWay());
        merUnionOrderPo.setDiscountAmt("0.00");
        merUnionOrderPo.setOrderAmt(tbOrder.getActOrderAmt().toString());
        merUnionOrderPo.setPayAmt(tbOrder.getActOrderAmt().toString());
        merUnionOrderPo.setEquipIp(tbOrderPayVo.getEquipIp());
        merUnionOrderPo.setEquipType("WEB");
        merUnionOrderPo.setGoodsName(tbOrder.getOrderDesc());
        merUnionOrderPo.setUserNo(tbOrderPayVo.getUserNo());
        String paySeqNo=IDUtils.genIdStr("P");
        MerPaySeqPo merPaySeqPo=new MerPaySeqPo();
        merPaySeqPo.setMerOrderNo(tbOrder.getOrderNo());
        merPaySeqPo.setMerPaySeq(paySeqNo);
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
            TbPayLog tbPayLog=new TbPayLog();
            tbPayLog.setOrderNo(tbOrder.getOrderNo());
            tbPayLog.setPaySeqNo(paySeqNo);
            tbPayLog.setPayWay(tbOrderPayVo.getPayWay());
            tbPayLog.setPayTitle(tbOrder.getOrderDesc());
            tbPayLog.setUserNo(tbOrderPayVo.getUserNo());
            tbPayLog.setCreateTime(new Date());
            tbPayLog.setModiTime(new Date());
            tbPayLogResp.save(tbPayLog);
            return OrderResult.newSuccess(respJson.get("data"));
        }else{
            return OrderResult.newError(retCode,retMsg);
        }
    }

    @Override
    public OrderResult notifyOrder(TbOrderPayNotifyVo tbOrderPayNotifyVo) {
        TbPayLog oldTbPayLog= tbPayLogResp.findByOrderNoAndPaySeqNo(tbOrderPayNotifyVo.getOrderNo(),tbOrderPayNotifyVo.getPaySeqNo());
        if(oldTbPayLog==null || PayStatusEnum.WAIT_PAY.getCode()!=oldTbPayLog.getPayStatus()){
            return OrderResult.newError(ResultCode.PAY_CANT_PROCESS);
        }
        //修改支付记录
        int payStatus=PayStatusEnum.WAIT_PAY.getCode();
        int orderStatus= OrderStatusEnum.WAIT_PAY.getCode();
        if("PAY_SUCCESS".equals(tbOrderPayNotifyVo.getPayResult())){
            payStatus=PayStatusEnum.PAY_SUCCESS.getCode();
            orderStatus=OrderStatusEnum.WAIT_POST.getCode();
        }else if("PAY_FAIL".equals(tbOrderPayNotifyVo.getPayResult())){
            payStatus=PayStatusEnum.PAY_FAIL.getCode();
        }
        oldTbPayLog.setPayStatus(payStatus);
        oldTbPayLog.setPayTime(DateUtil.formatDate(tbOrderPayNotifyVo.getPaySuccessTime(),"yyyy-MM-dd hh:mm:ss"));
        oldTbPayLog.setRespCode(tbOrderPayNotifyVo.getRespCode());
        oldTbPayLog.setRespMsg(tbOrderPayNotifyVo.getRespMsg());
        oldTbPayLog.setPayTradeNo(tbOrderPayNotifyVo.getPayTradeNo());
        tbPayLogResp.save(oldTbPayLog);
        //修改订单状态
        tbOrderResp.updateStatus(oldTbPayLog.getOrderNo(),orderStatus,new Date());
        return OrderResult.newSuccess();
    }

    @Override
    public OrderResult findOrder(TbOrderQueryVo tbOrderQueryVo) {
        Pageable pageable = new PageRequest(tbOrderQueryVo.getCurrentPage(), tbOrderQueryVo.getPageSize(), Sort.Direction.ASC, "id");
        Page<TbOrder> tbOrderPage=tbOrderResp.findAll(new Specification<TbOrder>(){
            @Override
            public Predicate toPredicate(Root<TbOrder> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                    list.add(criteriaBuilder.equal(root.get("userNo").as(String.class), tbOrderQueryVo.getUserNo()));
                if(null!=tbOrderQueryVo.getOrderStatus()&&!"".equals(tbOrderQueryVo.getOrderStatus())){
                    list.add(criteriaBuilder.equal(root.get("orderStatus").as(Integer.class), tbOrderQueryVo.getOrderStatus()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable);
        return OrderResult.newSuccess(tbOrderPage);
    }
}
