package com.three.order.orderjdbc.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_order_item")
@Data
public class TbOrderItem extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "order_no")
  private String orderNo;
  @Column(name = "item_no")
  private String itemNo;
  @Column(name = "item_name")
  private String itemName;
  @Column(name = "item_pic_url")
  private String itemPicUrl;
  private BigDecimal price;
  private BigDecimal num;
  private BigDecimal amt;

}
