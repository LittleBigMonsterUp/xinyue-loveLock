package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.AllCommentInfo;
import com.feng.framework.ycnweapp.DiaryResource;
import com.feng.framework.ycnweapp.GradeClbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName DiaryResourceRepository
 * @Author 小风谷
 * @Date 2021/3/23 19:31
 * @Version 1.0
 * @Description
 */
public interface DiaryResourceRepository extends JpaRepository<DiaryResource, Long> {

    @Query("select u from DiaryResource u where u.id=?1 and u.diaryId=?2" )
    List<DiaryResource> findByUserIdAndProjectId(Long diaryResourceId,Long diaryId);
}
