package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.Course;
import com.feng.framework.ycnweapp.UClockClass;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName CourseRepository
 * @Author 小风谷
 * @Date 2021/3/17 19:18
 * @Version 1.0
 * @Description
 */
public interface CourseRepository  extends JpaRepository<Course, Long> {
}
