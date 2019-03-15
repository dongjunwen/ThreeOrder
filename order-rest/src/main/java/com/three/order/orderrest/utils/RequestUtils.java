package com.three.order.orderrest.utils;

import com.three.order.orderapi.vo.TbUserResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:luiz
 * @Date: 2018/7/6 14:11
 * @Descripton:
 * @Modify :
 **/
@Component
public class RequestUtils {
    @Autowired
    TokenUtils tokenUtils;

    /**
     * 根据request获取当前登录账号
     * @param request
     * @return
     */
    public  TbUserResultVo getCurrentUser(HttpServletRequest request){
        String tokenStr=request.getHeader("Authorization");
        if(tokenStr==null) return null;
        TbUserResultVo tbUserResultVo= tokenUtils.getUserByToken(tokenStr);
        return tbUserResultVo;
    }

    /**
     * 当前用户是否登录
     * @param request
     * @return
     */
    public  boolean isLogin(HttpServletRequest request){
        if(getCurrentUser(request)==null){
            return false;
        }
        return true;
    }

}
