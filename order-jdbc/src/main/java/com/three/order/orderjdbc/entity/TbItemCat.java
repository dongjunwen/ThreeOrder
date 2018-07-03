package com.three.order.orderjdbc.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "tb_item_cat")
public class TbItemCat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "cat_no")
  private String catNo;
  @Column(name = "pcat_no")
  private String pcatNo;
  @Column(name = "item_level")
  private long itemLevel;
  @Column(name = "item_sort")
  private long itemSort;
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


  public String getCatNo() {
    return catNo;
  }

  public void setCatNo(String catNo) {
    this.catNo = catNo;
  }


  public String getPcatNo() {
    return pcatNo;
  }

  public void setPcatNo(String pcatNo) {
    this.pcatNo = pcatNo;
  }


  public long getItemLevel() {
    return itemLevel;
  }

  public void setItemLevel(long itemLevel) {
    this.itemLevel = itemLevel;
  }


  public long getItemSort() {
    return itemSort;
  }

  public void setItemSort(long itemSort) {
    this.itemSort = itemSort;
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
