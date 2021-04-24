package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.ClockDetails;
import com.feng.framework.ycnweapp.ClockDetailsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName ClockDetailsRepository
 * @Author 小风谷
 * @Date 2021/4/5 16:36
 * @Version 1.0
 * @Description
 */
public interface ClockDetailsRepository extends JpaRepository<ClockDetails, Long>, JpaSpecificationExecutor<ClockDetails> {


    @Query("select u from ClockDetails u where u.ClockDetailsInfoId=?1 and u.UserId=?2" )
    ClockDetails findByProjectIdUserId(Long ClockDetailsInfoId, Long UserId);

    @Transactional
    @Modifying
    @Query("update ClockDetails c set c.ClockCount = c.ClockCount+1 where c.id = ?1")
    int updateClockCount(Long id);

    @Query("select u from ClockDetails  u where  u.id=?1")
    ClockDetails findByClockDetailsId(Long id);
}
