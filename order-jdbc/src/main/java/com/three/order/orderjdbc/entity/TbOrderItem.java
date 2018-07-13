package com.three.order.orderjdbc.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
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
  private double price;
  private double num;
  private double amt;

}
