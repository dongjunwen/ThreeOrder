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
@ApiModel(value = "订单支付信息 TbOrderPayVo")
public class TbOrderPayVo {
    @NotNull(message = "订单号不能为空")
    @ApiModelProperty(value = "订单号",required =true )
    private String orderNo;
    @ApiModelProperty(value = "用户号",required =false )
    private String userNo;
    @ApiModelProperty(value = "设备ip",required =false )
    private String equipIp;
    @NotNull(message = "支付方式不能为空")
    @ApiModelProperty(value = "支付方式",required =true )
    private String payWay;
}
