package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.AllCommentInfo;
import com.feng.framework.ycnweapp.ClassInformation;
import com.feng.framework.ycnweapp.ClockDetailsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName AllCommentInfoRepository
 * @Author 小风谷
 * @Date 2021/3/23 19:26
 * @Version 1.0
 * @Description
 */
public interface AllCommentInfoRepository extends JpaRepository<AllCommentInfo, Long> {

    @Query("select u from AllCommentInfo u where u.diaryId=?1" )
    List<AllCommentInfo> findByallCommentId(Long diaryId);
}
