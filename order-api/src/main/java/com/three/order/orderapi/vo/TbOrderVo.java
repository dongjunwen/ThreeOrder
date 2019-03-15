package com.three.order.orderapi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/7/16 10:54
 * @Descripton:
 * @Modify :
 **/
@Data
@ApiModel(value = "订单信息 TbOrderVo")
public class TbOrderVo {
    @ApiModelProperty(value = "付款类型 付款方式 0:货到付款 1:在线支付 2:公司转账",required =false )
    private String payType="1";
    @NotNull(message = "订单金额不能为空")
    @DecimalMin(value = "0.01")
    @ApiModelProperty(value = "订单金额",required =true )
    private String orderAmt="0.00";
    @ApiModelProperty(value = "优惠金额",required =false )
    private String coupAmt="0.00";
    @ApiModelProperty(value = "运输费",required =false )
    private String transAmt="0.00";
    @ApiModelProperty(value = "用户编号",required =false )
    private String userNo;
    @ApiModelProperty(value = "店铺号",required =false )
    private String shopNo="001";
    @ApiModelProperty(value = "销售人员",required =false )
    private String sellerId;
    @ApiModelProperty(value = "订单描述",required =false )
    private String orderDesc;
    @ApiModelProperty(value = "商品列表",required =false )
    private List<TbItemVo> tbItemVoList;
    @ApiModelProperty(value = "收件人信息",required =false )
    private TbOrderShippingVo tbOrderShippingVo;
}
