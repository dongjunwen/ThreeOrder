package com.three.order.orderapi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author:luiz
 * @Date: 2018/7/16 10:54
 * @Descripton:
 * @Modify :
 **/
@Data
@ApiModel(value = "订单支付通知信息 TbOrderPayNotifyVo")
public class TbOrderPayNotifyVo {
    @NotNull(message = "订单号不能为空")
    @ApiModelProperty(value = "订单号",required =true )
    private String orderNo;
    @NotNull(message = "支付流水号不能为空")
    @ApiModelProperty(value = "支付流水号",required =true )
    private String paySeqNo;
    @NotNull(message = "响应码不能为空")
    @ApiModelProperty(value = "响应码",required =true )
    private String respCode;
    @NotNull(message = "响应内容不能为空")
    @ApiModelProperty(value = "响应内容",required =true )
    private String respMsg;
    @ApiModelProperty(value = "支付系统交易号",required =false )
    private String payTradeNo;
    @ApiModelProperty(value = "支付结果",required =false )
    private String payResult;
    @ApiModelProperty(value = "付款成功时间",required =false )
    private String paySuccessTime;
    @ApiModelProperty(value = "签名字符串",required =true )
    private String signValue;
}
