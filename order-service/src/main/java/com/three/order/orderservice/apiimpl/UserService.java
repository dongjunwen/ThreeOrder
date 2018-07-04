package com.three.order.orderservice.apiimpl;

import com.three.order.orderapi.api.IUserService;
import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.TbUserLoginVo;
import com.three.order.orderapi.vo.TbUserResultVo;
import com.three.order.orderapi.vo.TbUserVo;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/7/4 11:11
 * @Descripton:用户服务类
 * @Modify :
 **/
@Service
public class UserService implements IUserService {
    @Override
    public OrderResult<Integer> createUser(TbUserVo tbUserVo) {
        return null;
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
