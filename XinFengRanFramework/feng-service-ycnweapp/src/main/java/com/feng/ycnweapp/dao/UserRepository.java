package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.UClockTime;
import com.feng.framework.ycnweapp.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @ClassName UserRepository
 * @Author 小风谷
 * @Date 2021/3/17 19:26
 * @Version 1.0
 * @Description
 */
public interface UserRepository  extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

    /**
     * 用于微信注册用户查找：根据openId查找用户
     * @param OpenId 微信openId
     * @return openId对应的用户
     */
    @Query("select u from User u where u.OpenId=?1" )
    User findByopenId(String OpenId);

    @Query("select u from User u where u.id=?1" )
    User findByUserId(Long id);
}
