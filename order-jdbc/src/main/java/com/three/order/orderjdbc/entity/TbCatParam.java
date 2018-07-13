package com.three.order.orderjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "tb_cat_param")
public class TbCatParam extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "cat_no")
  private String catNo;
  @Column(name = "cat_param")
  private String catParam;


}
