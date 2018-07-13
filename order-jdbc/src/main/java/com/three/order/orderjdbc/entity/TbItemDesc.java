package com.three.order.orderjdbc.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Component
@Table(name = "tb_item_desc")
@Data
public class TbItemDesc extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "item_no")
  private String itemNo;
  @Column(name = "item_desc")
  private String itemDesc;



}
