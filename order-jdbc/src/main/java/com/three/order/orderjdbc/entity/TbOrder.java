package com.three.order.orderjdbc.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "tb_order")
public class TbOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "order_no")
  private String orderNo;
  @Column(name = "order_time")
  private java.sql.Timestamp orderTime;
  @Column(name = "pay_time")
  private java.sql.Timestamp payTime;
  @Column(name = "pay_type")
  private long payType;
  @Column(name = "order_status")
  private long orderStatus;
  @Column(name = "order_amt")
  private double orderAmt;
  @Column(name = "coup_amt")
  private double coupAmt;
  @Column(name = "trans_amt")
  private double transAmt;
  @Column(name = "act_order_amt")
  private double actOrderAmt;
  @Column(name = "order_rate")
  private double orderRate;
  @Column(name = "act_tax_amt")
  private double actTaxAmt;
  @Column(name = "pay_amt")
  private double payAmt;
  @Column(name = "buyler_id")
  private String buylerId;
  @Column(name = "shop_no")
  private String shopNo;
  @Column(name = "seller_id")
  private String sellerId;
  @Column(name = "order_desc")
  private String orderDesc;
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


  public java.sql.Timestamp getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(java.sql.Timestamp orderTime) {
    this.orderTime = orderTime;
  }


  public java.sql.Timestamp getPayTime() {
    return payTime;
  }

  public void setPayTime(java.sql.Timestamp payTime) {
    this.payTime = payTime;
  }


  public long getPayType() {
    return payType;
  }

  public void setPayType(long payType) {
    this.payType = payType;
  }


  public long getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(long orderStatus) {
    this.orderStatus = orderStatus;
  }


  public double getOrderAmt() {
    return orderAmt;
  }

  public void setOrderAmt(double orderAmt) {
    this.orderAmt = orderAmt;
  }


  public double getCoupAmt() {
    return coupAmt;
  }

  public void setCoupAmt(double coupAmt) {
    this.coupAmt = coupAmt;
  }


  public double getTransAmt() {
    return transAmt;
  }

  public void setTransAmt(double transAmt) {
    this.transAmt = transAmt;
  }


  public double getActOrderAmt() {
    return actOrderAmt;
  }

  public void setActOrderAmt(double actOrderAmt) {
    this.actOrderAmt = actOrderAmt;
  }


  public double getOrderRate() {
    return orderRate;
  }

  public void setOrderRate(double orderRate) {
    this.orderRate = orderRate;
  }


  public double getActTaxAmt() {
    return actTaxAmt;
  }

  public void setActTaxAmt(double actTaxAmt) {
    this.actTaxAmt = actTaxAmt;
  }


  public double getPayAmt() {
    return payAmt;
  }

  public void setPayAmt(double payAmt) {
    this.payAmt = payAmt;
  }


  public String getBuylerId() {
    return buylerId;
  }

  public void setBuylerId(String buylerId) {
    this.buylerId = buylerId;
  }


  public String getShopNo() {
    return shopNo;
  }

  public void setShopNo(String shopNo) {
    this.shopNo = shopNo;
  }


  public String getSellerId() {
    return sellerId;
  }

  public void setSellerId(String sellerId) {
    this.sellerId = sellerId;
  }


  public String getOrderDesc() {
    return orderDesc;
  }

  public void setOrderDesc(String orderDesc) {
    this.orderDesc = orderDesc;
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
