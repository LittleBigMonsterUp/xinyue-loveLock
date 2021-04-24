package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.AllCommentInfo;
import com.feng.framework.ycnweapp.ReviewerInfo;
import com.feng.framework.ycnweapp.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName ReviewerInfoRepository
 * @Author 小风谷
 * @Date 2021/3/23 19:36
 * @Version 1.0
 * @Description
 */
public interface ReviewerInfoRepository extends JpaRepository<ReviewerInfo, Long> {

    @Query("select u from ReviewerInfo u where u.AllCommentId=?1" )
    ReviewerInfo findreviewerInfoId(Long AllCommentId);
}
