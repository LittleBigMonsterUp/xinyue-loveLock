package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.AllCommentInfo;
import com.feng.framework.ycnweapp.PublisherInfo;
import com.feng.framework.ycnweapp.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName PublisherInfoRepository
 * @Author 小风谷
 * @Date 2021/3/23 19:33
 * @Version 1.0
 * @Description
 */
public interface PublisherInfoRepository extends JpaRepository<PublisherInfo, Long> {
    @Query("select u from PublisherInfo u where u.id=?1" )
    PublisherInfo findpublisherInfoId(Long id);
}
