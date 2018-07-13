package com.three.order.orderjdbc.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "tb_user")
@Data
public class TbUser extends BaseEntity{
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
  private String status;
  private String memo;
}
