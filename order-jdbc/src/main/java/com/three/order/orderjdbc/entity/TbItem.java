package com.three.order.orderjdbc.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "tb_item")
public class TbItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "item_no")
  private String itemNo;
  @Column(name = "item_title")
  private String itemTitle;
  @Column(name = "sell_point")
  private String sellPoint;
  private double price;
  private double num;
  @Column(name = "bar_code")
  private String barcode;
  @Column(name = "pic_url")
  private String picUrl;
  @Column(name = "cat_no")
  private String catNo;
  private long status;
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


  public String getSellPoint() {
    return sellPoint;
  }

  public void setSellPoint(String sellPoint) {
    this.sellPoint = sellPoint;
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


  public String getBarcode() {
    return barcode;
  }

  public void setBarcode(String barcode) {
    this.barcode = barcode;
  }


  public String getPicUrl() {
    return picUrl;
  }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }


  public String getCatNo() {
    return catNo;
  }

  public void setCatNo(String catNo) {
    this.catNo = catNo;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
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
