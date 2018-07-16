package com.three.order.orderjdbc.respository;

import com.three.order.orderjdbc.entity.TbOrderShipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author:luiz
 * @Date: 2018/7/6 10:36
 * @Descripton:
 * @Modify :
 **/
@Repository
public interface TbOrderShippingResp extends JpaRepository<TbOrderShipping,Long> {


}
