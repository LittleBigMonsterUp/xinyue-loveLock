package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.AllCommentInfo;
import com.feng.framework.ycnweapp.TeacherCourse;
import com.feng.framework.ycnweapp.TenLikeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName TenLikeInfoRepository
 * @Author 小风谷
 * @Date 2021/3/23 19:37
 * @Version 1.0
 * @Description
 */
public interface TenLikeInfoRepository  extends JpaRepository<TenLikeInfo, Long> {

    @Query("select u from TenLikeInfo u  where u.diaryId=?1" )
    List<TenLikeInfo> findByTenLikeInfoId(Long diaryId);
}
