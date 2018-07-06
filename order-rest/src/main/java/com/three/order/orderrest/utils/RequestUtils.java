package com.three.order.orderrest.utils;

import com.three.order.orderapi.vo.TbUserResultVo;
import com.three.order.ordercommon.constant.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author:luiz
 * @Date: 2018/7/6 14:11
 * @Descripton:
 * @Modify :
 **/
public class RequestUtils {

    /**
     * 根据request获取当前登录账号
     * @param request
     * @return
     */
    public static TbUserResultVo getCurrentUser(HttpServletRequest request){
        HttpSession session= request.getSession(false);
        TbUserResultVo tbUserResultVo= (TbUserResultVo)session.getAttribute(CommonConstants.USER_SESSION_ATTR);
        return tbUserResultVo;
    }

}
