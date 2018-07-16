package com.three.order.orderjdbc.respository;

import com.three.order.orderjdbc.entity.TbItemDesc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by on 2018/7/13 0013.
 */
@Repository
public interface TbItemDescResp extends JpaRepository<TbItemDesc,Long>{

    TbItemDesc findItemDescByItemNo(String itemNo);
}
