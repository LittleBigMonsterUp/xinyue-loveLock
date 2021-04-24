package com.feng.ycnweapp.dao;

import com.feng.framework.ycnweapp.Admirer;
import com.feng.framework.ycnweapp.AllCommentInfo;
import com.feng.framework.ycnweapp.ClassInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName AdmirerRepository
 * @Author 小风谷
 * @Date 2021/3/23 19:25
 * @Version 1.0
 * @Description  TenLikeInfoRepository
 */
public interface AdmirerRepository extends JpaRepository<Admirer, Long> {

    @Query("select u from Admirer u where u.tenLikeId=?1 and  not exists (select 1 from Admirer t where u.tenLikeId=t.tenLikeId and u.id<t.id)" )
    Admirer findadmirerId(Long tenLikeId);

    // @Modifying
    // @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    // int updateViews(Long id);

}
