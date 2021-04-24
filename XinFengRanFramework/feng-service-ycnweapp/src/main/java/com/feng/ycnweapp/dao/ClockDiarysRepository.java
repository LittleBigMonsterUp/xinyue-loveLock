package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.ClockDiary;
import com.feng.framework.ycnweapp.ClockDiarys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName ClockDiarysRepository
 * @Author 小风谷
 * @Date 2021/4/24 13:13
 * @Version 1.0
 * @Description
 */
public interface ClockDiarysRepository extends JpaRepository<ClockDiarys, Long>, JpaSpecificationExecutor<ClockDiarys> {


}
