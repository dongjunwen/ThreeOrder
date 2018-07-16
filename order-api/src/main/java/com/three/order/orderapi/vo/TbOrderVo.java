package com.three.order.orderapi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    @NotNull(message = "订单金额不能为空")
    @ApiModelProperty(value = "订单金额",required =true )
    private String orderAmt;
    @ApiModelProperty(value = "优惠金额",required =true )
    private String coupAmt;
    @ApiModelProperty(value = "运输费",required =true )
    private String transAmt;
    @ApiModelProperty(value = "用户编号",required =true )
    private String userNo;
    @NotNull(message = "店铺号不能为空")
    @ApiModelProperty(value = "店铺号",required =true )
    private String shopNo;
    @ApiModelProperty(value = "销售人员",required =true )
    private String sellerId;
    @ApiModelProperty(value = "订单描述",required =true )
    private String orderDesc;
    @NotNull(message = "商品列表不能为空")
    @ApiModelProperty(value = "商品列表",required =true )
    private List<TbItemVo> tbItemVoList;
    @ApiModelProperty(value = "收件人信息",required =true )
    private TbOrderShippingVo tbOrderShippingVo;
}
