package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.ClassInformation;
import com.feng.framework.ycnweapp.ClockDetailsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @ClassName ClassInformationRepository
 * @Author 小风谷
 * @Date 2021/3/17 19:19
 * @Version 1.0
 * @Description
 */
public interface ClassInformationRepository extends JpaRepository<ClassInformation, Long> {

}
