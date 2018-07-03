package com.three.order.orderapi.result;

import com.three.order.orderapi.enums.ResultCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author:luiz
 * @Date: 2018/1/22 13:36
 * @Descripton:风控返回结果
 * @Modify :
 **/
@Setter
@Getter
@ToString
public class OrderResult<T> implements Serializable {

    private static final long serialVersionUID = -62345784215837198L;
    /**
     * 接口响应返回码
     *  200：成功 则data域不为空，并且含有下面两个字段
     *  dataStatus 业务状态
     *  dataMsg	业务状态消息
     *  其他失败 失败则data域为空
     */
    private String retCode;
    /**
     * 接口响应返回消息
     */
    private String retMsg;
    /**
     * 接口响应返回码
     *  true：成功 则data域不为空，并且含有下面两个字段
     *  dataStatus 业务状态
     *  dataMsg	业务状态消息
     *  false:失败 失败则data域为空
     */
    private boolean success;
    /**
     * 具体交易数据内容 JSON 格式
     */
    private T data;

    public static <T> OrderResult<T> newSuccess(){
        return newSuccess(ResultCode.SUCCESS,null);
    }

    public static <T> OrderResult<T> newSuccess(T data){
        return newSuccess(ResultCode.SUCCESS,data);
    }

    public static <T> OrderResult<T> newSuccess(ResultCode resultCode, T data){
        return new OrderResult(resultCode.getCode(),resultCode.getMessage(),true,data);
    }

    public static <T> OrderResult<T> newError(ResultCode resultCode){
        return new OrderResult<T>(resultCode.getCode(),resultCode.getMessage(),false,null);
    }
    public static <T> OrderResult<T> newError(String code, String msg){
        return new OrderResult<T>(code,msg,false,null);
    }

    public OrderResult<T> setErrorCode(ResultCode resultCode){
        this.success = false;
        this.retCode = resultCode.getCode();
        this.retMsg = resultCode.getMessage();
        return this;
    }

    public OrderResult<T> setError(String code, String msg){
        this.success = false;
        this.retCode = code;
        this.retMsg = msg;
        return this;
    }

    public OrderResult(String code, String message, boolean success, T data) {
        this.retCode = code;
        this.retMsg = message;
        this.data = data;
        this.success = success;
    }

}
