package com.three.order.orderrest.controller;
import com.three.order.orderapi.api.IUserService;
import com.three.order.orderapi.enums.ResultCode;
import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.TbUserLoginVo;
import com.three.order.orderapi.vo.TbUserResultVo;
import com.three.order.orderapi.vo.TbUserVo;
import com.three.order.ordercommon.utils.IDUtils;
import com.three.order.orderrest.validator.ValidatorUtil;
import io.swagger.annotations.*;
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
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService iUserService;

    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ApiOperation(value="用户注册", notes="根据tbUserVo对象注册用户信息")
    @ApiParam(name = "tbUserVo", value = "用户操作实体 tbUserVo",required = true)
    public OrderResult<Integer> createUser(@RequestBody TbUserVo tbUserVo){
        ValidatorUtil.validateEntity(tbUserVo);//校验用户实体字段，
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
            logger.error("注册账号异常!{}",e);
            return OrderResult.newError(ResultCode.FAIL);
        }
    }


    @RequestMapping(value = "updateUser",method = RequestMethod.PUT)
    @ApiOperation(value="修改用户", notes="根据tbUserVo对象修改用户")
    @ApiParam(name = "tbUserVo", value = "用户操作实体 tbUserVo",required = true)
    public OrderResult<String> updateUser(@RequestBody TbUserVo tbUserVo) {
        ValidatorUtil.validateEntity(tbUserVo);//校验用户实体字段，
        try {
            OrderResult<Integer> _result = iUserService.updateUser(tbUserVo);
            if (!_result.isSuccess()) {
                return OrderResult.newError(_result.getRetCode(), _result.getRetMsg());
            }
            return OrderResult.newSuccess("修改账号成功");
        } catch (Exception e) {
            logger.error("用户修改异常!{}", e);
            return OrderResult.newError(ResultCode.FAIL);
        }
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
    public OrderResult<String> login(@RequestBody TbUserLoginVo tbUserLoginVo, HttpServletRequest request){
        //模拟登录
        ValidatorUtil.validateEntity(tbUserLoginVo);//校验用户实体字段，
        try {
            String tokenStr= IDUtils.genIdStr("T");
            OrderResult<TbUserResultVo> orderResult=iUserService.login(tbUserLoginVo);
            TbUserResultVo tbUserResultVo=orderResult.getData();
            HttpSession session= request.getSession(true);
            session.setAttribute("TOKEN",tokenStr);
            session.setAttribute(tokenStr,tbUserResultVo);
            session.setAttribute(session.getId(),tbUserResultVo);
            logger.info("账号:{}登录成功",tbUserLoginVo);
            return OrderResult.newSuccess(tbUserLoginVo.getLoginNo()+"登录成功!");
        }catch(Exception e){
            return OrderResult.newError(ResultCode.USERNAME_OR_PASS_ERR);
        }
    }

    @ApiOperation(value = "退出登录")
    @GetMapping(value="logout")
    public OrderResult<String> logout(HttpServletRequest request){
        HttpSession session= request.getSession(false);
         String tokenStr=(String) session.getAttribute("TOKEN");
        session.removeAttribute("TOKEN");
        session.removeAttribute(tokenStr);
        session.removeAttribute(session.getId());
        return OrderResult.newSuccess("已退出!");
    }


}
