package com.feng.framework.ycnweapp;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName TenLikeInfo
 * @Author 小风谷
 * @Date 2021/3/23 18:38
 * @Version 1.0
 * @Description 点赞记录表信息
 */
@Data
@ToString
@Entity
@Table(name = "TenLikeInfo")
public class TenLikeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public  Long id;

    /** 点赞者id*/
    @Column(name = "UseId")
    public  Long UseId;

    /** 创造该打卡者id*/
    @Column(name = "CreateId")
    public  Long CreateId;


    /** 被赞者 集合 */
    @Column(name = "AdmirerId")
    public Long AdmirerId ;

    /**日记id*/
    @Column(name = "diaryId")
    public  Long  diaryId;
}
