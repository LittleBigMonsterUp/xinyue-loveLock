package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.Grade;
import com.feng.framework.ycnweapp.GradeClbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName GradeClbumRepository
 * @Author 小风谷
 * @Date 2021/3/17 19:21
 * @Version 1.0
 * @Description
 */
public interface GradeClbumRepository extends JpaRepository<GradeClbum, Long> , JpaSpecificationExecutor<GradeClbum> {


     @Query("select t from GradeClbum t where id in (select min(id) from GradeClbum m group by m.ClbumName)")
     List<GradeClbum> findClbumISAll();

     @Query("select t from GradeClbum t where id in (select min(id) from GradeClbum m group by m.GradeName)")
     List<GradeClbum> findGradeISAll();

}
