package com.three.order.orderservice.apiimpl;

import com.three.order.orderapi.api.IUserService;
import com.three.order.orderapi.enums.ResultCode;
import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.TbUserLoginVo;
import com.three.order.orderapi.vo.TbUserResultVo;
import com.three.order.orderapi.vo.TbUserVo;
import com.three.order.ordercommon.constant.CommonConstants;
import com.three.order.ordercommon.enums.StatusEnum;
import com.three.order.ordercommon.utils.IDUtils;
import com.three.order.ordercommon.utils.MD5Util;
import com.three.order.orderjdbc.entity.TbUser;
import com.three.order.orderjdbc.respository.TbUserResp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * @Author:luiz
 * @Date: 2018/7/4 11:11
 * @Descripton:用户服务类
 * @Modify :
 **/
@Service
public class UserService implements IUserService {
    @Autowired
    TbUserResp tbUserResp;

    @Override
    public OrderResult<Integer> createUser(TbUserVo tbUserVo) {
        TbUser tbUser=new TbUser();
        BeanUtils.copyProperties(tbUserVo,tbUser);
        TbUser oldUser=tbUserResp.findIndex(tbUser.getEmailAddr(),tbUser.getPhoneNum(),tbUser.getNickName());
        if(oldUser!=null){
            return OrderResult.newError(ResultCode.USER_HAS_EXISTS);
        }
        String userNo=IDUtils.genIdStr("U");
        tbUser.setUserNo(userNo);
        java.util.Date nowDate=new java.util.Date();
        tbUser.setStatus(StatusEnum.YES.getCode());
        tbUser.setCreateTime(new java.sql.Timestamp(nowDate.getTime()));
        tbUser.setLoginPass(MD5Util.getMD5(tbUserVo.getPassword(), CommonConstants.USER_PASS_KEY));
        tbUserResp.save(tbUser);
        return OrderResult.newSuccess(1);
    }

    @Override
    public OrderResult<Integer> updateUser(TbUserVo tbUserVo) {
        return null;
    }

    @Override
    public OrderResult<TbUserResultVo> getUserById(String userNo) {
        return null;
    }

    @Override
    public OrderResult<TbUserResultVo> login(TbUserLoginVo tbUserLoginVo) {
        return null;
    }
}
