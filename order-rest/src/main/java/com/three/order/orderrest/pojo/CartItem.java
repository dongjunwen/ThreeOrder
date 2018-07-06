package com.three.order.orderrest.pojo;

import java.math.BigDecimal;

/**
 * @Author: luiz
 * @Date: Create in 2018/07/06 15:34
 * @Description: 购物车中的商品信息
 */
public class CartItem {

    private String itemNo;
    private String itemTitle;
    private BigDecimal num;
    private BigDecimal price;
    private String picUrl;

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
