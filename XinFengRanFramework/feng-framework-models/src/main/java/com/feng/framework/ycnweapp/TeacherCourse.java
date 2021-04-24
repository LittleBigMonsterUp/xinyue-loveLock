package com.feng.framework.ycnweapp;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @ClassName TeacherCourse
 * @Author 小风谷
 * @Date 2021/3/6 21:27
 * @Version 1.0
 * @Description 老师课程关联表
 */
@Data
@ToString
@Entity
@Table(name = "TeacherCourse")
public class TeacherCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //老师名
    @Column(name = "TeacherName")
    private String TeacherName;

    //用户身份证号
    @Column(name = "IdCard")
    private String IdCard;

    //课程名
    @Column(name = "CourseName")
    private String CourseName;

    //年级名
    @Column(name = "GradeName")
    private String GradeName;

    //创建班级名
    @Column(name = "ClassName")
    private String ClassName;

}
