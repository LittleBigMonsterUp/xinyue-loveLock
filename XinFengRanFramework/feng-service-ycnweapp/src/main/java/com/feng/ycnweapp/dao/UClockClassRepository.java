package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName UClockClassRepository
 * @Author 小风谷
 * @Date 2021/3/17 19:24
 * @Version 1.0
 * @Description
 */
public interface UClockClassRepository extends JpaRepository<TeacherCourse, Long> {

}
