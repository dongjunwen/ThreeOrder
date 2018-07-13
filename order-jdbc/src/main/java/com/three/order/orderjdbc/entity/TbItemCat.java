package com.three.order.orderjdbc.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Component
@Table(name = "tb_item_cat")
@Data
public class TbItemCat extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "cat_no")
  private String catNo;
  @Column(name = "pcat_no")
  private String pcatNo;
  @Column(name = "item_level")
  private long itemLevel;
  @Column(name = "item_sort")
  private long itemSort;
  private String status;



}
