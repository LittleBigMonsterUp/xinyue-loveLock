package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.RespondentInfo;
import com.feng.framework.ycnweapp.ReviewerInfo;
import com.feng.framework.ycnweapp.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName RespondentInfoRepository
 * @Author 小风谷
 * @Date 2021/3/23 19:34
 * @Version 1.0
 * @Description
 */
public interface RespondentInfoRepository extends JpaRepository<RespondentInfo, Long> {
    @Query("select u from RespondentInfo u where u.AllCommentId=?1" )
    RespondentInfo findrespondentInfoId(Long AllCommentId);
}
