package com.three.order.orderjdbc.respository;

import com.three.order.orderjdbc.entity.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author:luiz
 * @Date: 2018/7/6 10:36
 * @Descripton:
 * @Modify :
 **/
@Repository
public interface TbUserResp extends JpaRepository<TbUser,Long> {

    @Query(value = "select *  from tb_user u where 1=1 and (u.email_addr=:emailAddr or u.phone_num=:phoneNum or u.nick_name=:nickName)",nativeQuery = true)
    TbUser findIndex(@Param("emailAddr") String emailAddr,@Param("phoneNum")String phoneNum,@Param("nickName")String nickName);
}
