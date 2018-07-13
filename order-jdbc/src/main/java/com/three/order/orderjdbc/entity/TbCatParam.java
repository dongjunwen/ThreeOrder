package com.three.order.orderjdbc.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_cat_param")
@Data
public class TbCatParam extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "cat_no")
  private String catNo;
  @Column(name = "cat_param")
  private String catParam;


}
