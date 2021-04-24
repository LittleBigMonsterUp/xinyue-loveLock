package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.TeacherCourse;
import com.feng.framework.ycnweapp.UClockTime;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName UClockTimeRepository
 * @Author 小风谷
 * @Date 2021/3/17 19:25
 * @Version 1.0
 * @Description
 */
public interface UClockTimeRepository extends JpaRepository<UClockTime, Long> {
}
