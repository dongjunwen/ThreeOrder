package com.three.order.orderjdbc.entity;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by on 2018/7/13 0013.
 */
@Data
public class BaseEntity {
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "modi_time")
    private Date modiTime;
}
