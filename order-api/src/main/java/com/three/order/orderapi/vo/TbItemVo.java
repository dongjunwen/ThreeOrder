package com.three.order.orderapi.vo;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class TbItemVo{
      private String itemNo;
      private String itemTitle;
      private String sellPoint;
      private BigDecimal price;
      private BigDecimal num;
      private String barcode;
      private String picUrl;
}
