package com.three.order.orderjdbc.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "tb_order_shipping")
@Data
public class TbOrderShipping extends BaseEntity{
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

}
