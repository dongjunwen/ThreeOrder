package com.three.order.orderrest.utils;

import com.three.order.orderapi.vo.TbUserResultVo;

/**
 * Created by on 2018/7/26 0026.
 */
public class UserThreadLocal {
    private static ThreadLocal<TbUserResultVo> userTh=new ThreadLocal<>();

    public static TbUserResultVo get(){
        return userTh.get();
    }
    public static void set(TbUserResultVo resultVo){
        userTh.set(resultVo);
    }
}
