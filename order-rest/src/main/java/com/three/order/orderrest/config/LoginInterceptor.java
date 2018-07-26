package com.three.order.orderrest.config;

import com.alibaba.fastjson.JSONObject;
import com.three.order.orderapi.enums.ResultCode;
import com.three.order.orderapi.result.OrderResult;
import com.three.order.orderapi.vo.TbUserResultVo;
import com.three.order.orderrest.utils.TokenUtils;
import com.three.order.orderrest.utils.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by on 2018/7/26 0026.
 */
public class LoginInterceptor implements HandlerInterceptor{

    @Autowired
    private TokenUtils tokenUtils;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getParameter("tokenStr");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        if (StringUtils.isBlank(token)){
            //用户未登录
            response.getWriter().print(JSONObject.toJSON(OrderResult.newError(ResultCode.USER_NO_LOGGED_IN)));
            return false;
        }else {
            TbUserResultVo resultVo=tokenUtils.getUserByToken(token);
            if (resultVo ==null ){
                //登录失效
                response.getWriter().print(JSONObject.toJSON(OrderResult.newError(ResultCode.USER_LOGGED_FAILURE)));
                return false;
            }
            //应该验证账户和密码  TODO
            UserThreadLocal.set(resultVo);
        }
        return true;
    }
}
