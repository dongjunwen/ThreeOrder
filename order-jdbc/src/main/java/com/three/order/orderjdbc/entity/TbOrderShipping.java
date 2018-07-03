package com.three.order.orderjdbc.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "tb_order_shipping")
public class TbOrderShipping {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "order_no")
  private String orderNo;
  @Column(name = "recv_name")
  private String recvName;
  @Column(name = "recv_phone")
  private String recvPhone;
  @Column(name = "recv_mobile")
  private String recvMobile;
  @Column(name = "recv_province")
  private String recvProvince;
  @Column(name = "recv_city")
  private String recvCity;
  @Column(name = "recv_district")
  private String recvDistrict;
  @Column(name = "recv_address")
  private String recvAddress;
  @Column(name = "recv_zip")
  private String recvZip;
  private String status;
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


  public String getRecvName() {
    return recvName;
  }

  public void setRecvName(String recvName) {
    this.recvName = recvName;
  }


  public String getRecvPhone() {
    return recvPhone;
  }

  public void setRecvPhone(String recvPhone) {
    this.recvPhone = recvPhone;
  }


  public String getRecvMobile() {
    return recvMobile;
  }

  public void setRecvMobile(String recvMobile) {
    this.recvMobile = recvMobile;
  }


  public String getRecvProvince() {
    return recvProvince;
  }

  public void setRecvProvince(String recvProvince) {
    this.recvProvince = recvProvince;
  }


  public String getRecvCity() {
    return recvCity;
  }

  public void setRecvCity(String recvCity) {
    this.recvCity = recvCity;
  }


  public String getRecvDistrict() {
    return recvDistrict;
  }

  public void setRecvDistrict(String recvDistrict) {
    this.recvDistrict = recvDistrict;
  }


  public String getRecvAddress() {
    return recvAddress;
  }

  public void setRecvAddress(String recvAddress) {
    this.recvAddress = recvAddress;
  }


  public String getRecvZip() {
    return recvZip;
  }

  public void setRecvZip(String recvZip) {
    this.recvZip = recvZip;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
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
