package com.three.order.orderjdbc.respository;

import com.three.order.orderjdbc.entity.TbItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by on 2018/7/13 0013.
 */
@Repository
public interface TbItemResp extends JpaRepository<TbItem,Long>{
    TbItem findByItemNo(String itemNo);
}
