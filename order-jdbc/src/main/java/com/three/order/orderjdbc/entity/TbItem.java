package com.three.order.orderjdbc.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Component
@Table(name = "tb_item")
@Data
public class TbItem extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "item_no")
  private String itemNo;
  @Column(name = "item_title")
  private String itemTitle;
  @Column(name = "sell_point")
  private String sellPoint;
  private BigDecimal price;
  private BigDecimal num;
  @Column(name = "bar_code")
  private String barcode;
  @Column(name = "pic_url")
  private String picUrl;
  @Column(name = "cat_no")
  private String catNo;
  private String status;



}
