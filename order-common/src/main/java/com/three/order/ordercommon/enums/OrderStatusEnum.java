package com.three.order.ordercommon.enums;

/**
 * @Author:luiz
 * @Date: 2018/5/28 10:14
 * @Descripton:
 * @Modify :
 **/
public enum OrderStatusEnum {
    WAIT_PAY(0,"等待付款"),
    WAIT_POST(1,"付款成功,等待发货"),
    HAS_RECV(2,"已收货");

    private int code;
    private String name;

    OrderStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static final OrderStatusEnum parse(int code){
        for(OrderStatusEnum statusEnum : OrderStatusEnum.values()){
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
