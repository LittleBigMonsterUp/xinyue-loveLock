package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.Grade;
import com.feng.framework.ycnweapp.UClockClass;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName GradeRepository
 * @Author 小风谷
 * @Date 2021/3/17 19:02
 * @Version 1.0
 * @Description
 */
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
