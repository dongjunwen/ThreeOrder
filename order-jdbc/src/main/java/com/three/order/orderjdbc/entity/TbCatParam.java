package com.three.order.orderjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

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
  private Date createTime;
  @Column(name = "modi_time")
  private Date modiTime;

}
