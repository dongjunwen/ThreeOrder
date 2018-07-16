package com.three.order.orderjdbc.respository;

import com.three.order.orderjdbc.entity.TbOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author:luiz
 * @Date: 2018/7/6 10:36
 * @Descripton:
 * @Modify :
 **/
@Repository
public interface TbOrderItemResp extends JpaRepository<TbOrderItem,Long> {


}
