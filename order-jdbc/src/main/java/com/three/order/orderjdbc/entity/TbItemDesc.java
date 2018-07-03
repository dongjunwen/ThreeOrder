package com.three.order.orderjdbc.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "tb_item_desc")
public class TbItemDesc {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String itemNo;
  private String itemDesc;
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


  public String getItemDesc() {
    return itemDesc;
  }

  public void setItemDesc(String itemDesc) {
    this.itemDesc = itemDesc;
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
