package com.three.order.orderservice.apiimpl;

import com.three.order.orderapi.api.IItemService;
import com.three.order.orderapi.enums.ResultCode;
import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.TbItemDescVo;
import com.three.order.orderapi.vo.TbItemParamVo;
import com.three.order.orderapi.vo.TbItemVo;
import com.three.order.orderjdbc.entity.TbItem;
import com.three.order.orderjdbc.entity.TbItemDesc;
import com.three.order.orderjdbc.entity.TbItemParam;
import com.three.order.orderjdbc.respository.TbItemDescResp;
import com.three.order.orderjdbc.respository.TbItemParamResp;
import com.three.order.orderjdbc.respository.TbItemResp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/7/3 19:19
 * @Descripton:
 * @Modify :商品服务类
 **/
@Service
public class ItemService implements IItemService {

    @Autowired
    private TbItemResp tbItemResp;

    @Autowired
    private TbItemDescResp tbItemDescResp;

    @Autowired
    private TbItemParamResp tbItemParamResp;

    @Override
    public OrderResult getItemBase(String itemNo) {
        TbItem tbItem=tbItemResp.findByItemNo(itemNo);
        if (tbItem==null){
            return OrderResult.newError(ResultCode.COMMON_DATA_NOT_EXISTS);
        }
        TbItemVo tbItemVo=new TbItemVo();
        BeanUtils.copyProperties(tbItem,tbItemVo);
        return  OrderResult.newSuccess(tbItemVo);
    }

    @Override
    public OrderResult getItemDesc(String itemNo) {
        TbItemDesc tbItemDesc=tbItemDescResp.findItemDescByItemNo(itemNo);
        if (tbItemDesc==null){
           return OrderResult.newError(ResultCode.COMMON_DATA_NOT_EXISTS);
        }
        TbItemDescVo tbItemVo=new TbItemDescVo();
        BeanUtils.copyProperties(tbItemDesc,tbItemVo);
        return  OrderResult.newSuccess(tbItemVo);
    }

    @Override
    public OrderResult getItemParam(String itemNo) {

        TbItemParam tbItemDesc=tbItemParamResp.findItemParamByItemNo(itemNo);
        if (tbItemDesc==null){
            return OrderResult.newError(ResultCode.COMMON_DATA_NOT_EXISTS);
        }
        TbItemParamVo tbItemVo=new TbItemParamVo();
        BeanUtils.copyProperties(tbItemDesc,tbItemVo);
        return  OrderResult.newSuccess(tbItemVo);
    }
}
