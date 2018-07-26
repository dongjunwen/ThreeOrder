package com.three.order.orderrest.utils;

import com.three.order.orderapi.vo.TbUserResultVo;
import com.three.order.ordercommon.constant.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author:luiz
 * @Date: 2018/7/6 15:05
 * @Descripton:
 * @Modify :
 **/
@Component
public class TokenUtils {
    @Autowired
    RedisTemplate redisTemplate;
    public  TbUserResultVo getUserByToken(String tokenStr){
        return (TbUserResultVo)redisTemplate.opsForValue().get(CommonConstants.USER_TOKEN+tokenStr);
    }

    public  void putUser(String tokenStr,TbUserResultVo tbUserResultVo){
         redisTemplate.opsForValue().setIfAbsent(CommonConstants.USER_TOKEN+tokenStr,tbUserResultVo);
    }

    public  Boolean delUser(String tokenStr){
        return redisTemplate.delete(CommonConstants.USER_TOKEN+tokenStr);
    }
}
