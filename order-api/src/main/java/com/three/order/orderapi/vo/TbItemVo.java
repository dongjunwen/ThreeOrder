package com.three.order.orderapi.vo;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class TbItemVo{
      private String itemNo;
      private String itemTitle;
      private String sellPoint;
      private BigDecimal price=BigDecimal.ZERO;
      private BigDecimal num=BigDecimal.ZERO;
      private String barcode;
      private String picUrl;
}
