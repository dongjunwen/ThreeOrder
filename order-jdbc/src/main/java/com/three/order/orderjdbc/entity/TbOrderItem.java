package com.three.order.orderjdbc.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "tb_order_item")
public class TbOrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "order_no")
  private String orderNo;
  @Column(name = "item_no")
  private String itemNo;
  @Column(name = "item_name")
  private String itemName;
  @Column(name = "item_pic_url")
  private String itemPicUrl;
  private double price;
  private double num;
  private double amt;
  @Column(name = "create_time")
  private java.sql.Timestamp createTime;
  @Column(name = "modi_time")
  private java.sql.Timestamp modiTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }


  public String getItemNo() {
    return itemNo;
  }

  public void setItemNo(String itemNo) {
    this.itemNo = itemNo;
  }


  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }


  public String getItemPicUrl() {
    return itemPicUrl;
  }

  public void setItemPicUrl(String itemPicUrl) {
    this.itemPicUrl = itemPicUrl;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public double getNum() {
    return num;
  }

  public void setNum(double num) {
    this.num = num;
  }


  public double getAmt() {
    return amt;
  }

  public void setAmt(double amt) {
    this.amt = amt;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getModiTime() {
    return modiTime;
  }

  public void setModiTime(java.sql.Timestamp modiTime) {
    this.modiTime = modiTime;
  }

}
