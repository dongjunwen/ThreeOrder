package com.three.order.orderjdbc.respository;

import com.three.order.orderjdbc.entity.TbPayLog;
import com.three.order.orderjdbc.entity.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

/**
 * @Author:luiz
 * @Date: 2018/7/6 10:36
 * @Descripton:
 * @Modify :
 **/
@Repository
public interface TbPayLogResp extends JpaRepository<TbPayLog,Long> {

    TbPayLog findByOrderNoAndPaySeqNo( String orderNo,String paySeqNo);
}
