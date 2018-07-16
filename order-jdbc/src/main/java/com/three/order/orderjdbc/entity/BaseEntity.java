package com.three.order.orderjdbc.entity;

import com.three.order.ordercommon.utils.DateUtil;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by on 2018/7/13 0013.
 */
@Data
@MappedSuperclass
public class BaseEntity {
    @Temporal(TemporalType.DATE)
    @Column(name = "create_time")
    private Date createTime;
    @Temporal(TemporalType.DATE)
    @Column(name = "modi_time")
    private Date modiTime;
    public String getCreateTime(){
        return DateUtil.getDateFormat(createTime);
    }
    public String getModiTime(){
        return DateUtil.getDateFormat(modiTime);
    }
}
