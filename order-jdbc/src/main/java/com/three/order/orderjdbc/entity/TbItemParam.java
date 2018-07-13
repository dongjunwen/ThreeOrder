package com.three.order.orderjdbc.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "tb_item_param")
@Data
public class TbItemParam extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "item_no")
  private String itemNo;
  @Column(name = "item_param")
  private String itemParam;


}
