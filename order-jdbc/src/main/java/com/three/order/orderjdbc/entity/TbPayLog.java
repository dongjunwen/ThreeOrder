package com.three.order.orderjdbc.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_pay_log")
@Data
public class TbPayLog extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "order_no")
  private String orderNo;
  @Column(name = "pay_seq_no")
  private String paySeqNo;
  @Column(name = "pay_time")
  private Date payTime;
  @Column(name = "pay_way")
  private String payWay;
  @Column(name = "pay_status")
  private long payStatus;
  @Column(name = "user_no")
  private String userNo;
  @Column(name = "pay_title")
  private String payTitle;
  @Column(name = "resp_code")
  private String respCode;
  @Column(name = "resp_msg")
  private String respMsg;
  @Column(name = "pay_trade_no")
  private String payTradeNo;
}
