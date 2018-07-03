package com.three.order.orderjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "tb_cat_param")
public class TbCatParam {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "cat_no")
  private String catNo;
  @Column(name = "cat_param")
  private String catParam;
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


  public String getCatNo() {
    return catNo;
  }

  public void setCatNo(String catNo) {
    this.catNo = catNo;
  }


  public String getCatParam() {
    return catParam;
  }

  public void setCatParam(String catParam) {
    this.catParam = catParam;
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
