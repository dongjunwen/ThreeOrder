package com.three.order.orderapi.enums;



/**
 * Description:
 *
 * @author luiz
 * @version 1.0.0
 * @date: 2018-01-22 14:42
 */
public enum ResultCode {

    COMMON_PARAM_NULL("1001","参数为空"),
    COMMON_PARAM_INVALID("1002","参数不合法"),
    COMMON_DATA_EXISTS("1003", "数据已存在"),
    COMMON_DATA_NOT_EXISTS("1004", "数据不存在"),
    COMMON_QUERY_ERROR("1005", "数据执行SQL查询错误"),
    VALIDATE_ERROR("1006", "签名校验错误"),
    VALIDATE_TYPE_NOT_EXISTS("1007", "签名校验错误"),
    COMMON_DULIICATE_SUBMIT("1008", "重复提交"),

    //用户 1100-1199
    USER_REGIST_CHECK_ERROR("1100","手机号、邮箱、昵称不能全部为空!"),
    USER_HAS_EXISTS("1101","账号已存在!"),
    USER_NOT_EXISTS("1102","账号不存在!"),
    USER_LOGGED_FAILURE("1103","用户登录失效"),

    //订单 1200-1299
    ORDER_NOT_EXISTS("1200", "订单不存在"),
    PAY_CANT_PROCESS("1201", "支付结果无须再次处理"),


    SUCCESS("200","操作成功"),
    FAIL("500","系统异常"),
	USER_NO_LOGGED_IN("510","用户未登录"),
	FOR_UNAUTHORIZED("511","用户未授权"),
    USERNAME_OR_PASS_ERR("512","用户名或密码错误"), ;

    String code;
    String message;

    ResultCode(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    
    public static final ResultCode parse(String code){
    	for(ResultCode resultCode : ResultCode.values()){
    		if(code.equals(resultCode.getCode())) return resultCode;
    	}
    	return null;
    }
}
