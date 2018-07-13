package com.three.order.orderjdbc.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;


@Entity
@Component
@Table(name = "tb_order")
@Data
public class TbOrder extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "order_no")
  private String orderNo;
  @Column(name = "order_time")
  private Date orderTime;
  @Column(name = "pay_time")
  private Date payTime;
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

}
