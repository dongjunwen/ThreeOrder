package com.three.order.orderapi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author:luiz
 * @Date: 2018/7/16 11:30
 * @Descripton:
 * @Modify :
 **/
@Data
@ApiModel(value = "收件信息 TbOrderShippingVo")
public class TbOrderShippingVo {
    @ApiModelProperty(value = "收件人姓名")
    private String recvName;
    @ApiModelProperty(name = "收件人联系电话")
    private String recvPhone;
    @ApiModelProperty(name = "收件人手机号")
    private String recvMobile;
    @ApiModelProperty(name = "收件人省份")
    private String recvProvince;
    @ApiModelProperty(name = "收件人城市")
    private String recvCity;
    @ApiModelProperty(name = "收件人区县")
    private String recvDistrict;
    @ApiModelProperty(name = "收件人详细地址")
    private String recvAddress;
    @ApiModelProperty(name = "收件人邮编")
    private String recvZip;
}
