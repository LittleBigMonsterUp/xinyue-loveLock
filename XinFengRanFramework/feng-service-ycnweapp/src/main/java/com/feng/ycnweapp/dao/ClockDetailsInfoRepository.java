package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.Clbum;
import com.feng.framework.ycnweapp.ClockDetailsInfo;
import com.feng.framework.ycnweapp.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName ClockDetailsInfoRepository
 * @Author 小风谷
 * @Date 2021/3/23 19:27
 * @Version 1.0
 * @Description
 */
public interface ClockDetailsInfoRepository extends JpaRepository<ClockDetailsInfo, Long>, JpaSpecificationExecutor<ClockDetailsInfo> {
    /**
     * 用于微信注册用户查找：UserId
     * @param CreatorId 微信UserIdd
     * @return openId对应的用户
     */
    @Query("select u.CreatorId from ClockDetailsInfo u where u.CreatorId=?1" )
    Long findByCreatorId(Long CreatorId);

    @Query("select u from ClockDetailsInfo u where u.id=?1" )
    ClockDetailsInfo findByProjectId(Long id);

    @Query("select u from ClockDetailsInfo u where u.id=?1 and u.CreatorId=?2" )
    ClockDetailsInfo findByProjectIdUserId(Long id,Long CreatorId);

    @Query("select u from ClockDetailsInfo u where u.CreatorId=?1" )
    List<ClockDetailsInfo> findByUserId(Long userid);
}
