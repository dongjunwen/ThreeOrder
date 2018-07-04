package com.three.order.orderapi.api;

import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.TbUserLoginVo;
import com.three.order.orderapi.vo.TbUserResultVo;
import com.three.order.orderapi.vo.TbUserVo; /**
 * @Author:luiz
 * @Date: 2018/7/4 11:02
 * @Descripton:用户服务接口
 * @Modify :
 **/
public interface IUserService {
    OrderResult<Integer> createUser(TbUserVo tbUserVo);

    OrderResult<Integer> updateUser(TbUserVo tbUserVo);

    OrderResult<TbUserResultVo> getUserById(String userNo);

    OrderResult<TbUserResultVo> login(TbUserLoginVo tbUserLoginVo);
}
