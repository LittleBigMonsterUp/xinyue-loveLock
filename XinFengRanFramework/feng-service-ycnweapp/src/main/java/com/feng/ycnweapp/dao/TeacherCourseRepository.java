package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.Grade;
import com.feng.framework.ycnweapp.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName TeacherCourseRepository
 * @Author 小风谷
 * @Date 2021/3/17 19:23
 * @Version 1.0
 * @Description
 */
public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, Long> {
}
