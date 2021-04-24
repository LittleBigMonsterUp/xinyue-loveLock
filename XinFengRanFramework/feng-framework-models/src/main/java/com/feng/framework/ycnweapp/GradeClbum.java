package com.feng.framework.ycnweapp;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ClassName ClassCategory
 * @Author 小风谷
 * @Date 2021/3/5 12:00
 * @Version 1.0
 * @Description  班级分类
 */
@Data
@ToString
@Entity
@Table(name = "GradeClbum")
public class GradeClbum {
    //班级id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private   Long id;

    //班级名称
    @Column(name = "ClbumName")
    private  String ClbumName;


    //班级名称
    @Column(name = "PareadId")
    private  String PareadId;

    //年级分类
    @Column(name = "GradeName")
    private  String GradeName;

}
