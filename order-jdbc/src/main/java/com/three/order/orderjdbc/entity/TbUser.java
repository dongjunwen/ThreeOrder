package com.three.order.orderjdbc.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "tb_user")
public class TbUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "user_no")
  private String userNo;
  @Column(name = "user_name")
  private String userName;
  @Column(name = "nick_name")
  private String nickName;
  @Column(name = "phone_num")
  private String phoneNum;
  @Column(name = "email_addr")
  private String emailAddr;
  @Column(name = "login_pass")
  private String loginPass;
  @Column(name = "last_login_time")
  private java.sql.Timestamp lastLoginTime;
  private String status;
  private String memo;
  @Column(name = "create_no")
  private String createNo;
  @Column(name = "modi_no")
  private String modiNo;
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


  public String getUserNo() {
    return userNo;
  }

  public void setUserNo(String userNo) {
    this.userNo = userNo;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }


  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }


  public String getEmailAddr() {
    return emailAddr;
  }

  public void setEmailAddr(String emailAddr) {
    this.emailAddr = emailAddr;
  }


  public String getLoginPass() {
    return loginPass;
  }

  public void setLoginPass(String loginPass) {
    this.loginPass = loginPass;
  }


  public java.sql.Timestamp getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(java.sql.Timestamp lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }


  public String getCreateNo() {
    return createNo;
  }

  public void setCreateNo(String createNo) {
    this.createNo = createNo;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public String getModiNo() {
    return modiNo;
  }

  public void setModiNo(String modiNo) {
    this.modiNo = modiNo;
  }


  public java.sql.Timestamp getModiTime() {
    return modiTime;
  }

  public void setModiTime(java.sql.Timestamp modiTime) {
    this.modiTime = modiTime;
  }

}
