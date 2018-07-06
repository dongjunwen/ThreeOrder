package com.three.order.orderapi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author:luiz
 * @Date: 2018/7/6 15:38
 * @Descripton:
 * @Modify :
 **/
@Data
@ApiModel(value = "购物车商品 CartItemVo")
public class CartItemVo {
    @NotNull(message = "商品编号")
    @ApiModelProperty(value = "商品编号",required =true )
    private String itemNo;
    @ApiModelProperty(value = "商品数目",required =true )
    private BigDecimal itemNum;

}
