package com.three.order.orderrest.controller;
import com.three.order.orderapi.api.IUserService;
import com.three.order.orderapi.enums.ResultCode;
import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.TbUserLoginVo;
import com.three.order.orderapi.vo.TbUserResultVo;
import com.three.order.orderapi.vo.TbUserVo;
import com.three.order.ordercommon.constant.CommonConstants;
import com.three.order.ordercommon.utils.IDUtils;
import com.three.order.orderrest.utils.RequestUtils;
import com.three.order.orderrest.utils.TokenUtils;
import com.three.order.orderrest.utils.UserThreadLocal;
import com.three.order.orderrest.validator.ValidatorUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Date:2017/10/19 0019 15:05
 * @Author lu.dong
 * @Description：
 **/
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户",description = "用户相关api")
@Slf4j
public class UserController {

    @Autowired
    IUserService iUserService;
    @Autowired
    TokenUtils tokenUtils;

    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ApiOperation(value="用户注册", notes="根据tbUserVo对象注册用户信息")
    @ApiParam(name = "tbUserVo", value = "用户操作实体 tbUserVo",required = true)
    public OrderResult<Integer> createUser( TbUserVo tbUserVo){
        try{
            if(StringUtils.isEmpty(tbUserVo.getEmailAddr())
                    &&StringUtils.isEmpty(tbUserVo.getPhoneNum())
                    &&StringUtils.isEmpty(tbUserVo.getNickName())
                    ){
                return OrderResult.newError(ResultCode.USER_REGIST_CHECK_ERROR);
            }

            OrderResult<Integer> _result=iUserService.createUser(tbUserVo);
            if(!_result.isSuccess()){
                return OrderResult.newError(_result.getRetCode(),_result.getRetMsg());
            }
            return  _result;
        }catch (Exception e){
            log.error("注册账号异常!{}",e);
            return OrderResult.newError(ResultCode.FAIL);
        }
    }


    @RequestMapping(value = "updateUser",method = RequestMethod.POST)
    @ApiOperation(value="修改用户", notes="根据tbUserVo对象修改用户")
    @ApiParam(name = "tbUserVo", value = "用户操作实体 tbUserVo",required = true)
    public OrderResult<String> updateUser(TbUserVo tbUserVo) {
        try {
            OrderResult<Integer> _result = iUserService.updateUser(tbUserVo);
            if (!_result.isSuccess()) {
                return OrderResult.newError(_result.getRetCode(), _result.getRetMsg());
            }
            return OrderResult.newSuccess("修改账号成功");
        } catch (Exception e) {
            log.error("用户修改异常!{}", e);
            return OrderResult.newError(ResultCode.FAIL);
        }
    }
    @RequestMapping(value = "/getCurrentUser",method = RequestMethod.GET)
    @ApiOperation(value="根据request当前登录信息", notes="根据request当前登录信息")
    public OrderResult<TbUserResultVo> getCurrentUser(){
        return OrderResult.newSuccess(UserThreadLocal.get());

    }

    @RequestMapping(value = "/getCurrentUserByToken/{tokenStr}",method = RequestMethod.GET)
    @ApiOperation(value="根据Token当前登录信息", notes="根据Token当前登录信息")
    @ApiImplicitParam(name = "tokenStr", value = "token值", required = true, dataType = "string",paramType = "path")
    public OrderResult<TbUserResultVo> getCurrentUserByToken(@PathVariable("tokenStr") String tokenStr){
        return OrderResult.newSuccess(UserThreadLocal.get());

    }

    @RequestMapping(value = "/{userNo}",method = RequestMethod.GET)
    @ApiOperation(value="获取用户详细信息", notes="根据url的用户编号来获取用户详细信息")
    @ApiImplicitParam(name = "userNo", value = "用户编号", required = true, dataType = "string",paramType = "path")
    public OrderResult<TbUserResultVo> getUserById(@PathVariable("userNo")String userNo){
        return iUserService.getUserById(userNo);
    }

    @ApiOperation(value = "登录",notes = "登录接口")
    @PostMapping(value = "login")
    @ApiParam(name = "tbUserLoginVo", value = "用户操作实体 tbUserLoginVo",required = true)
    public OrderResult<String> login(TbUserLoginVo tbUserLoginVo, HttpServletRequest request){
        try {
            String tokenStr= IDUtils.genIdStr("T");
            OrderResult<TbUserResultVo> orderResult=iUserService.login(tbUserLoginVo);
            if(!orderResult.isSuccess()){
                return OrderResult.newError(orderResult.getRetCode(),orderResult.getRetMsg());
            }
            TbUserResultVo tbUserResultVo=orderResult.getData();
            tokenUtils.putUser(tokenStr,tbUserResultVo);
            log.info("账号:{}登录成功",tbUserLoginVo.getLoginNo());
            return OrderResult.newSuccess(tokenStr);
        }catch(Exception e){
            return OrderResult.newError(ResultCode.USERNAME_OR_PASS_ERR);
        }
    }

    @ApiOperation(value = "退出登录")
    @GetMapping(value="logout")
    public OrderResult<String> logout(HttpServletRequest request){
        String token=request.getParameter("tokenStr");
        return tokenUtils.delUser(token)?OrderResult.newSuccess("已退出!"):OrderResult.newSuccess("退出失败!");
    }


}
