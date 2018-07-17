package com.three.order.orderjdbc.respository;

import com.three.order.orderjdbc.entity.TbOrder;
import com.three.order.orderjdbc.entity.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author:luiz
 * @Date: 2018/7/6 10:36
 * @Descripton:
 * @Modify :
 **/
@Repository
public interface TbOrderResp extends JpaRepository<TbOrder,Long> {


    TbOrder findByOrderNo(String orderNo);

    @Transactional
    @Modifying
    @Query("update TbOrder u set u.orderStatus = ?2," +
            "u.modiTime=?3" +
            " where u.orderNo=?1" )
    void updateStatus(String orderNo, long orderStatus, Date modiTime);
}
