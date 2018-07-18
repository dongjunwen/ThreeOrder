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
@ApiModel(value = "订单查询条件 TbOrderQueryVo")
public class TbOrderQueryVo {
    @ApiModelProperty(value = "用户编号",required =false )
    private String userNo;
    @ApiModelProperty(value = "订单状态",required =false )
    private int orderStatus;
    @ApiModelProperty(value = "当前页",required =false )
    private int currentPage=1;
    @ApiModelProperty(value = "每页显示条数",required =false )
    private int pageSize=10;
}
