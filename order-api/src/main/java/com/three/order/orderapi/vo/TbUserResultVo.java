package com.three.order.orderapi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @Date:2017/10/19 0019 15:26
 * @Author lu.dong
 * @Description：
 **/
@Data
@ApiModel(value = "用户展示实体 TbUserResultVo")
public class TbUserResultVo implements Serializable {
    @ApiModelProperty(value = "用户号",required = false)
    private String userNo;
    @ApiModelProperty(value = "昵称",required = false)
    private String nickName;
    @ApiModelProperty(value = "手机号",required = false)
    private String phoneNum;
    @ApiModelProperty(value = "邮箱地址",required = false)
    private String emailAddr;
    @ApiModelProperty(value = "备注",required = false)
    private String memo;

}
