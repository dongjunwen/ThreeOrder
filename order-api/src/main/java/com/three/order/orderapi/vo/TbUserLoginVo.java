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
@ApiModel(value = "用户登录实体 TbUserLoginVo")
public class TbUserLoginVo implements Serializable {
    @NotBlank(message = "登录号不能为空")
    @ApiModelProperty(value = "登录号",required =true )
    private String loginNo;
    @NotBlank(message = "登录密码不能为空")
    @ApiModelProperty(value = "登录密码",required = true)
    private String loginPass;

}
