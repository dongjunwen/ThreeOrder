package com.three.order.ordercommon.enums;

/**
 * @Author:luiz
 * @Date: 2018/5/28 10:14
 * @Descripton:
 * @Modify :
 **/
public enum PayStatusEnum {
    WAIT_PAY(0,"等待付款"),
    PAY_SUCCESS(1,"付款成功"),
    PAY_FAIL(2,"付款失败");

    private int code;
    private String name;

    PayStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static final PayStatusEnum parse(int code){
        for(PayStatusEnum statusEnum : PayStatusEnum.values()){
            if(code==statusEnum.getCode()) return statusEnum;
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
