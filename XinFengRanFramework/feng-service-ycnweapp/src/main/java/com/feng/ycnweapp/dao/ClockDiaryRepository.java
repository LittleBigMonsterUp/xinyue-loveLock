package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.AllCommentInfo;
import com.feng.framework.ycnweapp.ClockDetailsInfo;
import com.feng.framework.ycnweapp.ClockDiary;
import com.feng.framework.ycnweapp.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName ClockDiaryRepository
 * @Author 小风谷
 * @Date 2021/3/23 19:29
 * @Version 1.0
 * @Description
 */
public interface ClockDiaryRepository extends JpaRepository<ClockDiary, Long>,JpaSpecificationExecutor<ClockDiary> {


    /**
     * 获取打卡列表
     */
    @Query("select u from ClockDiary u where u.ClockDetailsInfoId=?1")
    Page<ClockDiary> findProjectId(Long ClockDetailsInfoId, Pageable pageable);


    @Query("select u from ClockDiary u where u.id=?1" )
    ClockDiary findclockDiaryId(Long id);



}
